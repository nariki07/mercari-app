package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.service.ShowItemDetailService;

@Controller
@RequestMapping("detail")
public class ShowItemDetailController {
	
	@Autowired
	private ShowItemDetailService showItemDetailService;
	
	
	@GetMapping("/")
	public String showdetail(Integer itemId,Model model) {
		Item item = showItemDetailService.showDetail(itemId);
		Category category = showItemDetailService.showDetailCategory(item.getCategory());
		model.addAttribute("item",item);
		model.addAttribute("categoryNameAll",category.getNameAll());
		return "detail";
	}
}
