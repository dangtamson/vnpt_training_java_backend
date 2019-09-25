var LoginView = function(){
	
	var that = this;
	
	this.initPage = function(){
		
		$('#Message').html('');
		
		if(localStorage.USERINFO){
			window.location.href = oReataConfig.HomeUrl;
		}
		
	}
	
	
	
	$(function() {
		
		that.initPage();
		
		$('.actions #btnLogin').on('click',function(){
			var sUsername = $('#Username').val();
			var sPassword = $('#Password').val();
			
			if(sUsername.length < 3 || sPassword.length < 3){
				$('#Message').html('Tài khoản hoặc mật khẩu không hợp lệ.');
				$('#Username').focus();
				return false;
			}
			
			var oResult =  oAuthenHelper.authen(sUsername,sPassword,'0');
			if(oResult && oResult != '' && oResult != null){
				localStorage.USERINFO = JSON.stringify(oResult);
				window.location.href = oReataConfig.HomeUrl;
			}else{
				 $('#Username').val('');
				 $('#Password').val('');
				 $('#Username').focus();
				 $('#Message').html('Tài khoản hoặc mật khẩu không đúng.');
				 
			}
			
		});
		
	});
}