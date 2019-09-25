var DmLoaiChungTu = function(){
	var that = this;
	
	this.LOAICHUNGTUID = 0;
	this.MA = '';
	this.TEN = '';
	this.LIST = [];
	
	this.getAll = function(){
		var rs = DATA.get('dm_loaichungtu_get_all');
		that.LIST = rs;
	}
	
	this.getById = function(sId){
		console.log('sId >> ',sId);
		console.log(that.LIST);
		for(var i = 0; i< that.LIST.length; i++){
			var item = that.LIST[i];
			if(sId == item.LOAICHUNGTUID){
				that.MA = item.MA;
				that.TEN = item.TEN;
				that.LOAICHUNGTUID = item.LOAICHUNGTUID;
				console.log('that.LOAICHUNGTUID >> ',that.LOAICHUNGTUID);
			}
		}
	}
	
	this.save = function(){
		var arPars = [
			{key:'ma',value:that.MA},
			{key:'loaichungtuid',value:that.LOAICHUNGTUID},
			{key:'ten',value:that.TEN}
		];
		var rs = DATA.set('dm_loaichungtu_save',arPars);
		return rs;
	}
	
	this.del = function(){
		if(that.LOAICHUNGTUID !=''){
			var arPars = [
				{key:'loaichungtuid',value:that.LOAICHUNGTUID}
			];
			var rs = DATA.set('dm_loaichungtu_del',arPars);
			return rs;
		}else{
			alert('Chưa chọn mục cần xóa.');
			return false;
		}
		
	}

}