var DmNhanVienInfoView = function(){
	
	var that = this;
	
	this.init = function(){
		var id = Util.Url.getParameterByName('id');
		oDmNhanVien.getByIdServer(id);
		that.bindForm();
	}

	this.bindForm = function(){
		$('#Ma').val(oDmNhanVien.MA);
		$('#Ten').val(oDmNhanVien.TEN);
		$('#Username').val(oDmNhanVien.USERNAME);
		$('#Quyenhan').val(oDmNhanVien.QUYENHAN);
		$('#Khoa').val(oDmNhanVien.KHOA);
	}

	$(function() {
		that.init();

	});
}