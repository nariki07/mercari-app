//package com.example.demo.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.example.demo.domain.Category;
//import com.example.demo.domain.Item;
//import com.example.demo.form.InsertItemForm;
//import com.example.demo.service.InsertItemService;
//import com.example.demo.service.ShowItemListService;
//
///**
// * 商品登録を行うコントローラー.
// * 
// * @author moriharanariki
// *
// */
//@Controller
//@RequestMapping("/insertItem")
//public class InsertItemController {
//	
//	@Autowired
//	private ShowItemListService showItemListService;
//	
//	@Autowired
//	private InsertItemService insertItemService; 
//	
//	
//	@GetMapping("/")
//	public String toInsert(InsertItemForm insertItemForm,Model model) {
//		List<Category> largeCategoryList = showItemListService.showLargeCategoryList();
//		model.addAttribute("largeCategoryList", largeCategoryList); // 大カテゴリのリストを格納.
//		return "add";
//	}
//	
//	
//	@PostMapping("/insert")
//	public String insert(@Validated InsertItemForm insertItemForm,BindingResult result,Model model,String conditionId2) {
//		List<InsertItemForm> insertItemFormList = new ArrayList<>();
//		
//		if(result.hasErrors()) {
//			return toInsert(insertItemForm,model);
//		}
//
//		//,区切りでパラメーターが送られてくるためsplit()で配列に格納.
//		String[] name = insertItemForm.getName().split(",",0);
//		String[] price = insertItemForm.getPrice().split(",",0);
//		String[] category = insertItemForm.getCategory().split(",",0);
//		String[] brand = insertItemForm.getBrand().split(",",0);
//		String[] conditionId = new String[5];  
//		conditionId[0] = insertItemForm.getConditionId();
//		conditionId[1] = conditionId2;
//		String[] description = insertItemForm.getDescription().split(",",0);
//		//要素の数だけinsert処理を行う。
//		for(int i=0; i < name.length; i++) {
//			Item item = new Item();
//			item.setName(name[i]);
//			item.setPrice(Double.parseDouble(price[i]));
//			item.setCategory(Integer.parseInt(category[i]));
//			item.setBrand(brand[i]);
//			item.setConditionId(Integer.parseInt(conditionId[i]));
//			item.setDescription(description[i]);
//			item.setShipping(0);
//			insertItemService.insertItem(item);
//		}
//		
//		return "redirect:/insertItem/";
//	}
//	
//}
