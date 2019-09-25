package com.data.mysql.security;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.Base64Codec;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;


@Component
public class TokenAuthenticationService {

	private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationService.class);

	public static final String[] SWAGGER_PATH = { "/webjars/**", "/v2/api-docs", "/swagger-resources/**",
			"/swagger-ui.html" };
	public static final String[] PUBLIC_PATH = { 
			"/api/**/public/**",
			"/pages/**" ,
			"/app/**" ,
			"/",
			"/public/**"
			};
	public static final String AUTHEN_PATH = "/api/**/auth";
	public static final String OTP_PATH = "/api/**/otp";
	public static final String[] PERMITS_PATH = { "/api/**/captcha", "/api/**/statistic/**", "/api/**/medical/**",
			"/api/**/report/**" };

	private static long EXPIRATIONTIME;

	private static String SECRET;

	static final String TOKEN_PREFIX = "Bearer ";

	public static final String HEADER_STRING = "Authorization";

	static final String AUTHORITIES_KEY = "scopes";

	private static final String PUBLICKEY_START = "-----BEGIN PUBLIC KEY-----";

	private static final String PUBLICKEY_END = "-----END PUBLIC KEY-----";
	
	private static final String ALGORITHM = "RSA";

	private static String KEYPATH = "publickey.txt";

	private static final Cache authTokenCache = CacheManager.getInstance().getCache("authTokenCache");

	public void evictExpiredTokens() {
		logger.info("Evicting expired tokens");
		authTokenCache.evictExpiredElements();
	}

	@Value("${security.jwt.secret-key}")
	public void setSecretKey(String secretKey) {
		SECRET = secretKey;
	}

	@Value("${security.jwt.secret-otp-key}")
	public void setSecretOtpKey(String secretOtpKey) {
	}

	public static long getEXPIRATIONTIME() {
		return EXPIRATIONTIME;
	}

	@Value("${security.jwt.expired-time}")
	public void setEXPIRATIONTIME(long eXPIRATIONTIME) {
		EXPIRATIONTIME = eXPIRATIONTIME;
	}
	

	public static String getKEYPATH() {
		return KEYPATH;
	}

	public static void setKEYPATH(String kEYPATH) {
		KEYPATH = kEYPATH;
	}

	// Create "Authorization string" add to Header then sent to client
	public static String getNewToken(String sUsername, String sExtraData) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", sUsername);
		map.put("extradata", sExtraData);
		String accessToken = Jwts.builder().setSubject(sUsername).addClaims(map)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		return accessToken;
	}
	
	public static String getUsernameFromJWT() throws Exception {
		HttpServletRequest request = getCurrentRequest();
		String token = request.getHeader(HEADER_STRING).replaceAll(TOKEN_PREFIX, "");
		Claims claims = getClaims(token);
		return (String) claims.get("username");
	}

	
	public static String getExtraDataFromJWT() throws Exception {
		HttpServletRequest request = getCurrentRequest();
		String token = request.getHeader(HEADER_STRING).replaceAll(TOKEN_PREFIX, "");
		Claims claims = getClaims(token);
		return (String) claims.get("extradata");
	}
	

	private static HttpServletRequest getCurrentRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}
	
	public static String getToken() {
		String token = getCurrentRequest().getHeader(HEADER_STRING);
		return token == null ? "" : token.replaceFirst(TOKEN_PREFIX, "").trim();
	}
	
	// Check Authorization string from Client
	public static Authentication getAuthentication(HttpServletRequest request) throws Exception, IOException {
		String token = request.getHeader(HEADER_STRING);
		if (token == null)
			return null;
		token = token.replaceFirst(TOKEN_PREFIX, "");
		Set<GrantedAuthority> authorities = new HashSet<>();
		// token verify
		//Claims claims = getClaims(token);
		Claims claims = Jwts.parser()
	            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
	            .parseClaimsJws(token).getBody();
		return claims.get("username") != null ? new UsernamePasswordAuthenticationToken(claims.get("username"), null, authorities) : null;
	}

	public static PublicKey getKey(String key)  throws Exception{
		try {
			Base64Codec b64 = new Base64Codec();
			X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(b64.decode(key));
			KeyFactory kf = KeyFactory.getInstance(ALGORITHM);
			return kf.generatePublic(X509publicKey);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new Exception();
		}
	}

	public static Claims getClaims(String token) throws Exception {
		try {
			Resource resource = new ClassPathResource(KEYPATH);
			String publicKey = IOUtils.toString(resource.getInputStream());
			publicKey = publicKey.replace(PUBLICKEY_START, "").replace(PUBLICKEY_END, "");
			//Claims claims = Jwts.parser().setSigningKey(keys).parseClaimsJws(token).getBody();
			Claims claims = Jwts.parser()
		            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
		            .parseClaimsJws(token).getBody();
			return claims;
		} catch (ExpiredJwtException e) {
			throw new Exception("SESSION_EXPRIED");
		}catch (UnsupportedJwtException | MalformedJwtException |IOException | IllegalArgumentException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}catch (SignatureException e) {
			throw new Exception(e.getMessage());
		}
	}

	
}
