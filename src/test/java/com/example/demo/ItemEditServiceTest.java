package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.domain.Item;
import com.example.demo.form.InsertItemForm;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemEditService;

@SpringBootTest
public class ItemEditServiceTest {
	
	@Autowired
	private ItemEditService itemEditService;
	
	@Autowired
	private ItemRepository itemRepository;
	
	private InsertItemForm insertItemform;
	
	
	@BeforeEach
	public void setupItemForm() {
		insertItemform = new InsertItemForm();
		insertItemform.setName("iphone23");
		insertItemform.setPrice("100.0");
		insertItemform.setCategory("3");
		insertItemform.setBrand("iphone");
		insertItemform.setConditionId("2");
		insertItemform.setDescription("傷があります。");
	}
	
	/**
	 * 商品情報の更新を行います.
	 */
	@Test
	public void updateTest() {		
		
		Item item = new Item();
		Integer itemId = 1482552;
		
		BeanUtils.copyProperties(insertItemform, item);
		item.setConditionId(Integer.parseInt(insertItemform.getConditionId()));
		item.setCategory(Integer.parseInt(insertItemform.getCategory()));
		item.setPrice(Double.parseDouble(insertItemform.getPrice()));
		item.setShipping(0);
		item.setId(itemId); 
		itemEditService.update(item);
		
		List<Item> itemList = itemRepository.findByName("iphone23");
		assertEquals("iphone23",itemList.get(0).getName(),"商品名が一致しません。");
		assertEquals("傷があります。",itemList.get(0).getDescription(),"商品名が一致しません。");
	}
}














