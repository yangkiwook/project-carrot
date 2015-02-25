

$(document).on('click', '#closeModal', function() {
  $('input').val('');
});

$(document).on('click', '#btnCancel', function() {
//$('#btnCancel').click(function(){
	$('.my-update-form').css('display', 'none');
	$('.my-new-form').css('display', '');
  $('input').val('');
	goods = null;
});

$(document).on('click', '#btnDelete', function() {
//$('#btnDelete').click(function(){		
	deleteGoods($('#no').val());
	
});

$(document).on('click', '#btnUpdate', function() {
//$('#btnUpdate').click(function(){

	$('.my-update-form').css('display', '');
	$('.my-new-form').css('display', 'none');
	if (goods.supplierNo == $('#supplierNo').val() &&
			goods.code == $('#code').val() &&
			goods.name == $('#name').val() &&
			goods.url == $('#url').val() &&
			goods.unit == $('#unit').val() &&
			goods.category == $('#category').val() &&
			goods.note == $('#note').val() &&
			goods.priceA == $('#priceA').val() &&
			goods.priceB == $('#priceB').val() &&
			goods.priceC == $('#priceC').val()) {
		alert('변경한것이 없습니다');
		return;
	}

//	if (!validateForm()) return;
	updateGoods(aaa);
});

$(document).on('click', '#btnAdd', function() {
//$('#btnAdd').click(function(){
	$.post('../json/goods/add.do'/* URL */ , 
			{
			no : $('#no').val(),
			supplierNo : $('#supplierNo').val(),
			code : $('#code').val(),
			name : $('#name').val(),
			url : $('#url').val(),
			unit : $('#unit').val(),
			category : $('#category').val(),
			note : $('#note').val(),
			priceA : $('#priceA').val(),
			priceB : $('#priceB').val(),
			priceC : $('#priceC').val()
			}, function(result) {
				if (result.status == "success") {
					loadGoodsList(1);
					$('#btnCancel').click();
					$('#closeModal').click();
					
				} else {
					alert("등록 실패!");
				}
			}/* 서버로부터 응답을 받았을 때 호출될 메서드 */
			, 'json'/* 서버가 보낸 데이터를 JSON형식으로 처리 */)
			/* 서버 요청이 실패했을때 호출될 함수 등록 */
			.fail(function(jqXHR, textStatus, errorThrown){
				alert(textStatus + ":" + errorThrown);
			});
});


function loadGoods(goodsNo) {
	$.getJSON('../json/goods/view.do?no=' + goodsNo, 
			function(data){
		$('#btnCancel').click();
		$('#supplierNo').val(data.goods.supplierNo),
		$('#code').val(data.goods.code),
		$('#name').val(data.goods.name),
		$('#unit').val(data.goods.unit),
		$('#category').val(data.goods.category),
		$('#note').val(data.goods.note),
		$('#priceA').val(data.goods.priceA),
		$('#priceB').val(data.goods.priceB),
		$('#priceC').val(data.goods.priceC),
//		$('#url').val(data.goods.url)

		goods = data.goods;
		aaa = data.goods.no;
		
		$('.my-update-form').css('display', '');
		$('.my-new-form').css('display', 'none');
	});
}

function deleteGoods(goodsNo) {
	$.getJSON('../json/goods/delete.do?no=' + goodsNo, 
			function(data){
		if (data.status == 'success') {
			loadGoodsList(0, saveList);

			$('#btnCancel').click();
		}
	});
}



function updateGoods(goodsNo) {
	$.post('../json/goods/update.do'
			, {
				no : aaa,
				supplierNo : $('#supplierNo').val(),
				code : $('#code').val(),
				name : $('#name').val(),
				unit : $('#unit').val(),
				category : $('#category').val(),
				note : $('#note').val(),
				priceA : $('#priceA').val(),
				priceB : $('#priceB').val(),
				priceC : $('#priceC').val(),
				url : $('#url').val()
			}, function(result) {
				console.log(result);
				if (result.status == "success") {
					loadGoodsList(0);
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

