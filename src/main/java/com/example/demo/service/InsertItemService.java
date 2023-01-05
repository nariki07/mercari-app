package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;

@Service
public class InsertItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 商品の登録を行います.
	 * 
	 * @param item
	 */
	public void insertItem(Item item) {
		itemRepository.insert(item);
	}
}
