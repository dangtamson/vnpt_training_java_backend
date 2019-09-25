var AuthenHelper = function(oConfig){
	
	var that = this;
	
	this.ApiUrl=oConfig.AuthenUrl;
	
	this.authen = function(sUser, sPass, sUnit){
		var _rs = null;


        var _data = {
        		unit: sUnit,
        		username: sUser,
        		password:sPass
        };

        var request = $.ajax({
            url: this.ApiUrl,
            type: "POST",
            data: JSON.stringify(_data),
            contentType: 'application/json; charset=utf-8',
            dataType: "json",
            async: false,
            beforeSend: function (xhr) {
                
            }
        });

        request.done(function (_response) {
            _rs = _response;
        });

        request.fail(function (jqXHR, textStatus) {
            alert("Lỗi tại server: " + textStatus);
            return false;
        });

        return _rs.RESULT;

	}
	
	this.logout = function(){
		localStorage.clear();
		window.location.href = '/';
	}
	
}