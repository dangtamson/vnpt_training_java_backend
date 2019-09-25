var TopBar = function() {

	var that = this;
	
	this.init = function() {
		var UserInfo =JSON.parse( window.localStorage.getItem('USERINFO'));
		console.log(UserInfo);
		$('#UserInfo .name').html(UserInfo.Username);
		$('#UserInfo .pos').html(UserInfo.Ten);
		
	}

	this.getClock = function() {
		var date = new Date();
		function addZero(x) {
			if (x < 10) {
				return x = '0' + x;
			} else {
				return x;
			}
		}

		function twelveHour(x) {
			if (x > 12) {
				return x = x - 12;
			} else if (x == 0) {
				return x = 12;
			} else {
				return x;
			}
		}

		var h = addZero(twelveHour(date.getHours()));
		var m = addZero(date.getMinutes());
		var s = addZero(date.getSeconds());
		return h + ':' + m + ':' + s;
	}
	
	
	this.updateClock = function(){
		$('#DongHo').text(that.getClock());
	}
	
	$(function(){
		
		that.init();
		
		setInterval(that.updateClock, 1000);

		$('#btnSignOut').on('click',function(){
			oAuthenHelper.logout();
		});
		
		$(".menus .item").click(function () {
            $(this).toggleClass("active").siblings().removeClass('active');

        });
		
        $(".header .nav-icon").click(function () {
            $("body").toggleClass("minimize-menu");

        });
        
        $(".menus li").each(function(){
            if($(this).hasClass("active")){
                $(this).parent().parent().addClass("active");
            }
        });
        
        $(".overlay-common").click(function () {
            $("body").removeClass("minimize-menu");
        }); 
        
        $('#btnSignOut').click(function(){
        	oAuthenHelper.logout();
        });
	})
	
}