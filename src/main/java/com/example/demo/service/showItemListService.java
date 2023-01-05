package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

/**
 * 商品情報を操作するサービスクラス.
 * 
 * @author moriharanariki
 *
 */
@Service
@Transactional
public class ShowItemListService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
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
	
	/**
	 * 大カテゴリの情報を取得します.
	 * 
	 * @return
	 */
	public List<Category> showLargeCategoryList(){
		List<Category> largeCategoryList = categoryRepository.findByLargeCategory();
		return largeCategoryList;
	}
	
	/**
	 * 中カテゴリの情報を取得します.
	 * 
	 * @return
	 */
	public List<Category> showMediumCategoryList(){
		List<Category> mediumCategoryList = categoryRepository.findByMediumCategory();
		return mediumCategoryList;
	}
	
	/**
	 * 親IDで検索した中カテゴリ情報を取得します.
	 * 
	 * @param id
	 * @return
	 */
	public List<Category> showParentIdMediumCategoryList(Integer id){
		List<Category> mediumCategoryList = categoryRepository.findByParentIdMediumCategory(id);
		return  mediumCategoryList;
	}
	
	/**
	 * 小カテゴリの情報を取得します.
	 * 
	 * @return
	 */
	public List<Category> showSmallCategoryList(){
		List<Category> smallCategoryList = categoryRepository.findBySmallCategory();
		return smallCategoryList;
	}
	
	/**
	 * 小カテゴリの情報を取得します.
	 * 
	 * @return
	 */
	public List<Category> showParentIdSmallCategoryList(Integer parentId){
		List<Category> smallCategoryList = categoryRepository.findByParentIdSmallCategory(parentId);
		return smallCategoryList;
	}
	
}
