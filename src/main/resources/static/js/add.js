"use strict";

$(function() {
	//フォームの数をカウントするための変数.
	let n = 0;
	//番号用
	let b = 1;

	$("#btnAdd").val("add").css({ "margin-bottom": "25px", "margin-top": "10px" }).on('click', function() {
		n++
		b++
		let form_block;

		console.log(n)
		//フォームに追加するコードを記述. 
		let input_name = '<div class="form-group" id="dynamicForm1"><h4>' + b + '.</h4><label for="inputName" class="col-sm-2 control-label">name</label><div class="col-sm-8"><input type="text" class="form-control" id="inputName" name="insertItemFormList[' + n + '].name" th:field="*{insertItemFormList[' + n + '].name}" /><div th:errors="*{insertItemFormList[' + n + '].name}" style="color: red" class="error-messages"></div></div></div>'
		let input_price = '<div class="form-group" id="dynamicForm2"><label for="price" class="col-sm-2 control-label">price</label><div class="col-sm-8"><input type="text" class="form-control" id="price" name="insertItemFormList[' + n + '].price"　th:field="*{insertItemFormList[' + n + '].price}" /><div th:errors="*{insertItemFormList[' + n + '].price}" style="color: red"class="error-messages"><div></div>'
		let input_category = '<div class="form-group" id="dynamicForm3"><label for="category" class="col-sm-2 control-label">category</label><div class="col-sm-8"><select class="form-control" id="largeCategory' + n + '"><option>- parentCategory -</option><option value="1" id="largeCategory' + n + '"><span>Men</span></option><option value="4" id="largeCategory' + n + '"><span>Electronics</span></option><option value="7" id="largeCategory' + n + '"><span>Women</span></option><option value="10" id="largeCategory' + n + '"><span>Home</span></option><option value="19" id="largeCategory' + n + '"><span>Sports &amp; Outdoors</span></option><option value="22" id="largeCategory' + n + '"><span>Vintage &amp; Collectibles</span></option><option value="25" id="largeCategory' + n + '"><span>Beauty</span></option><option value="32" id="largeCategory' + n + '"><span>Other</span></option><option value="39" id="largeCategory' + n + '"><span>Kids</span></option><option value="168" id="largeCategory' + n + '"><span>Handmade</span></option></select></div></div><div class="form-group"><label for="category" class="col-sm-2 control-label"></label><div class="col-sm-8"><select class="form-control" id="mediumCategory' + n + '"></select></div></div><div class="form-group"><label for="category" class="col-sm-2 control-label"></label><div class="col-sm-8"><select class="form-control" id="smallCategory' + n + '" name="insertItemFormList[' + n + '].category" th:field="*{insertItemFormList[' + n + '].category}"></select></div></div><div class="form-group"><label for="category" class="col-sm-2 control-label"></label><div class="col-sm-8"><div th:errors="*{insertItemFormList[' + n + '].category}" style="color: red"class="error-messages"></div></div></div>';
		let input_brand = '<div class="form-group" id="dynamicForm4"><label for="brand" class="col-sm-2 control-label">brand</label><div class="col-sm-8"><input type="text" id="brand" class="form-control" name="insertItemFormList[' + n + '].brand" th:field="*{insertItemFormList[' + n + '].brand}" /><div th:errors="*{insertItemFormList[' + n + '].brand}" style="color: red" class="error-messages"></div></div></div>'
		let input_condition = '<div class="form-group" id="dynamicForm5"><label for="condition" class="col-sm-2 control-label">condition</label><div class="col-sm-8"><label for="condition1" class="radio-inline"> <input type="radio" name="insertItemFormList[' + n + '].conditionId" id="condition1" value="1" th:field="*{insertItemFormList[' + n + '].conditionId}" checked /> 1</label> <label for="condition2" class="radio-inline"> <input type="radio" name="insertItemFormList[' + n + '].conditionId" id="condition2" value="2" th:field="*{insertItemFormList[' + n + '].conditionId}" /> 2</label> <label for="condition3" class="radio-inline"> <input type="radio" name="insertItemFormList[' + n + '].conditionId" id="condition3" value="3" th:field="*{insertItemFormList[' + n + '].conditionId}" /> 3 </label></div></div><div class="form-group"><label for="category" class="col-sm-2 control-label"></label><div class="col-sm-8"><div th:errors="*{insertItemFormList[' + n + '].conditionId}" style="color: red" class="error-messages"></div></div></div>'
		let input_description = '<div class="form-group" id="dynamicForm6"><label for="description" class="col-sm-2 control-label">description</label><div class="col-sm-8"><textarea name="insertItemFormList[' + n + '].description" id="description" class="form-control"rows="5" th:field="*{insertItemFormList[' + n + '].description}"></textarea><div th:errors="*{insertItemFormList[' + n + '].description}" style="color: red" class="error-messages"></div></div></div></div>'
		let line = '<hr id="dynamicForm7">'
		let block = '<div class="form-block" id="form-block' + n + '">'

		if (n === 1) {
			form_block = document.getElementById("form-block")
		} else if (n === 2) {
			form_block = document.getElementById("form-block1")
		} else if (n === 3) {
			form_block = document.getElementById("form-block2")
		} else if (n === 4) {
			form_block = document.getElementById("form-block3")
		} else if (n === 5) {
			form_block = document.getElementById("form-block4")
		}

		console.log(form_block)

		//form_blockの下にコードを追加している.
		form_block.insertAdjacentHTML('afterend', block);
		form_block.insertAdjacentHTML('afterend', input_description);
		form_block.insertAdjacentHTML('afterend', input_condition);
		form_block.insertAdjacentHTML('afterend', input_brand);
		form_block.insertAdjacentHTML('afterend', input_category);
		form_block.insertAdjacentHTML('afterend', input_price);
		form_block.insertAdjacentHTML('afterend', input_name);
		form_block.insertAdjacentHTML('afterend', line);

		//デフォルトではカテゴリを表示させないように指定.
		document.getElementById("mediumCategory" + n).style.display = "none";
		document.getElementById("smallCategory" + n).style.display = "none";
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
		$("#dynamicForm7").remove();

		//削除された場合は番号も減らす.index番号や表示用番号の整合性を保つため.
		if (!(b === 1)) {
			b--
		}
		
		if(!(n === 0)){
			n--
		}
	});
});