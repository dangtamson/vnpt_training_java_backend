var DmLoaiChiPhi = function(){
	var that = this;
	
	this.LOAICHIPHIID = 0;
	this.MA = '';
	this.TEN = '';
	this.LIST = [];
	
	this.getAll = function(){
		var rs = DATA.get('dm_loaichiphi_get_all');
		that.LIST = rs;
	}
	
	this.getById = function(sId){
		for(var i = 0; i< that.LIST.length; i++){
			var item = that.LIST[i];
			if(sId == item.LOAICHIPHIID){
				that.MA = item.MA;
				that.TEN = item.TEN;
				that.LOAICHIPHIID = item.LOAICHIPHIID;
			}
		}
	}
	
	this.save = function(){
		var arPars = [
			{key:'ma',value:that.MA},
			{key:'loaichiphiid',value:that.LOAICHIPHIID},
			{key:'ten',value:that.TEN}
		];
		var rs = DATA.set('dm_loaichiphi_save',arPars);
		return rs;
	}
	
	this.del = function(){
		if(that.LOAICHIPHIID !=''){
			var arPars = [
				{key:'loaichiphiid',value:that.LOAICHIPHIID}
			];
			var rs = DATA.set('dm_loaichiphi_del',arPars);
			return rs;
		}else{
			alert('Chưa chọn mục cần xóa.');
			return false;
		}
		
	}

}