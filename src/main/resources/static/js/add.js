"use strict";

$(function() {
	$("#btnAdd").val("add").css({ "margin-bottom": "25px", "margin-top": "10px" }).on('click', function() {
		
		//フォームに追加するコードを記述.
		let input_name = '<div class="form-block" id="form-block"><div class="form-group" id="dynamicForm1"><label for="inputName" class="col-sm-2 control-label">name</label><div class="col-sm-8"><input type="text" class="form-control" id="inputName" name="name"th:field="*{name}" /><div th:errors="*{name}" style="color: red" class="error-messages"></div></div></div>'
		let input_price = '<div class="form-group" id="dynamicForm2"><label for="price" class="col-sm-2 control-label">price</label><div class="col-sm-8"><input type="text" class="form-control" id="price" name="price"　th:field="*{price}" /><div th:errors="*{price}" style="color: red"class="error-messages"><div></div>'
		let input_category ='<div class="form-group" id="dynamicForm3"><label for="category" class="col-sm-2 control-label">category</label><div class="col-sm-8"><select class="form-control" id="largeCategory2"><option>- parentCategory -</option><option value="1" id="largeCategory2"><span>Men</span></option><option value="4" id="largeCategory2"><span>Electronics</span></option><option value="7" id="largeCategory2"><span>Women</span></option><option value="10" id="largeCategory2"><span>Home</span></option><option value="19" id="largeCategory2"><span>Sports &amp; Outdoors</span></option><option value="22" id="largeCategory2"><span>Vintage &amp; Collectibles</span></option><option value="25" id="largeCategory2"><span>Beauty</span></option><option value="32" id="largeCategory2"><span>Other</span></option><option value="39" id="largeCategory2"><span>Kids</span></option><option value="168" id="largeCategory2"><span>Handmade</span></option></select></div></div><div class="form-group"><label for="category" class="col-sm-2 control-label"></label><div class="col-sm-8"><select class="form-control" id="mediumCategory2"></select></div></div><div class="form-group"><label for="category" class="col-sm-2 control-label"></label><div class="col-sm-8"><select class="form-control" id="smallCategory2" name="category"></select></div></div><div class="form-group"><label for="category" class="col-sm-2 control-label"></label><div class="col-sm-8"><div th:errors="*{category}" style="color: red"class="error-messages"></div></div></div>'
		let input_brand = '<div class="form-group" id="dynamicForm4"><label for="brand" class="col-sm-2 control-label">brand</label><div class="col-sm-8"><input type="text" id="brand" class="form-control" name="brand"th:field="*{brand}" /><div th:errors="*{brand}" style="color: red" class="error-messages"></div></div></div>'
		let input_condition = '<div class="form-group" id="dynamicForm5"><label for="condition" class="col-sm-2 control-label">condition</label><div class="col-sm-8"><label for="condition1" class="radio-inline"> <input type="radio" name="conditionId2" id="condition1" value="1" th:field="*{conditionId}" checked /> 1</label> <label for="condition2" class="radio-inline"> <input type="radio" name="conditionId2" id="condition2" value="2" th:field="*{conditionId}" /> 2</label> <label for="condition3" class="radio-inline"> <input type="radio" name="conditionId2" id="condition3" value="3" th:field="*{conditionId}" /> 3 </label></div></div><div class="form-group"><label for="category" class="col-sm-2 control-label"></label><div class="col-sm-8"><div th:errors="*{conditionId}" style="color: red" class="error-messages"></div></div></div>'
		let input_description = '<div class="form-group" id="dynamicForm6"><label for="description" class="col-sm-2 control-label">description</label><div class="col-sm-8"><textarea name="description" id="description" class="form-control"rows="5" th:field="*{description}"></textarea><div th:errors="*{description}" style="color: red" class="error-messages"></div></div></div></div>'
		let line = '<hr id="dynamicForm7">'
		
		let form_block = document.getElementById("form-block")

		//form_blockの下にコードを追加している.
		form_block.insertAdjacentHTML('afterend', input_description);
		form_block.insertAdjacentHTML('afterend', input_condition);
		form_block.insertAdjacentHTML('afterend', input_brand);
		form_block.insertAdjacentHTML('afterend', input_category);
		form_block.insertAdjacentHTML('afterend', input_price);
		form_block.insertAdjacentHTML('afterend', input_name);
		form_block.insertAdjacentHTML('afterend', line);
		
		//デフォルトでは表示させないように指定.
		document.getElementById("mediumCategory2").style.display = "none";
		document.getElementById("smallCategory2").style.display = "none";
	});

	$("#btnDel").val("deleate").css({ "margin-bottom": "25px", "margin-top": "10px" }).on('click', function() {
		console.log("削除ボタンが押されました。")

		// 要素の削除
		$("#dynamicForm1").remove();
		$("#dynamicForm2").remove();
		$("#dynamicForm3").remove();
		$("#dynamicForm4").remove();
		$("#dynamicForm5").remove();
		$("#dynamicForm6").remove();
		$("#dynamicForm7").remove();

	});
});