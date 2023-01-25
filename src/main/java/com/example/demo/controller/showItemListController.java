package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
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

	// 1ページに表示するアイテム数は20個
	private static final int VIEW_SIZE = 20;

	@RequestMapping("/")
	public String showItemList(String name, String brand, Integer category, Model model, Integer page) {

		List<Category> largeCategoryList = showItemListService.showLargeCategoryList();
		model.addAttribute("largeCategoryList", largeCategoryList); // 大カテゴリのリストを格納.

		if (page == null || page == 0) {
			page = 1;
		}

		// 検索した内容を基にアイテムリストを取得します.
		List<Item> itemList = showItemListService.showItemList(name, category, brand);
		model.addAttribute("name", name);
		model.addAttribute("category", category);
		model.addAttribute("brand", brand);

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
