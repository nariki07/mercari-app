package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.form.InsertItemForm;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemEditService;
import com.example.demo.service.ShowItemListService;

@Controller
@RequestMapping("/edit")
public class ItemEditController {
	
	@Autowired
	private ShowItemListService showItemListService;
	
	@Autowired
	private ItemEditService itemEditService;
	
	@Autowired 
	private ItemRepository itemRepository;
	
	@Autowired
	private ShowItemDetailController shoItemDetailController;
	
	@GetMapping("/")
	public String edit(InsertItemForm insertItemForm,Model model,Integer itemId) {
		Item item = itemRepository.load(itemId);
		
		insertItemForm.setName(item.getName());
		insertItemForm.setPrice(String.valueOf(item.getPrice()));
		insertItemForm.setCategory(String.valueOf(item.getCategory()));
		insertItemForm.setBrand(item.getBrand());
		insertItemForm.setConditionId(String.valueOf(item.getConditionId()));
		insertItemForm.setDescription(item.getDescription());
		
		List<Category> largeCategoryList = showItemListService.showLargeCategoryList();
		model.addAttribute("largeCategoryList", largeCategoryList); // 大カテゴリのリストを格納.
		model.addAttribute("item",item);
		return "edit";
	}
	
	@PostMapping("update")
	public String update(@Validated InsertItemForm insertItemForm,BindingResult result,Model model,Integer itemId) {
		if(result.hasErrors()) {
			return edit(insertItemForm,model,itemId);
		}
		
		Item item = new Item();
		System.out.println(insertItemForm.toString());
		BeanUtils.copyProperties(insertItemForm, item);
		item.setConditionId(Integer.parseInt(insertItemForm.getConditionId()));
		item.setCategory(Integer.parseInt(insertItemForm.getCategory()));
		item.setPrice(Double.parseDouble(insertItemForm.getPrice()));
		item.setShipping(0);
		item.setId(itemId);
		itemEditService.update(item);
		return shoItemDetailController.showdetail(itemId, model);
	}
	
}
