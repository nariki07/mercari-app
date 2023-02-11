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
		let input_name = '<div class="form-group" id="dynamicForm1' + n + '"><h4>' + b + '.</h4><label for="inputName" class="col-sm-2 control-label">name</label><div class="col-sm-8"><input type="text" class="form-control" id="inputName" name="itemFormList[' + n + '].name" th:field="*{itemFormList[' + n + '].name}" /><div th:errors="*{itemFormList[' + n + '].name}" style="color: red" class="error-messages"></div></div></div>'
		let input_price = '<div class="form-group" id="dynamicForm2' + n + '"><label for="price" class="col-sm-2 control-label">price</label><div class="col-sm-8"><input type="text" class="form-control" id="price" name="itemFormList[' + n + '].price"　th:field="*{itemFormList[' + n + '].price}" /><div th:errors="*{itemFormList[' + n + '].price}" style="color: red"class="error-messages"><div></div>'
		let input_category = '<div class="form-group" id="dynamicForm3' + n + '"><label for="category" class="col-sm-2 control-label">category</label><div class="col-sm-8"><select class="form-control" id="largeCategory' + n + '"><option>- parentCategory -</option><option value="1" id="largeCategory' + n + '"><span>Men</span></option><option value="4" id="largeCategory' + n + '"><span>Electronics</span></option><option value="7" id="largeCategory' + n + '"><span>Women</span></option><option value="10" id="largeCategory' + n + '"><span>Home</span></option><option value="19" id="largeCategory' + n + '"><span>Sports &amp; Outdoors</span></option><option value="22" id="largeCategory' + n + '"><span>Vintage &amp; Collectibles</span></option><option value="25" id="largeCategory' + n + '"><span>Beauty</span></option><option value="32" id="largeCategory' + n + '"><span>Other</span></option><option value="39" id="largeCategory' + n + '"><span>Kids</span></option><option value="168" id="largeCategory' + n + '"><span>Handmade</span></option></select></div></div><div class="form-group"><label for="category" class="col-sm-2 control-label"></label><div class="col-sm-8"><select class="form-control" id="mediumCategory' + n + '"></select></div></div><div class="form-group"><label for="category" class="col-sm-2 control-label"></label><div class="col-sm-8"><select class="form-control" id="smallCategory' + n + '" name="itemFormList[' + n + '].category" th:field="*{itemFormList[' + n + '].category}"></select></div></div><div class="form-group"><label for="category" class="col-sm-2 control-label"></label><div class="col-sm-8"><div th:errors="*{itemFormList[' + n + '].category}" style="color: red"class="error-messages"></div></div></div>';
		let input_brand = '<div class="form-group" id="dynamicForm4' + n + '"><label for="brand" class="col-sm-2 control-label">brand</label><div class="col-sm-8"><input type="text" id="brand" class="form-control" name="itemFormList[' + n + '].brand" th:field="*{itemFormList[' + n + '].brand}" /><div th:errors="*{itemFormList[' + n + '].brand}" style="color: red" class="error-messages"></div></div></div>'
		let input_condition = '<div class="form-group" id="dynamicForm5' + n + '"><label for="condition" class="col-sm-2 control-label">condition</label><div class="col-sm-8"><label for="condition1" class="radio-inline"> <input type="radio" name="itemFormList[' + n + '].conditionId" id="condition1" value="1" th:field="*{itemFormList[' + n + '].conditionId}" checked /> 1</label> <label for="condition2" class="radio-inline"> <input type="radio" name="itemFormList[' + n + '].conditionId" id="condition2" value="2" th:field="*{insertItemFormList[' + n + '].conditionId}" /> 2</label> <label for="condition3" class="radio-inline"> <input type="radio" name="itemFormList[' + n + '].conditionId" id="condition3" value="3" th:field="*{itemFormList[' + n + '].conditionId}" /> 3 </label></div></div><div class="form-group"><label for="category" class="col-sm-2 control-label"></label><div class="col-sm-8"><div th:errors="*{itemFormList[' + n + '].conditionId}" style="color: red" class="error-messages"></div></div></div>'
		let input_description = '<div class="form-group" id="dynamicForm6' + n + '"><label for="description" class="col-sm-2 control-label">description</label><div class="col-sm-8"><textarea name="itemFormList[' + n + '].description" id="description" class="form-control"rows="5" th:field="*{itemFormList[' + n + '].description}"></textarea><div th:errors="*{itemFormList[' + n + '].description}" style="color: red" class="error-messages"></div></div></div></div>'
		let line = '<hr id="dynamicForm7' + n + '">'
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
		$("#dynamicForm1" + n).remove();
		$("#dynamicForm2" + n).remove();
		$("#dynamicForm3" + n).remove();
		$("#dynamicForm4" + n).remove();
		$("#dynamicForm5" + n).remove();
		$("#dynamicForm6" + n).remove();
		$("#dynamicForm7" + n).remove();

		//削除された場合は番号も減らす.index番号や表示用番号の整合性を保つため.
		if (!(b === 1)) {
			b--
		}

		if (!(n === 0)) {
			n--
		}
	});
});

$(function() {
	$(document).on("click", "#submit", function() {
		console.log("送信ボタンが押されました");
		let hostUrl = "http://localhost:8080/mercari-202210/insertItem/insertTest";
		let form = $("form").serializeArray()
		console.log(JSON.stringify(form))
		$.ajax({
			url: hostUrl, //リクエスト先のURL
			type: "POST",
			data: {
				insertItemFormList: JSON.stringify(form),
			}, //リクエストパラメーター
			datatype: "json", //レスポンスデータの種類
			async: "true", //trueは非同期処理
		}).done(function(data) {
			//成功した時の処理.
			console.log(data)
		})
			.fail(function(XMLHttpRequest, textStatus, errorThrown) {
				console.log("XMLHttpRequest:" + XMLHttpRequest.status);
				console.log("textStatus:" + textStatus);
				console.log("errorThrown:" + errorThrown.message);
			})
			.always(function(data) {
				//通信成功・失敗問わず行う処理を記載(dataにはレスポンス情報が含まれる。)
			});
	});
});