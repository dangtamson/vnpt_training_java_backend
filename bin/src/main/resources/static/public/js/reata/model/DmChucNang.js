var DmChucNang = function(){
	var that = this;
	
	this.CHUCNANGID = 0;
	this.MA = '';
	this.TEN = '';
	this.URL = '';
	this.LOAICHUCNANG = 1;
	this.DSQUYENTRUYCAP ='';
	this.TRANGTHAI = 1;
	this.STT = 0;
	
	this.LIST = [];
	
	this.getAll = function(){
		var rs = DATA.get('dm_chucnang_get_all');
		that.LIST = rs;
	}
	
	this.getById = function(sId){
		for(var i = 0; i< that.LIST.length; i++){
			var item = that.LIST[i];
			if(sId == item.CHUCNANGID){
				that.MA = item.MA;
				that.TRANGTHAI = item.TRANGTHAI;
				that.STT = item.STT;
				that.CHUCNANGID = item.CHUCNANGID;
				that.TEN = item.TEN;
				that.URL = item.URL;
				that.LOAICHUCNANG = item.LOAICHUCNANG;
				that.DSQUYENTRUYCAP = item.DSQUYENTRUYCAP;
			}
		}
	}
	
	this.save = function(){
		var arPars = [
			{key:'ma',value:that.MA},
			{key:'trangthai',value:that.TRANGTHAI},
			{key:'stt',value:that.STT},
			{key:'chucnangid',value:that.CHUCNANGID},
			{key:'ten',value:that.TEN},
			{key:'url',value:that.URL},
			{key:'loaichucnang',value:that.LOAICHUCNANG},
			{key:'dsquyentruycap',value:that.DSQUYENTRUYCAP}
		];
		var rs = DATA.set('dm_chucnang_save',arPars);
		return rs;
	}
	
	this.del = function(){
		if(that.CHUCNANGID !=''){
			var arPars = [
				{key:'chucnangid',value:that.CHUCNANGID}
			];
			var rs = DATA.set('dm_chucnang_del',arPars);
			return rs;
		}else{
			alert('Chưa chọn mục cần xóa.');
			return false;
		}
		
	}

}