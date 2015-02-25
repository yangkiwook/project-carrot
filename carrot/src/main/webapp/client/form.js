$(document).on('click', '#closeModal', function() {
  $('input').val('');
});

$(document).on('click', '#btnCancel', function() {
	$('.my-update-form').css('display', 'none');
	$('.my-new-form').css('display', '');
	 $('input').val('');
		goods = null;
});

$(document).on('click','#btnDelete', function(){
	deleteClient($('#no').val());
});

$(document).on('click', '#btnUpdate', function() {
	$('.my-update-form').css('display', '');
	$('.my-new-form').css('display', 'none');
	if (
			client.supplierNo == $('#supplierNo').val() &&
			client.clientTel == $('#clientTel').val() &&
			client.clientPassword == $('#clientPassword').val() &&
			client.clientCorName == $('#clientCorName').val() &&
			client.clientMail == $('#clientMail').val() &&
			client.clientName == $('#clientName').val() &&
			client.mLevel == $('#mLevel').val() &&
			client.clientPostNo == $('#clientPostNo').val() &&
			client.clientAddress == $('#clientAddress').val() &&
			client.clientAddressDet == $('#clientAddressDet').val() &&
			client.clientMemo == $('#clientMemo').val()) {
		alert('변경한것이 없습니다');
		return;
	}
//	if (!validateForm()) return;
	updateClient($('#no').aaa);
});

$(document).on('click','#btnAdd',function(){

	$.post('../json/client/add.do' 
			, { 
				supplierNo : $('#supplierNo').val(),
				clientTel : $('#clientTel').val(),
				clientPassword : $('#clientPassword').val(),
				clientCorName : $('#clientCorName').val(),
				clientMail : $('#clientMail').val(),
				clientName : $('#clientName').val(),
				clientPostNo : $('#clientPostNo').val(),
				clientAddress : $('#clientAddress').val(),
				clientAddressDet : $('#clientAddressDet').val(),
				clientMemo : $('#clientMemo').val()
			} , function(result){ 
				if (result.status == "success") {
					loadClientList(maxPageNo,supplierNo);
					$('#btnCancel').click(); 
					$('#closeModal').click();
				} else {
					alert("등록 실패!");
				}
			} 
			, 'json' )
			.fail(function(jqXHR, textStatus, errorThrown){ 
				alert(textStatus + ":" + errorThrown);
			});
});

function loadClient(clientNo) {
	$.getJSON('../json/client/view.do?no=' + clientNo, 
			function(data){
		
		$('#btnCancel').click();
		$('#supplierNo').val(data.goods.supplierNo),
		$('#no').val(data.client.no),
		$('#clientTel').val(data.client.clientTel),
		$('#clientPassword').val(data.client.clientPassword),
		$('#clientCorName').val(data.client.clientCorName),
		$('#mLevel').val(data.client.mLevel),
		$('#clientMail').val(data.client.clientMail),
		$('#clientName').val(data.client.clientName),
		$('#clientPostNo').val(data.client.clientPostNo),
		$('#clientAddress').val(data.client.clientAddress),
		$('#clientAddressDet').val(data.client.clientAddressDet),
		$('#clientMemo').val(data.client.clientMemo),

		client = data.client;
		aaa = data.client.no;

		$('.my-update-form').css('display', '');
		$('.my-new-form').css('display', 'none');
		$('#myModalLabel2').css('display', '');
		$('#myModalLabel').css('display', 'none');
	});
}

function deleteClient(clientNo) {
	$.getJSON('../json/client/delete.do?no=' + clientNo, 
			function(data){
		if (data.status == 'success') {
			loadClientList(0, saveList);

			$('#btnCancel').click();
		}
	});
}

function updateClient(no) {
	$.post('../json/client/update.do'
			, {
				no : aaa,
				supplierNo : $('#supplierNo').val(),
				clientTel : $('#clientTel').val(),
				clientPassword : $('#clientPassword').val(),
				clientCorName : $('#clientCorName').val(),
				mLevel : $('#mLevel').val(),
				clientMail : $('#clientMail').val(),
				clientName : $('#clientName').val(),
				clientPostNo : $('#clientPostNo').val(),
				clientAddress : $('#clientAddress').val(),
				clientAddressDet : $('#clientAddressDet').val(),
				clientMemo : $('#clientMemo').val()
			} 
			, function(result){
				if (result.status == "success") {
					loadClientList(0,supplierNo);
					$('#btnCancel').click(); 
					$('#closeModal').click();
				} else {
					alert("변경 실패!");
				}
			} 
			, 'json')
			.fail(function(jqXHR, textStatus, errorThrown){ 
				alert(textStatus + ":" + errorThrown);
			});
}

function validateForm() {
	if ( $('#clientCorName').val().length == 0) {
		alert('제품명은 필수 입력 항목입니다.');
		return false;
	}

	if ( $('#clientName').val().length == 0) {
		alert('수량은 필수 입력 항목입니다.');
		return false;
	}

	if ( $('#clientPassword').val() == '0') {
		alert('제조사를 선택하세요');
		return false;
	}

	if ( $('#clientTel').val() == '0') {
		alert('제조사를 선택하세요');
		return false;
	}

	if ( $('#clientMail').val() == '0') {
		alert('제조사를 선택하세요');
		return false;
	}

	if ( $('#clientPostNo').val() == '0') {
		alert('제조사를 선택하세요');
		return false;
	}

	if ( $('#clientAddress').val() == '0') {
		alert('제조사를 선택하세요');
		return false;
	}

	if ( $('#clientAddressDet').val() == '0') {
		alert('제조사를 선택하세요');
		return false;
	}

	if ( $('#clientMemo').val() == '0') {
		alert('제조사를 선택하세요');
		return false;
	}

	return true;
}

