package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.form.InsertItemForm;
import com.example.demo.service.InsertItemService;
import com.example.demo.service.ShowItemListService;

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
	public String toInsert(InsertItemForm insertItemForm,Model model) {
		List<Category> largeCategoryList = showItemListService.showLargeCategoryList();
		model.addAttribute("largeCategoryList", largeCategoryList); // 大カテゴリのリストを格納.
		return "add";
	}
	
	@PostMapping("/insert")
	public String insert(InsertItemForm insertItemForm,Model model) {
		Item item = new Item();
		BeanUtils.copyProperties(insertItemForm, item);
		item.setShipping(0);
		insertItemService.insertItem(item);
		return toInsert(insertItemForm,model);
	}
	
}
