package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.ShowItemListService;

/**
 * 商品情報を操作するコントローラー.
 * 
 * @author moriharanariki
 *
 */
@Controller
@RequestMapping("")
public class ShowItemListController {

	@Autowired
	private ShowItemListService showItemListService;

	@Autowired
	private CategoryRepository categoryRepository;
	// 1ページに表示するアイテム数は20個
	private static final int VIEW_SIZE = 20;

	@RequestMapping("/")
	public String showItemList(String name, String brand, Integer category, Model model, Integer page) {

		if (category != null) {
			Category smallCategory = showItemListService.showIdSmallCategoryList(category);
			// nameallをcategoryに分ける。
			String[] categorys = smallCategory.getNameAll().split("/", 0);
			model.addAttribute("largeCategory", categorys[0]);
			model.addAttribute("mediumCategory", categorys[1]);
			model.addAttribute("smallCategory", categorys[2]);
		}
		
		List<Category> largeCategoryList = showItemListService.showLargeCategoryList();
		model.addAttribute("largeCategoryList", largeCategoryList); // 大カテゴリのリストを格納.

		// pageがnullもしくは0の場合は1をいれる.
		if (page == null || page == 0) {
			page = 1;
		}

		List<Item> itemList = new ArrayList<>();

		if (name == "" && category == null && brand != "") {
			// 商品名のみで検索を行なった場合の処理.
			itemList = showItemListService.showItemList2(name);
		} else if (name == null && category == null && brand != "") {
			// リンクのブランド名から検索をかける.リンクからのパラメーターは""ではなくnullが入るため.
			itemList = showItemListService.showItemList3(brand);
		} else if (name != "" && category == null && brand == "") {
			// ブランド名のみで検索を行なった場合の処理.
			itemList = showItemListService.showItemList3(brand);
		} else if (name != "" && category == null && brand != "") {
			// 商品名とブランド名で検索を行なった場合の処理.
			// 何も検索欄に入力していないと、ここの処理が行われるが動作は変わらないためこのままにしておく.
			itemList = showItemListService.showItemList4(name, brand);
		} else {
			itemList = showItemListService.showItemList(name, category, brand);
		}

		List<Category> categoryList = new ArrayList<>();

		model.addAttribute("name", name); // 検索にかけた名前が入る.
		model.addAttribute("category", category); // 小カテゴリのIDが入る.
		model.addAttribute("brand", brand); // 検索にかけたブランド名が入る.

		// ページング機能追加のためコメントアウト.
		/* model.addAttribute("itemList",itemList); */

		// 表示させたいページ数、ページサイズ、商品リストを渡し１ページに表示させる商品リストを絞り込み.
		Page<Item> itemPage = null;
		itemPage = showItemListService.showItemListPaging(page, VIEW_SIZE, itemList);
		model.addAttribute("itemPage", itemPage);

		if (itemPage.isEmpty() && page != 1) {
			page--;
			Page<Item> itemPage2 = showItemListService.showItemListPaging(page, VIEW_SIZE, itemList);
			model.addAttribute("itemPage", itemPage2);
		}

		// ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
		List<Integer> pageNumbers = calcPageNumbers(model, itemPage);
		model.addAttribute("pageNumbers", pageNumbers);

		return "list";
	}

	/**
	 * ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
	 * 
	 * @param model        モデル
	 * @param employeePage ページング情報
	 */
	private List<Integer> calcPageNumbers(Model model, Page<Item> itemPage) {
		int totalPages = itemPage.getTotalPages();
		List<Integer> pageNumbers = null;
		if (totalPages > 0) {
			pageNumbers = new ArrayList<Integer>();
			for (int i = 1; i <= totalPages; i++) {
				pageNumbers.add(i);
			}
		}
		return pageNumbers;
	}

	@ResponseBody
	@PostMapping("/largeCategory")
	public Map<String, List<Category>> largeCategory(Integer largeCategoryId) {
		Map<String, List<Category>> map = new HashMap<>();
		List<Category> mediumCategoryList = showItemListService.showParentIdMediumCategoryList(largeCategoryId);
		map.put("mediumCategoryList", mediumCategoryList);
		return map;
	}

	@ResponseBody
	@PostMapping("/mediumCategory")
	public Map<String, List<Category>> mediumCategory(Integer mediumCategoryId) {
		Map<String, List<Category>> map = new HashMap<>();
		List<Category> smallCategoryList = showItemListService.showParentIdSmallCategoryList(mediumCategoryId);
		map.put("smallCategoryList", smallCategoryList);
		return map;
	}
}
