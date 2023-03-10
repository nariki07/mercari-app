package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;

/**
 * 商品情報を登録するサービスクラス.
 * 
 * @author moriharanariki
 *
 */
@Service
@Transactional
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
