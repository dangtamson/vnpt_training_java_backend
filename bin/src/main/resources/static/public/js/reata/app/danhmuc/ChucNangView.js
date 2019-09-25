var ChucNangView = function(){
	
	var that = this;
	this.AppTitle = 'Danh mục chức năng';
	
	this.initPage = function(){
		$('#AppTitle').html(that.AppTitle);
		oDmChucNang.CHUCNANGID = 0;
		that.clearForm();
		that.bindGrid01();
		that.bindFormCancel();
	}
	
	this.bindFormAddNew = function(){
		ControlHelper.Input.disable(['#btnEdit','#btnDelete']);
		ControlHelper.Input.enable(['#btnSave','#btnCancel']);
		ControlHelper.Input.enable(['#ma','#ten','#url','#loaichucnang','#stt','.dsquyentruycap','#trangthai']);
	}
	
	this.bindFormCancel = function(){
		ControlHelper.Input.disable(['#btnSave','#btnEdit','#btnDelete']);
		ControlHelper.Input.disable(['#ma','#ten','#url','#loaichucnang','#stt','.dsquyentruycap','#trangthai']);
	}
	
	this.bindFormEdit = function(){
		ControlHelper.Input.disable(['#btnSave','#btnEdit']);
		ControlHelper.Input.enable(['#btnSave']);
		ControlHelper.Input.enable(['#ma','#ten','#url','#loaichucnang','#stt','.dsquyentruycap','#trangthai']);
	}
	
	this.bindFormSelect = function(){
		ControlHelper.Input.disable(['#btnSave']);
		ControlHelper.Input.enable(['#btnEdit','#btnDelete']);
		ControlHelper.Input.disable(['#ma','#ten','#url','#loaichucnang','#stt','.dsquyentruycap','#trangthai']);
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
		that.clearForm();
		$('#ma').val(oDmChucNang.MA);
		$('#trangthai').val(oDmChucNang.TRANGTHAI);
		$('#stt').val(oDmChucNang.STT);
		$('#chucnangid').val(oDmChucNang.CHUCNANGID);
		$('#ten').val(oDmChucNang.TEN);
		$('#url').val(oDmChucNang.URL);
		$('#loaichucnang').val(oDmChucNang.LOAICHUCNANG);
		
		if(oDmChucNang.DSQUYENTRUYCAP && oDmChucNang.DSQUYENTRUYCAP != ''){
			var sDsQuyen = oDmChucNang.DSQUYENTRUYCAP;
			var array = sDsQuyen.split(';');
			console.log(array);
			for(var i = 0; i< array.length; i++){
				var item = array[i];
				
				switch (item){
					case '0':
						$('#quyen_khach').prop('checked',true);
						break;
					case '1':
						$('#quyen_admin').prop('checked',true);
						break;
					case '2':
						$('#quyen_ketoan').prop('checked',true);
						break;
					case '3':
						$('#quyen_quanly').prop('checked',true);
						break;
				}
			}
		}
	}
	
	this.clearForm = function(){
		$('#ma').val('');
		$('#trangthai').val(1);
		$('#stt').val(0);
		$('#chucnangid').val(0);
		$('#ten').val('');
		$('#url').val('');
		$('#loaichucnang').val(1);
		$('#quyen_khach').prop('checked',false);
		$('#quyen_ketoan').prop('checked',false);
		$('#quyen_quanly').prop('checked',false);
		$('#quyen_admin').prop('checked',false);
		//$('#dsquyentruycap').val('');
		
	}
	
	this.bindGrid01 = function(){
		oDmChucNang.getAll();
        oTable.clear().draw();
        var aRows = [];
        var _html = '';
        
        for (var i = 0; i < oDmChucNang.LIST.length; i++) {
            var _item = oDmChucNang.LIST[i];
            var _hidden = '<input type="hidden" class="rowID" value="' + _item.CHUCNANGID + '" />';
            var sTrangThai = _item.TRANGTHAI == '0'?'<span class="label label-danger">Khóa</span>':'<span class="label label-success">Kích hoạt</span>';
            var sLoaiCn = _item.LOAICHUCNANG == '0'?'<span class="label label-primary">Chức năng</span>':'<span class="label label-warning">Báo cáo</span>';
            var arQuyen = (_item.DSQUYENTRUYCAP !='' && _item.DSQUYENTRUYCAP)? _item.DSQUYENTRUYCAP.split(';'):[];
            var sQuyen = '';
            for (var j = 0; j < arQuyen.length; j++) {
				let item1 = '';
				switch (arQuyen[j]) {
				case '0':
					item1 = 'Khách';
					break;
				case '1':
					item1 = 'Admin';
					break;
				case '2':
					item1 = 'Kế toán';
					break;
				case '3':
					item1 = 'Quản lý giám sát';
					break;

				default:
					break;
				}
				sQuyen +='<span class="label label-primary">'+ item1 +'</span> ';
			}
            aRows.push([
                (i + 1) + _hidden,
                sTrangThai,
                sLoaiCn,
                _item.MA,
                _item.TEN,
                _item.URL,
                sQuyen,
                _item.STT
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
			 oDmChucNang.MA = $('#ma').val();
			 oDmChucNang.TRANGTHAI = $('#trangthai').val();
			 oDmChucNang.STT = $('#stt').val();
			 oDmChucNang.TEN = $('#ten').val();
			 oDmChucNang.URL = $('#url').val();
			 oDmChucNang.LOAICHUCNANG = $('#loaichucnang').val();
			 
			 var arrQuyenTruyCap = [];
			 $.each($("input[name='dsquyentruycap']:checked"), function(){            
				 arrQuyenTruyCap.push($(this).val());
			 });
			 oDmChucNang.DSQUYENTRUYCAP = arrQuyenTruyCap.join(";");
			 console.log(oDmChucNang);
			 var rs = oDmChucNang.save();
			 if(rs.CODE == '0'){
				 that.initPage();
			 }
			 alert(rs.MESSAGE);
	     });
		 
		 $('.ACTIONS').on('click', '#btnDelete', function () {
			 if(!confirm("Bạn có chắc chắn muốn xóa m này không?")){return false;}
			 var rs = oDmChucNang.del();
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
                oDmChucNang.getById(id);
                that.bindForm();
                that.bindFormSelect();
	       }
	     });

	});
}