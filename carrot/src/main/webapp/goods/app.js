var currPageNo;
var maxPageNo;
var saveList="";
var goodsNo;
var categoryClick = 0;
var codeClick = 0;
var nameClick = 0;



//$(document).ready(function(){});
$(function() {

	$('.header').load('../common/header.html');
	$('.footer').load('../common/footer.html');
//	$('.form').load('form.html');

	loadGoodsList(1);

	$(document).on('click', '.data-row a', function() {
		loadGoods($(this).attr('data-no'));
	});

/*
	$(document).on('click', '#mycheck', function() {
		if ($(this).is(':checked')) {
			$("input[name=mycheck]").prop("checked", true);
		} else {
			$("input[name=mycheck]").prop("checked", false);
		}
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
			deleteGoods(check_value);
			loadGoods(1);
		}
	});
/*
	$(document).on('click', '.my-delete-btn', function() {
		if(confirm("삭제하시겠습니까?")) {
			$("input[name=mycheck]:checked").each(function() {
				var temp = $(this).val();
				console.log("test==>",temp);
				deleteGoods(temp);
			});
			
			loadGoods(0);
		}
	});
	*/
	$(document).on('click','#order-category-head',function(){
		$('#order-category-head').css('color','red');
		setcategoryList();
	});
	
	$(document).on('click','#order-code-head', function(){
		setcodeList();
	});

	$(document).on('click','#order-name-head', function(){
		setnameList();
	});
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
		loadGoodsList(num, saveList);		
	});
}

function setcategoryList(){
	categoryClick++;
	console.log(categoryClick);
	$('#order-category-head').css('color','red');

	if((categoryClick % 2) == 0){
		saveList = 'category';
		loadGoodsList(1,'category');
	} else if((categoryClick % 2) == 1){
		saveList = 'categoryDesc';
		loadGoodsList(1,'categoryDesc');
	} 
}

function setcodeList(){
	codeClick++;
	console.log(codeClick);

	if((codeClick % 2) == 0){
		saveList = 'code';
		loadGoodsList(1,'code');
	} else if((codeClick % 2) == 1){
		saveList = 'codeDesc';
		loadGoodsList(1,'codeDesc');
	} 
}

function setnameList(){
	nameClick++;
	console.log(nameClick);

	if((nameClick % 2) == 0){
		saveList = 'name';
		loadGoodsList(1,'name');
	} else if((nameClick % 2) == 1){
		saveList = 'nameDesc';
		loadGoodsList(1,'nameDesc');
	} 
}

function loadGoodsList(pageNo, orderBy, category, code, name, categoryDesc, codeDesc, nameDesc) {
	saveList = orderBy;

	if (pageNo <= 0)	pageNo = currPageNo;

	if (orderBy == null)	orderBy ="";
	if (category == null)	category ="";
	if (code == null)	code ="";
	if (name == null)	name ="";
	if (categoryDesc == null)	categoryDesc ="";
	if (codeDesc == null)	codeDesc ="";
	if (nameDesc == null)	nameDesc ="";

	$.getJSON('../json/goods/list.do?pageNo=' + pageNo + '&orderBy=' + orderBy
			+ '&category=' + category + '&code=' + code + '&name=' + name
			+ '&categoryDesc=' + categoryDesc + '&codeDesc=' + codeDesc + '&nameDesc=' + nameDesc,

			function(data) {
		setPageNo(data.currPageNo, data.maxPageNo);
		var goodss = data.goodss;
		console.log(goodss);
		require([ 'text!templates/goods-table.html' ], function(html) {
			var template = Handlebars.compile(html);
			$('#listDiv').html(template(data));
		});
	});
}

$(document).on('click', '#btnCancel', function() {
//	$('#btnCancel').click(function(){
	$('.my-update-form').css('display', 'none');
	$('.my-new-form').css('display', '');
	$('input').val('');
	goods = null;
});

$(document).on('click', '#btnDelete', function() {
//	$('#btnDelete').click(function(){		
	deleteGoods($('#no').val());
});


$(document).on('click', '#btnAdd', function() {
//	$('#btnAdd').click(function(){
	$.post('../json/goods/add.do'/* URL */ , 
			{
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
					loadGoodsList(maxPageNo);
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
		$('#code').val(data.goods.code),
		$('#name').val(data.goods.name),
		$('#unit').val(data.goods.unit),
		$('#category').val(data.goods.category),
		$('#note').val(data.goods.note),
		$('#priceA').val(data.goods.priceA),
		$('#priceB').val(data.goods.priceB),
		$('#priceC').val(data.goods.priceC)
//		$('#url').val(data.goods.url)

		goods = data.goods;
		goodsNo = data.goods.no;

		$('.my-update-form').css('display', '');
		$('.my-new-form').css('display', 'none');
		$('#myModalLabel2').css('display', '');
		$('#myModalLabel').css('display', 'none');
		
		$(document).on('click', '#btnUpdate', function() {
//			$('#btnUpdate').click(function(){
			if (
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
//			if (!validateForm()) return;
			console.log(goodsNo);
			updateGoods(goodsNo);
		});

	});
}

function deleteGoods(goodsNo) {
	$.getJSON('../json/goods/delete.do?no=' + goodsNo, 
			function(data){
		if (data.status == 'success') {
			loadGoodsList(0);

			$('#btnCancel').click();
		}
	});
}



function updateGoods(goodsNo) {
	console.log(goodsNo);
	$.post('../json/goods/update.do'
			, {
				no : goodsNo,
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

