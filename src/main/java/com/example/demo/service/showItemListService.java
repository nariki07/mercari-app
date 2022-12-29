package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;

/**
 * 商品情報を操作するサービスクラス.
 * 
 * @author moriharanariki
 *
 */
public class showItemListService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	
	/**
	 * 商品情報を取得します.
	 * 
	 * @param name 商品名
	 * @param category　カテゴリID
	 * @param brand ブランド名
	 * @return
	 */
	public List<Item> showItemList(String name, Integer category, String brand){
		List<Item> itemList = itemRepository.findByNameAndCategoryAndBrand(name, category, brand);
		return itemList;
	}
	
}
