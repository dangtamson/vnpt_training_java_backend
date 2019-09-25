package com.data.mysql.security;

import static com.data.mysql.security.TokenAuthenticationService.AUTHEN_PATH;
import static com.data.mysql.security.TokenAuthenticationService.PUBLIC_PATH;
import static com.data.mysql.security.TokenAuthenticationService.SWAGGER_PATH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SysL2Security extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.authorizeRequests().antMatchers(SWAGGER_PATH).permitAll().antMatchers(PUBLIC_PATH).permitAll()
				.antMatchers(HttpMethod.POST, AUTHEN_PATH).permitAll()
				// Need authentication.
				//.anyRequest().permitAll().and()
				.anyRequest().authenticated().and()
//				.addFilterBefore(new JWTOtpAuthenFilter(), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				// this disables session creation on Spring Security. This prevents spring from
				// creating and accepting cookies.
				.headers().frameOptions().sameOrigin().and() /* HieuBD 2019-06-06*/
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

}
