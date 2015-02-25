var currPageNo;
var maxPageNo;
$(function() {
	$('.header').load('../common/header.html');
	$('.form').load('form.html');
	$('.footer').load('../common/footer.html');
	
	loadDeliveryList(1);
	$(document).on('click', '.data-row a', function() {
		loadDeliveryList($(this).attr('data-no'));
	});

	$(document).on('click', '.my-delete-btn', function() {
		delete ($(this).attr('data-no'));
		loadDeliveryList(0);
	});
});

$('#prevBtn').click(function(event) {
	if (currPageNo > 1) {
		loadDeliveryList(currPageNo - 1);
	}
});

$('#nextBtn').click(function(event) {
	if (currPageNo < maxPageNo) {
		loadDeliveryList(currPageNo + 1);
	}
});

function setPageNo(currPageNo, maxPageNo) {
	window.currPageNo = currPageNo;
	window.maxPageNo = maxPageNo;

	$('#pageNo').html(currPageNo);

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
		loadDeliveryList(num);		
	});
}

function loadDeliveryList(pageNo) {
	if (pageNo <= 0)
		pageNo = currPageNo;

	$.getJSON('../json/order2/list.do?pageNo=' + pageNo, function(data) {
		setPageNo(data.currPageNo, data.maxPageNo);
		var orders = data.orders;
		var oname = data.oname;
		var oodate = data.oodate;
		var mlevel = data.ograde;

		var ccname = " "+data.oname;
		var oddate = "("+data.oodate+")";
		var mlevel = data.mlevel;
		var title = ccname+oddate;
		
		require([ 'text!templates/order-table.html' ], function(html) {
			var template = Handlebars.compile(html);
			$('#listDiv').html(template(data));		

			console.log("oname : " + oname);
			console.log("odate : " + oodate);
			console.log("mlevel : " + mlevel);
			$('#listDiv').html(template(data));
			$('#titleDiv').html(title);
		});
	});
}

