package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;

@Service
@Transactional
public class ShowItemDetailService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public Item showDetail(Integer itemId) {
		Item item = itemRepository.load(itemId);
		return item;
	}
}
