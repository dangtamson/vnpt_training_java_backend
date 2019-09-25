var DmKhachHang = function(){
	var that = this;
	
	this.KHACHHANGID = '';
	this.MA = '';
	this.TEN = '';
	this.DIACHI = '';
	this.EMAIL = '';
	this.SDT = '';
	this.WEBSITE = '';
	this.TENNGUOILIENHE = '';
	this.CHUCVU = '';
	this.NHANVIENID = '';

	this.ChucVuSuggest = [];
	this.LIST = [];
	
	this.getAll = function(){
		var rs = DATA.get('dm_khachhang_get_all');
		that.LIST = rs;
	}
	
	this.getById = function(sId){
		var arPars = [
			{key:'khachhangid',value:sId}
		];
		var rs = DATA.get('dm_khachhang_getby_id',arPars);
		console.log('rs',rs);
		if (!rs || rs.length>0) {
			var item = rs[0];
			that.KHACHHANGID = item.KHACHHANGID;
			that.MA = item.MA;
			that.TEN = item.TEN;
			that.DIACHI = item.DIACHI;
			that.EMAIL = item.EMAIL;
			that.SDT = item.SDT;
			that.WEBSITE = item.WEBSITE;
			that.TENNGUOILIENHE = item.TENNGUOILIENHE;
			that.CHUCVU = item.CHUCVU;
			that.NHANVIENID = item.NHANVIENID;
		}
	}
	
	this.save = function(){
		var arPars = [
			{key:'KHACHHANGID',value:that.KHACHHANGID},
			{key:'MA',value:that.MA},
			{key:'TEN',value:that.TEN},
			{key:'DIACHI',value:that.DIACHI},
			{key:'EMAIL',value:that.EMAIL},
			{key:'SDT',value:that.SDT},
			{key:'WEBSITE',value:that.WEBSITE},
			{key:'TENNGUOILIENHE',value:that.TENNGUOILIENHE},
			{key:'CHUCVU',value:that.CHUCVU},
			{key:'NHANVIENID',value:that.NHANVIENID}
		];
		var rs = DATA.set('dm_khachhang_save',arPars);
		if (rs && rs.CODE=='0') {
			that.KHACHHANGID = rs.RESULT;
		}
		
		return rs;
	}
	
	this.del = function(){
		if(that.KHACHHANGID !=''){
			var arPars = [
				{key:'khachhangid',value:that.KHACHHANGID}
			];
			var rs = DATA.set('dm_khachhang_del',arPars);
			return rs;
		}else{
			alert('Chưa chọn mục cần xóa.');
			return false;
		}
	}

	this.bindSuggestChucVu = function (pControl) {
		var rs = DATA.get('dm_khachhang_chucvu_get_suggest');
		var aChucVu = [];
		for (let index = 0; index < rs.length; index++) {
			const element = rs[index].CHUCVU;
			aChucVu.push(element);
		}
		$(pControl).autocomplete({source: aChucVu});
    }

}