package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品情報を操作するコントローラー.
 * 
 * @author moriharanariki
 *
 */
@Controller
@RequestMapping("/")
public class showItemListController {
	
	@GetMapping("")
	public String showItem() {
		return "list";
	}
}
