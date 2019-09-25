var DmKhoanChi = function(){
	var that = this;
	
	this.KHOANCHIID = 0;
	this.MA = '';
	this.TEN = '';
	this.LIST = [];
	
	this.getAll = function(){
		var rs = DATA.get('dm_khoanchi_get_all');
		that.LIST = rs;
	}
	
	this.getById = function(sId){
		for(var i = 0; i< that.LIST.length; i++){
			var item = that.LIST[i];
			if(sId == item.KHOANCHIID){
				that.MA = item.MA;
				that.TEN = item.TEN;
				that.KHOANCHIID = item.KHOANCHIID;
			}
		}
	}
	
	this.save = function(){
		var arPars = [
			{key:'ma',value:that.MA},
			{key:'khoanchiid',value:that.KHOANCHIID},
			{key:'ten',value:that.TEN}
		];
		var rs = DATA.set('dm_khoanchi_save',arPars);
		return rs;
	}
	
	this.del = function(){
		if(that.KHOANCHIID !=''){
			var arPars = [
				{key:'khoanchiid',value:that.KHOANCHIID}
			];
			var rs = DATA.set('dm_khoanchi_del',arPars);
			return rs;
		}else{
			alert('Chưa chọn mục cần xóa.');
			return false;
		}
		
	}

}