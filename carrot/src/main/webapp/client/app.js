var currPageNo;
var maxPageNo;
var aaa;
var saveList="";

//$(document).ready(function(){});
$(function() {
	$('.header').load('../common/header.html');
	$('.form').load('form.html');
	$('.footer').load('../common/footer.html');
	preOrderList(1,supplierNo);
	
	$('#mLevel').selectpicker('refresh');
	
////////	
	$(document).on('click', '#clientTable tr', function(){
		loadClient($(this).attr('data-no'));
	});
	
	$(document).on('click', '.data-row tr', function() {
//		loadClientList($(this).attr('data-no'),supplierNo);
		loadClient($(this).attr('data-no'));
		loadClientList(0,supplierNo);
	});

	$(document).on('click', '.my-delete-btn', function() {
		deleteClient($(this).attr('data-no'));
		loadClientList(0,supplierNo);
	});
});

//$('#prevBtn').click(function(event) {
//	if (currPageNo > 1) {
//		loadClientList(currPageNo - 1);
//	}
//});
//
//$('#nextBtn').click(function(event) {
//	if (currPageNo < maxPageNo) {
//		loadClientList(currPageNo + 1);
//	}
//});

function setPageNo(currPageNo, maxPageNo) {
	window.currPageNo = currPageNo;
	window.maxPageNo = maxPageNo;

	
	console.log(currPageNo);
	console.log(maxPageNo);
	
	if (currPageNo <= 1)
		$('#prevBtn').css('display', 'none');
	else
		$('#prevBtn').css('display', '');

	if (currPageNo >= maxPageNo)
		$('#nextBtn').css('display', 'none');
	else
		$('#nextBtn').css('display', '');

	$('#page-selection').bootpag({
		total: maxPageNo,
		page: currPageNo,
		maxVisible: 10 
	}).on('page', function(event, num){
		loadClientList(num);		
	});
}

function loadClient(clientNo) {
	$.getJSON('../json/client/view.do?no=' + clientNo, 
			function(data){
		
		$('#btnCancel').click();
		$('#clientNo').val(data.client.no),
		$('#clientTel').val(data.client.clientTel),
		$('#clientPassword').val(data.client.clientPassword),
		$('#clientCorName').val(data.client.clientCorName),
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

var supplierNo;

function preOrderList(no, saveList){
	$.getJSON('../json/auth/loginUser.do', function(data) {
		supplierNo=data.loginUser.sno;

		loadClientList(1,supplierNo,saveList);
	});
}

function loadClientList(pageNo, supplierNo) {
	var supplierNo;
	
	if (pageNo <= 0)	pageNo = currPageNo;
	if (supplierNo == null)	supplierNo ="";
	
	$.getJSON('../json/auth/loginUser.do', function(data) {
		supplierNo=data.loginUser.sno;
		
		console.log(supplierNo);
	});
	
	$.getJSON('../json/client/list.do?pageNo=' + pageNo + '&supplierNo=' + supplierNo,
			function(data) {
		setPageNo(data.currPageNo, data.maxPageNo);
		var clients = data.clients;
		
		require(['text!templates/client-table.html'], function(html) {
			var template = Handlebars.compile(html);
			$('#listDiv').html(template(data));
		});
	});
}

