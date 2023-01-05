package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Item;
import com.example.demo.service.ShowItemDetailService;

@Controller
@RequestMapping("detail")
public class ShowItemDetailController {
	
	@Autowired
	private ShowItemDetailService showItemDetailService;
	
	@GetMapping("/")
	public String showdetail(Integer itemId,Model model) {
		System.out.println(itemId);
		Item item = showItemDetailService.showDetail(itemId);
		model.addAttribute("item",item);
		return "detail";
	}
}
