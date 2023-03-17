package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ShowItemListService;

@SpringBootTest
public class ShowItemListServiceTest {
	
	@Autowired
	private ShowItemListService shoItemListService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	/*
	 *商品検索のテスト.
	 */
	@Test
	public void showItemListTest() {
		
		//検索ワード
		String name = "MLB"; //商品名
		Integer category = 3; //カテゴリID
		String brand = ""; //ブランド名
		
		List<Item> itemList = itemRepository.findByNameAndCategoryAndBrand(name, category, brand);
		
		assertEquals(2,itemList.size(),"検索結果の数が一致しません。");
		assertEquals("MLB Chicago White Sox Men's Tshirts S",itemList.get(0).getName(),"商品名が異なります。");
		assertEquals("MLB Cincinnati Reds T Shirt Size XL",itemList.get(1).getName(),"商品名が異なります。");
	}
	
	/**
	 * 中カテゴリに紐づく小カテゴリリストを取得するテスト.
	 */
	@Test
	public void showIdSmallCategoryList() {
		
		Integer parentId = 2;
		List<Category> smallCategoryList = categoryRepository.findByParentIdSmallCategory(parentId);
		
		assertEquals(9,smallCategoryList.size(),"検索結果の数が一致しません。");
		assertEquals("T-shirts",smallCategoryList.get(0).getCategoryName());
	}
	
	
}
