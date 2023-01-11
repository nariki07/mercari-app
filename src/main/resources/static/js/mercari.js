"use strict";

// const largeCategory = document.querySelector("#largeCategory");
// const mediumCategory = document.querySelector("#mediumCategory");
// const smallCategory = document.querySelector("#smallCategory");

document.getElementById("mediumCategory").style.display = "none";
document.getElementById("smallCategory").style.display = "none";

$(function() {
	$(document).on("change","#largeCategory", function() {
		console.log("大カテゴリのセレクトボックスの変更を行いました.");
		let hostUrl = "http://localhost:8080/mercari-202210/largeCategory";
		let largeCategoryId = $("#largeCategory").val();
		document.getElementById("smallCategory").style.display = "none";
		console.log("大カテゴリID：" + largeCategoryId);
		$.ajax({
			url: hostUrl, //リクエスト先のURL
			type: "POST",
			data: {
				largeCategoryId: largeCategoryId,
			}, //リクエストパラメーター
			datatype: "json", //レスポンスデータの種類
			async: "true", //trueは非同期処理
		}).done(function(data) {
			const mediumCategory = document.getElementById("mediumCategory") //中カテゴリのセレクトボックス.
			mediumCategory.style.display = "inline-block"
			$('#mediumCategory > option').remove();
			const defaultOption1 = document.createElement("option")
			defaultOption1.text = "-childCategory-"
			mediumCategory.appendChild(defaultOption1)
			for (let i = 0; i < data.mediumCategoryList.length; i++) {
				console.log(data.mediumCategoryList[i].categoryName)
				const option = document.createElement("option")
				option.text = data.mediumCategoryList[i].categoryName
				option.value = data.mediumCategoryList[i].id
				mediumCategory.appendChild(option)
			}
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

$(function() {
	$(document).on("change","#mediumCategory", function() {
		console.log("中カテゴリのセレクトボックスの変更を行いました.");
		let hostUrl = "http://localhost:8080/mercari-202210/mediumCategory";
		let mediumCategoryId = $("#mediumCategory").val();
		console.log("中カテゴリID：" + mediumCategoryId);
		$.ajax({
			url: hostUrl, //リクエスト先のURL
			type: "POST",
			data: {
				mediumCategoryId: mediumCategoryId,
			}, //リクエストパラメーター
			datatype: "json", //レスポンスデータの種類
			async: "true", //trueは非同期処理
		}).done(function(data) {
			const smallCategory = document.getElementById("smallCategory") //小カテゴリのセレクトボックス.
			smallCategory.style.display = "inline-block"
			$('#smallCategory > option').remove();
			const defaultOption2 = document.createElement("option")
			defaultOption2.text = "-grandChildCategory-"
			smallCategory.appendChild(defaultOption2)
			for (let i = 0; i < data.smallCategoryList.length; i++) {
				console.log(data.smallCategoryList[i].categoryName)
				const option = document.createElement("option")
				option.text = data.smallCategoryList[i].categoryName
				option.value = data.smallCategoryList[i].id
				smallCategory.appendChild(option)
			}
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

//ここからが動的に作成したフォームを動作させるコード.
//idが静的に作成したセレクトボックスと同じだと正常に動作しなかったため追加した.
$(function() {
	$(document).on("change","#largeCategory2", function() {
		console.log("動的に作成した大カテゴリのセレクトボックスの変更を行いました.");
		let hostUrl = "http://localhost:8080/mercari-202210/largeCategory";
		let largeCategoryId = $("#largeCategory2").val();
		document.getElementById("smallCategory2").style.display = "none";
		console.log("大カテゴリID：" + largeCategoryId);
		$.ajax({
			url: hostUrl, //リクエスト先のURL
			type: "POST",
			data: {
				largeCategoryId: largeCategoryId,
			}, //リクエストパラメーター
			datatype: "json", //レスポンスデータの種類
			async: "true", //trueは非同期処理
		}).done(function(data) {
			const mediumCategory = document.getElementById("mediumCategory2") //中カテゴリのセレクトボックス.
			mediumCategory.style.display = "inline-block"
			$('#mediumCategory2 > option').remove();
			const defaultOption1 = document.createElement("option")
			defaultOption1.text = "-childCategory-"
			mediumCategory.appendChild(defaultOption1)
			for (let i = 0; i < data.mediumCategoryList.length; i++) {
				console.log(data.mediumCategoryList[i].categoryName)
				const option = document.createElement("option")
				option.text = data.mediumCategoryList[i].categoryName
				option.value = data.mediumCategoryList[i].id
				mediumCategory.appendChild(option)
			}
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

$(function() {
	$(document).on("change","#mediumCategory2", function() {
		console.log("動的に作成した中カテゴリのセレクトボックスの変更を行いました.");
		let hostUrl = "http://localhost:8080/mercari-202210/mediumCategory";
		let mediumCategoryId = $("#mediumCategory2").val();
		console.log("中カテゴリID：" + mediumCategoryId);
		$.ajax({
			url: hostUrl, //リクエスト先のURL
			type: "POST",
			data: {
				mediumCategoryId: mediumCategoryId,
			}, //リクエストパラメーター
			datatype: "json", //レスポンスデータの種類
			async: "true", //trueは非同期処理
		}).done(function(data) {
			const smallCategory = document.getElementById("smallCategory2") //小カテゴリのセレクトボックス.
			smallCategory.style.display = "inline-block"
			$('#smallCategory2 > option').remove();
			const defaultOption2 = document.createElement("option")
			defaultOption2.text = "-grandChildCategory-"
			smallCategory.appendChild(defaultOption2)
			for (let i = 0; i < data.smallCategoryList.length; i++) {
				console.log(data.smallCategoryList[i].categoryName)
				const option = document.createElement("option")
				option.text = data.smallCategoryList[i].categoryName
				option.value = data.smallCategoryList[i].id
				smallCategory.appendChild(option)
			}
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

