package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;

@Service
@Transactional
public class ItemEditService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 更新を行います.
	 * 
	 * @param item 商品情報
	 */
	public void update(Item item) {
		itemRepository.update(item);
	}
}
