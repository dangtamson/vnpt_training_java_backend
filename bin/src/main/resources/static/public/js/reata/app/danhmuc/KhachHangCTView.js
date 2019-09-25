var KhachHangCTView = function(){
	var that = this;
	this.AppTitle = '';
	this.Id = '';
	this.DmNhanVien = new DmNhanVien();
	this.DmKhachHang = new DmKhachHang();
	
	this.init = function(){
		that.DmKhachHang.KHACHHANGID = Util.Url.getParameterByName('id');
		that.DmNhanVien.bindSelect('#NHANVIENID');
		that.bindPopup();
		
	}

	this.filterAction = function(sState){
		switch (sState) {
			case 'NEW':
				ControlHelper.Input.enable(['#btnSave','#btnClose']);
				ControlHelper.Input.enable(['#KHACHHANGID','#MA','#TEN','#DIACHI','#EMAIL','#SDT','#WEBSITE'
				,'#TENNGUOILIENHE','#CHUCVU','#NHANVIENID',]);
				break;
			case 'EDIT':
				ControlHelper.Input.enable(['#btnSave','#btnClose']);
				ControlHelper.Input.enable(['#KHACHHANGID','#MA','#TEN','#DIACHI','#EMAIL','#SDT','#WEBSITE'
				,'#TENNGUOILIENHE','#CHUCVU','#NHANVIENID',]);
				break;
			default:
				break;
		}
	}

	this.bindPopup = function(){
		that.DmKhachHang.bindSuggestChucVu('#CHUCVU');
		if(!that.DmKhachHang.KHACHHANGID || that.DmKhachHang.KHACHHANGID ==''){
			that.AppTitle = 'Thêm mới khách hàng';
			this.filterAction('NEW');
		}else{
			that.AppTitle = 'Cập nhật khách hàng';
			this.filterAction('EDIT');
			that.DmKhachHang.getById(that.DmKhachHang.KHACHHANGID);
			$('#MA').val(that.DmKhachHang.MA);
			$('#TEN').val(that.DmKhachHang.TEN);
			$('#DIACHI').val(that.DmKhachHang.DIACHI);
			$('#EMAIL').val(that.DmKhachHang.EMAIL);
			$('#SDT').val(that.DmKhachHang.SDT);
			$('#WEBSITE').val(that.DmKhachHang.WEBSITE);
			$('#TENNGUOILIENHE').val(that.DmKhachHang.TENNGUOILIENHE);
			$('#CHUCVU').val(that.DmKhachHang.CHUCVU);
			$('#NHANVIENID').val(that.DmKhachHang.NHANVIENID).trigger('change');
			console.log('DmKhachHang',that.DmKhachHang);
		}
		$('.bootstrap-dialog-title', window.parent.document).html(that.AppTitle);
		
	}

	this.save = function(){
		that.DmKhachHang.MA = $('#MA').val();
		that.DmKhachHang.TEN = $('#TEN').val();
		that.DmKhachHang.DIACHI = $('#DIACHI').val();
		that.DmKhachHang.EMAIL = $('#EMAIL').val();
		that.DmKhachHang.SDT = $('#SDT').val();
		that.DmKhachHang.WEBSITE = $('#WEBSITE').val();
		that.DmKhachHang.TENNGUOILIENHE = $('#TENNGUOILIENHE').val();
		that.DmKhachHang.CHUCVU = $('#CHUCVU').val();
		that.DmKhachHang.NHANVIENID = $('#NHANVIENID').val();
		var rs = that.DmKhachHang.save();
		alert(rs.MESSAGE);
		that.bindPopup();

	}
	
	$(function() {

		that.init();

		$('.ACTIONS').on('click', '#btnClose', function () {
			window.parent.oKhachHangView.oDialog.close();
		});

		$('.ACTIONS').on('click', '#btnSave', function () {
			that.save();
		});



	});
}