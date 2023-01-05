package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping("/")
	public String showItem(Model model) {
		List<Category> largeCategoryList = showItemListService.showLargeCategoryList();
		model.addAttribute("largeCategoryList", largeCategoryList); // 大カテゴリのリストを格納.
		return "list";
	}

	@PostMapping("/serch")
	public String showItemList(String name, String brand, Integer category,Model model) {
		
		//検索した内容を基にアイテムリストを取得します.
		List<Item> itemList = showItemListService.showItemList(name, category, brand);
		itemList.forEach(item -> System.out.println(item));
		model.addAttribute("itemList",itemList);
		
		return showItem(model);
	}
	
	@ResponseBody
	@PostMapping("/largeCategory")
	public Map<String,List<Category>> largeCategory(Integer largeCategoryId){
		Map<String,List<Category>> map = new HashMap<>();
		List<Category> mediumCategoryList = showItemListService.showParentIdMediumCategoryList(largeCategoryId);
		map.put("mediumCategoryList",mediumCategoryList);
		return map;
	}
	
	@ResponseBody
	@PostMapping("/mediumCategory")
	public Map<String,List<Category>> mediumCategory(Integer mediumCategoryId){
		Map<String,List<Category>> map = new HashMap<>();
		List<Category> smallCategoryList = showItemListService.showParentIdSmallCategoryList(mediumCategoryId);
		map.put("smallCategoryList",smallCategoryList);
		return map;
	}
}
