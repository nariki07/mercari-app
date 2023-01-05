package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

/**
 * 商品詳細のサービスクラス.
 * 
 * @author moriharanariki
 *
 */
@Service
@Transactional
public class ShowItemDetailService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	/**
	 * 商品情報を取得します.
	 * 
	 * @param itemId 商品ID
	 * @return 商品情報
	 */
	public Item showDetail(Integer itemId) {
		Item item = itemRepository.load(itemId);
		return item;
	}
	
	public Category showDetailCategory(Integer id) {
		Category category = categoryRepository.load(id);
		return category;
	}
}
