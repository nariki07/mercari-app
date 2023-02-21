package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.config.Condition;
import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.form.InsertItemFormList;
import com.example.demo.service.InsertItemService;
import com.example.demo.service.ShowItemListService;

import dto.ItemData;

/**
 * 商品登録を行うコントローラー.
 * 
 * @author moriharanariki
 *
 */
@Controller
@RequestMapping("/insertItem")
public class InsertItemController {

	@Autowired
	private ShowItemListService showItemListService;

	@Autowired
	private InsertItemService insertItemService;

	@GetMapping("/")
	public String toInsert(InsertItemFormList insertItemFormList, Model model) {
		List<Category> largeCategoryList = showItemListService.showLargeCategoryList();
		// 大カテゴリのリストを格納.
		model.addAttribute("largeCategoryList", largeCategoryList); 
		//Conditionクラスをmodelに格納.
		model.addAttribute("conditionList",Condition.values());
		
		// ここからフォームを表示させるための処理.
		// itemFormListがnullの場合のみ処理を行う.
		if (insertItemFormList.getItemFormList() == null) {
			insertItemFormList = new InsertItemFormList();
			List<ItemData> insertItemFormList2 = new ArrayList<>();
			insertItemFormList.setItemFormList(insertItemFormList2);
			ItemData itemData = new ItemData();
			insertItemFormList.getItemFormList().add(itemData);
			model.addAttribute("insertItemFormList", insertItemFormList);
		}
		return "add";
	}

	@PostMapping("/insert")
	public String insert(@Validated InsertItemFormList insertItemFormList, BindingResult result, Model model) {
		System.out.println(insertItemFormList);
		
		if (result.hasErrors()) {
			return toInsert(insertItemFormList, model);
		}

		// 要素の数だけinsert処理を行う。
		for (ItemData itemData : insertItemFormList.getItemFormList()) {
			Item item = new Item();
			item.setName(itemData.getName());
			item.setPrice(Double.parseDouble(itemData.getPrice()));
			item.setCategory(Integer.parseInt(itemData.getCategory()));
			item.setBrand(itemData.getBrand());
			item.setConditionId(Integer.parseInt(itemData.getConditionId()));
			item.setDescription(itemData.getDescription());
			item.setShipping(0);
			insertItemService.insertItem(item);
		}

		return "redirect:/insertItem/";

	}

}
