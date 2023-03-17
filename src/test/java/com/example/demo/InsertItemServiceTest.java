package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.InsertItemService;

@SpringBootTest
public class InsertItemServiceTest {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private InsertItemService insertItemService;
	
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 商品登録を行うテスト
	 */
	@Test
	public void insertItemTest() {
		Item item = new Item("iphone20",2,3,"apple",100.0,0,"やや傷があります。");
		insertItemService.insertItem(item);
		
		List<Item> itemList = itemRepository.findByName("iphone20");
		assertEquals("iphone20",itemList.get(0).getName(),"商品名が一致しません。");
	}
	
	@AfterEach
    public void setupAfterTransaction() {
		jdbc.execute("DELETE FROM items WHERE name = 'iphone20' ");
    }
	
}
