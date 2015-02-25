$(function() {
	OrderList();

});

var clientNo;

function OrderList() {
	$.getJSON('../../json/auth2/loginUser.do', function(data) {
		clientNo=data.loginUser.no;

		loadOrderList();
	});
}

function loadOrderList(){

	$.getJSON('../../json/order/mobilelist.do',function(data){
		var orders = data.orders;
		console.log(orders);
		require(['text!templates/list_detail_table.html'], function(html) {
			var template = Handlebars.compile(html);
			$('#listDiv').html(template(data));
			$('#page_order').page('destroy').page();

		});
	});
	
	
//	$(document).on('click','#listDiv_a', function(){
//		$.mobile.changePage('#page_order',
//				 {
//				  allowSamePageTransition : true,
//				  transition : 'none',
//				  showLoadMsg : true,
//				  reloadPage : true
//				 });
//		OrderList();
//		$('#page_order').page('destroy').page();
//	});

}

