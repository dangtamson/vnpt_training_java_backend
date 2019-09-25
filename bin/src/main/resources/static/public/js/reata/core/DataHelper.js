var DataHelper = function(oConfig){
	
	var that = this;
	
	this.ApiUrl=oConfig.DataUrl;
	
	this.cmd = function(sCmd, aParams){
		
		if (typeof aParams === 'undefined') {
			aParams = [];
		}
		
		var _rs = null;


        var _data = {
        		type: 'FN',
        		cmd: sCmd,
        		params:aParams
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
            alert("ERR: " + textStatus);
            return false;
        });
        
        if(!_rs){alert('ERR: Return NULL'); return false;}
        
        if(_rs.CODE !='0'){alert('ERR: ' + _rs.MESSAGE); return false;}

        return _rs.RESULT;

	}
	
	this.get = function(sCmd, aParams){
		
		if (typeof aParams === 'undefined') {
			aParams = [];
		}
		
		var _rs = null;
		
        var _data = {
        		type: 'FN',
        		cmd: sCmd,
        		params:aParams
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
            alert("ERR: " + textStatus);
            return false;
        });
        
        if(!_rs){alert('ERR: Return NULL'); return false;}
        
        if(_rs.CODE !='0'){alert('ERR: ' + _rs.MESSAGE); return false;}

        return _rs.RESULT;

	}
	
	this.set = function(sCmd, aParams){
		
		if (typeof aParams === 'undefined') {
			aParams = [];
		}
		
		var _rs = null;


        var _data = {
        		type: 'FN',
        		cmd: sCmd,
        		params:aParams
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
            alert("ERR: " + textStatus);
            return false;
        });
        
        if(!_rs){alert('ERR: Return NULL'); return false;}
        
        if(_rs.CODE !='0'){alert('ERR: ' + _rs.MESSAGE); return false;}
        return _rs.RESULT[0];

	}
	
	
}