var DmNhanVienListView = function(){
	
	var that = this;
	
	this.initPage = function(){
		var arDisable = ['Ma','Ten','Username','Password','Quyenhan','Khoa','btnSave','btnEdit','btnDelete'];
		var arEnable = [];
		that.setEnabled(arEnable,arDisable);
		
		that.bindGrid01();
		that.clearForm();
	}
	
	this.setEnabled = function(_ena, _dis) {
        for (var i = 0; i < _ena.length; i++) {
            $('#' + _ena[i]).attr('disabled', false);
        }
        for (var i = 0; i < _dis.length; i++) {
            $('#' + _dis[i]).attr('disabled', true);
        }
    }
	
	this.bindForm = function(){
		$('#Ma').val(oDmNhanVien.MA);
		$('#Ten').val(oDmNhanVien.TEN);
		$('#Username').val(oDmNhanVien.USERNAME);
		$('#Password').val(oDmNhanVien.PASSWORD);
		$('#Quyenhan').val(oDmNhanVien.QUYENHAN);
		$('#Khoa').val(oDmNhanVien.KHOA);
	}
	
	this.clearForm = function(){
		oDmNhanVien.NHANVIENID = '';
		$('#Ma').val('');
		$('#Ten').val('');
		$('#Username').val('');
		$('#Password').val('');
		$('#Quyenhan').val('0');
		$('#Khoa').val('1');
	}
	
	this.bindGrid01 = function(){
		oDmNhanVien.getAll();
        oTable.clear().draw();
        var aRows = [];
        var _html = '';
        
        for (var i = 0; i < oDmNhanVien.LIST.length; i++) {
            var _item = oDmNhanVien.LIST[i];
            var _hidden = '<input type="hidden" class="rowID" value="' + _item.NHANVIENID + '" />';
            var sKhoa = _item.KHOA == '1'?'<span class="label label-danger">Khóa</span>':'<span class="label label-success">Kích hoạt</span>';
            aRows.push([
                (i + 1) + _hidden,
                _item.MA,
                _item.TEN,
                _item.USERNAME,
                _item.QUYENHAN_NAME,
                sKhoa
            ]);
        }
        oTable.rows.add(aRows).draw();
	}
	

	$(function() {
		
		 oTable = ControlHelper.Datatable.Init('Grid01', 10, true)
		
		that.initPage();
		 
		 $('.ACTIONS').on('click', '#btnCancel', function () {
			 that.initPage();
	     });
		 
		 $('.ACTIONS').on('click', '#btnSave', function () {
			 oDmNhanVien.MA = $('#Ma').val();
			 oDmNhanVien.TEN = $('#Ten').val();
			 oDmNhanVien.USERNAME = $('#Username').val();
			 oDmNhanVien.PASSWORD = $('#Password').val();
			 oDmNhanVien.QUYENHAN = $('#Quyenhan').val();
			 oDmNhanVien.KHOA = $('#Khoa').val();
			 var rs = oDmNhanVien.save();
			 if(rs.CODE == '0'){
				 that.initPage();
			 }
			 alert(rs.MESSAGE);
	     });
		 
		 $('.ACTIONS').on('click', '#btnDelete', function () {
			 if(!confirm("Bạn có chắc chắn muốn xóa nhân viên này không?")){return false;}
			 var rs = oDmNhanVien.del();
			 if(rs.CODE == '0'){
				 that.initPage();
			 }
			 alert(rs.MESSAGE);
	     });
		 
		 $('.ACTIONS').on('click', '#btnAddNew', function () {
			that.clearForm();
			var arDisable = ['btnEdit','btnDelete'];
			var arEnable = ['Ten','Password','Quyenhan','Khoa','Ma','Username','btnSave'];
			that.setEnabled(arEnable,arDisable);
	     });
		 
		 $('.ACTIONS').on('click', '#btnEdit', function () {
				var arDisable = ['btnEdit','btnDelete','Password'];
				var arEnable = ['Ten','Quyenhan','Khoa','btnSave'];
				that.setEnabled(arEnable,arDisable);
		     });
		
		 $('#Grid01 tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
                var arDisable = ['Ma','Username','Ten','Password','Quyenhan','Khoa','btnEdit','btnDelete','btnSave'];
        		var arEnable = [];
        		that.setEnabled(arEnable,arDisable);
            }
            else {
                oTable.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
                var id = $(this).find('.rowID').val();
                oDmNhanVien.getById(id);
                that.bindForm();
                var arDisable = [];
        		var arEnable = ['btnEdit','btnDelete'];
        		that.setEnabled(arEnable,arDisable);
	            }
	     });

	});
}