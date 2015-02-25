var currPageNo;
var maxPageNo;
var sno;
$(function() {
	$('.header').load('../common/header.html');
	$('.form').load('form.html');
	$('.footer').load('../common/footer.html');
	
	loadOrderList(1);
	$(document).on('click', '.data-row a', function() {
		loadOrderList($(this).attr('data-no'));
	});
/*
	$(document).on('click', '.my-delete-btn', function() {
		delete ($(this).attr('data-no'));
		loadOrderList(0);
	});
	*/
	$(document).on('click', '.my-delete-btn', function() {
		if(confirm("삭제하시겠습니까?")){
			var check_value = new Array();
			var j = 0;
			var check_arr = $(".ab");
			for (var i = 0; i < check_arr.length ; i++) {
				if (check_arr[i].checked) {
					check_value[j++] = check_arr[i].value;
				}
			}
			console.log(check_value);
			deleteOrders(check_value);
			loadOrderList(0);
		}
	});
});

$('#prevBtn').click(function(event) {
	if (currPageNo > 1) {
		loadOrderList(currPageNo - 1);
	}
});

$('#nextBtn').click(function(event) {
	if (currPageNo < maxPageNo) {
		loadOrderList(currPageNo + 1);
	}
});


function setPageNo(currPageNo, maxPageNo) {
	window.currPageNo = currPageNo;
	window.maxPageNo = maxPageNo;

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
		loadOrderList(num);		
	});
}


function loadOrderList(pageNo, sno) {
	if (pageNo <= 0)
		pageNo = currPageNo;

	$.getJSON('../json/order/list.do?pageNo=' + pageNo, function(data) {
		setPageNo(data.currPageNo, data.maxPageNo);
		var orders = data.orders;
		
		console.log(orders);

		require([ 'text!templates/orderClient-table.html' ], function(html) {
			var template = Handlebars.compile(html);
			$('#listDiv').html(template(data));
			//console.log("deliverys : " + deliverys);
		});
	});
}

