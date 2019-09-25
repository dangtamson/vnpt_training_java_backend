var DmNhanVien = function(){
	var that = this;
	
	this.NHANVIENID = '';
	this.MA = '';
	this.TEN = '';
	this.USERNAME = '';
	this.PASSWORD = '';
	this.QUYENHAN = '0';
	this.KHOA = '1';
	
	this.LIST = [];
	
	this.getAll = function(){
		var rs = DATA.get('dm_nhanvien_get_all');
		that.LIST = rs;
	}
	
	this.getById = function(sId){
		for(var i = 0; i< that.LIST.length; i++){
			var item = that.LIST[i];
			if(sId == item.NHANVIENID){
				that.NHANVIENID = item.NHANVIENID;
				that.MA = item.MA;
				that.TEN = item.TEN;
				that.USERNAME = item.USERNAME;
				that.PASSWORD = item.PASSWORD;
				that.QUYENHAN = item.QUYENHAN;
				that.KHOA = item.KHOA;
			}
		}
	}

	// Truy vấn để lấy chi tiết
	this.getByIdServer = function(sId){
		var arPars = [
			{key:'nhanvienid',value:sId}
		];
		var rs = DATA.get('dm_nhanvien_getby_id',arPars);
		if (!rs || rs.length>0) {
			var item = rs[0];
			that.NHANVIENID = item.NHANVIENID;
			that.MA = item.MA;
			that.TEN = item.TEN;
			that.USERNAME = item.USERNAME;
			that.PASSWORD = item.PASSWORD;
			that.QUYENHAN = item.QUYENHAN;
			that.KHOA = item.KHOA;
		}
	}
	
	this.save = function(){
		if(that.NHANVIENID ==''){
			var arPars = [
				{key:'nhanvienid',value:that.NHANVIENID},
				{key:'ma',value:that.MA},
				{key:'ten',value:that.TEN},
				{key:'username',value:that.USERNAME},
				{key:'password',value:that.PASSWORD},
				{key:'quyenhan',value:that.QUYENHAN},
				{key:'khoa',value:that.KHOA}
			];
			var rs = DATA.cmd('dm_nhanvien_add',arPars);
			return rs;
		}else{
			var arPars = [
				{key:'nhanvienid',value:that.NHANVIENID},
				{key:'ma',value:that.MA},
				{key:'ten',value:that.TEN},
				{key:'username',value:that.USERNAME},
				{key:'password',value:that.PASSWORD},
				{key:'quyenhan',value:that.QUYENHAN},
				{key:'khoa',value:that.KHOA}
			];
			var rs = DATA.set('dm_nhanvien_edit',arPars);
			return rs;
		}
		
	}
	
	this.del = function(){
		if(that.NHANVIENID !=''){
			var arPars = [
				{key:'nhanvienid',value:that.NHANVIENID}
			];
			var rs = DATA.set('dm_nhanvien_del',arPars);
			return rs;
		}else{
			alert('Chưa chọn nhân viên cần xóa.');
			return false;
		}
		
	}

	this.bindSelect = function (pControl) {
        that.getAll();
        var _select = $(pControl);
        _select.html('');
        $.each(that.LIST, function () {
            _select.append($("<option />").val(this.NHANVIENID).text(this.TEN));
        });
        _select.select2({ placeholder: { id: '', text: '- Lựa chọn -', selected: 'selected' }, allowClear: true });
        _select.val(null).trigger("change");
    }

}