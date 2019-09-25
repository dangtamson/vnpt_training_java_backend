var KhachHangView = function(){
	
	var that = this;
	this.AppTitle = 'Danh mục Khách hàng';
	this.Id='';
	this.oTable = null;
	this.oDialog = null;
	this.oDmKhachHang = new DmKhachHang();
	
	this.init = function(){
		$('#AppTitle').html(that.AppTitle);
		that.filterAction('NEW');
		this.bindGrid01();
	}

	this.filterAction = function(sState){
		switch (sState) {
			case 'NEW':
				ControlHelper.Input.disable(['#btnEdit','#btnSave','#btnDelete','#btnCancel']);
				ControlHelper.Input.enable(['#btnAddNew']);
				break;
			case 'SELECT':
				ControlHelper.Input.disable(['#btnAddNew']);
				ControlHelper.Input.enable(['#btnEdit','#btnCancel','#btnDelete']);
				break;
			case 'EDIT':
				ControlHelper.Input.disable(['#btnEdit','#btnAddNew']);
				ControlHelper.Input.enable(['#btnSave','#btnCancel','#btnDelete']);
				break;
			default:
				break;
		}
	}

	this.bindGrid01 = function(){
		that.oDmKhachHang.getAll();
        that.oTable.clear().draw();
		var aRows = [];
        for (var i = 0; i < that.oDmKhachHang.LIST.length; i++) {
            var _item = that.oDmKhachHang.LIST[i];
			var _hidden = '<input type="hidden" class="rowID" value="' + _item.KHACHHANGID + '" />';
			var btnNhanVien = '<button class="btn btn-sm btn-link btnNhanVienInfo" data-id="'+ _item.NHANVIENID +'">'+ _item.NHANVIEN_TEN +'</button>';
            aRows.push([
                (i + 1) + _hidden,
                _item.MA,
				_item.TEN,
				_item.CHUCVU,
                _item.TENNGUOILIENHE,
                _item.SDT,
				_item.EMAIL,
				_item.DIACHI,
				btnNhanVien
            ]);
		}
        that.oTable.rows.add(aRows).draw();
	}
	

	$(function() {

		that.oTable = ControlHelper.Datatable.Init('Grid01', 10, true);
		that.oDialog = new PopupDialog(reload);
		that.init();

		function reload(){
			that.bindGrid01();
			that.oDmKhachHang.KHACHHANGID = '';
			that.filterAction('NEW');
		}

		$('#Grid01 tbody tr td').on('click', '.btnNhanVienInfo', function () {
			event.stopPropagation();
			that.oDialog.show('Thông tin nhân viên chăm sóc', '/reata/app/danhmuc/nhanvien_info?id=' + $(this).data('id'), '50%', '300px');
	     });

		$('#Grid01 tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
				that.oDmKhachHang.KHACHHANGID = '';
				that.filterAction('NEW');
            }
            else {
                that.oTable.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
                that.oDmKhachHang.KHACHHANGID = $(this).find('.rowID').val();
				that.filterAction('SELECT');
	       }
		 });
		 
		$('.ACTIONS').on('click', '#btnRefresh', function () {
			that.bindGrid01();
			that.oDmKhachHang.KHACHHANGID = '';
			that.filterAction('NEW');
		});

		$('.ACTIONS').on('click', '#btnEdit', function () {
			that.oDialog.show('Cập nhật thông tin khách hàng', '/reata/app/danhmuc/khachhang_ct?id=' + that.oDmKhachHang.KHACHHANGID, '85%', '500px');
		});

		$('.ACTIONS').on('click', '#btnAddNew', function () {
			that.oDialog.show('Thêm khách hàng', '/reata/app/danhmuc/khachhang_ct', '85%', '500px');
		});
		
		$('.ACTIONS').on('click', '#btnCancel', function () {
			that.oDmKhachHang.KHACHHANGID = '';
			$('#Grid01 tbody tr').removeClass('selected');
			that.filterAction('NEW');
		});
		
		$('.ACTIONS').on('click', '#btnDelete', function () {
			if (confirm('Bạn có chắc chắn muốn xóa mục này không?')) {
				var rs = that.oDmKhachHang.del();
				alert(rs.MESSAGE);
				that.bindGrid01();
				$('#Grid01 tbody tr').removeClass('selected');
				that.filterAction('NEW');
			}
	    });
	});
}