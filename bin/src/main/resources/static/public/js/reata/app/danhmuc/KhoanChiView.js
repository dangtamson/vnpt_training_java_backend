var KhoanChiView = function(){
	
	var that = this;
	this.AppTitle = 'Danh mục khoản chi';
	
	this.initPage = function(){
		$('#AppTitle').html(that.AppTitle);
		oDmKhoanChi.KHOANCHIID = 0;
		that.clearForm();
		that.bindGrid01();
		that.bindFormCancel();
	}
	
	this.bindFormAddNew = function(){
		ControlHelper.Input.disable(['#btnEdit','#btnDelete']);
		ControlHelper.Input.enable(['#btnSave','#btnCancel']);
		ControlHelper.Input.enable(['#ma','#ten']);
	}
	
	this.bindFormCancel = function(){
		ControlHelper.Input.disable(['#btnSave','#btnEdit','#btnDelete']);
		ControlHelper.Input.disable(['#ma','#ten']);
	}
	
	this.bindFormEdit = function(){
		ControlHelper.Input.disable(['#btnSave','#btnEdit']);
		ControlHelper.Input.enable(['#btnSave']);
		ControlHelper.Input.enable(['#ma','#ten']);
	}
	
	this.bindFormSelect = function(){
		ControlHelper.Input.disable(['#btnSave']);
		ControlHelper.Input.enable(['#btnEdit','#btnDelete']);
		ControlHelper.Input.disable(['#ma','#ten']);
	}
	
	
	this.bindForm = function(){
		that.clearForm();
		$('#ma').val(oDmKhoanChi.MA);
		$('#ten').val(oDmKhoanChi.TEN);
	}
	
	this.clearForm = function(){
		$('#ma').val('');
		$('#stt').val(0);
		$('#ten').val('');
	}
	
	this.bindGrid01 = function(){
		oDmKhoanChi.getAll();
        oTable.clear().draw();
        var aRows = [];
        var _html = '';
        
        for (var i = 0; i < oDmKhoanChi.LIST.length; i++) {
            var _item = oDmKhoanChi.LIST[i];
            var _hidden = '<input type="hidden" class="rowID" value="' + _item.KHOANCHIID + '" />';
            aRows.push([
                (i + 1) + _hidden,
                _item.MA,
                _item.TEN
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
			 oDmKhoanChi.MA = $('#ma').val();
			 oDmKhoanChi.TEN = $('#ten').val();
			 var rs = oDmKhoanChi.save();
			 if(rs.CODE == '0'){
				 that.initPage();
			 }
			 alert(rs.MESSAGE);
	     });
		 
		 $('.ACTIONS').on('click', '#btnDelete', function () {
			 if(!confirm("Bạn có chắc chắn muốn xóa mục này không?")){return false;}
			 var rs = oDmKhoanChi.del();
			 if(rs.CODE == '0'){
				 that.initPage();
			 }
			 alert(rs.MESSAGE);
	     });
		 
		 $('.ACTIONS').on('click', '#btnAddNew', function () {
			that.clearForm();
			that.bindFormAddNew();
	     });
		 
		 $('.ACTIONS').on('click', '#btnEdit', function () {
			 that.bindFormEdit();
		 });
		
		 $('#Grid01 tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
                that.clearForm();
                that.bindFormCancel();
            }
            else {
                oTable.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
                var id = $(this).find('.rowID').val();
                oDmKhoanChi.getById(id);
                that.bindForm();
                that.bindFormSelect();
	       }
	     });

	});
}