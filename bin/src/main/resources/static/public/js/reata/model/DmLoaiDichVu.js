var DmLoaiDichVu = function(){
	var that = this;
	
	this.LOAIDICHVUID = 0;
	this.MA = '';
	this.TEN = '';
	this.LIST = [];
	
	this.getAll = function(){
		var rs = DATA.get('dm_loaidichvu_get_all');
		that.LIST = rs;
	}
	
	this.getById = function(sId){
		for(var i = 0; i< that.LIST.length; i++){
			var item = that.LIST[i];
			if(sId == item.LOAIDICHVUID){
				that.MA = item.MA;
				that.TEN = item.TEN;
				that.LOAIDICHVUID = item.LOAIDICHVUID;
			}
		}
	}
	
	this.save = function(){
		var arPars = [
			{key:'ma',value:that.MA},
			{key:'loaidichvuid',value:that.LOAIDICHVUID},
			{key:'ten',value:that.TEN}
		];
		var rs = DATA.set('dm_loaidichvu_save',arPars);
		return rs;
	}
	
	this.del = function(){
		if(that.LOAIDICHVUID !=''){
			var arPars = [
				{key:'loaidichvuid',value:that.LOAIDICHVUID}
			];
			var rs = DATA.set('dm_loaidichvu_del',arPars);
			return rs;
		}else{
			alert('Chưa chọn mục cần xóa.');
			return false;
		}
		
	}

}