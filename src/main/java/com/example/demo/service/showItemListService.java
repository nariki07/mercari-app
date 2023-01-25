package com.example.demo.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
	 * 商品名で商品情報を取得します.
	 * 
	 * @param name　商品名
	 * @return 商品情報一覧
	 */
	public List<Item> showItemList2(String name){
		List<Item> itemList = itemRepository.findByName(name);
		return itemList;
	}
	
	/**
	 * ブランド名で商品情報を取得します.
	 * 
	 * @param brand
	 * @return
	 */
	public List<Item> showItemList3(String brand){
		List<Item> itemList = itemRepository.findByBrand(brand);
		return itemList;
	}
	
	/**
	 * 商品名とブランド名で商品情報を取得します.
	 * 
	 * @param name 商品名
	 * @param brand　ブランド名
	 * @return　商品情報一覧
	 */
	public List<Item> showItemList4(String name ,String brand){
		List<Item> itemList = itemRepository.findByNameAndBrand(name, brand);
		return itemList;
	}
	
	/**
	 *全件検索を行うメソッド. 
	 * 
	 * @return 商品一覧
	 */
	public List<Item> showAllItemList(){
		List<Item> itemList = itemRepository.findAll();
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
	
	/**
	 * ページング用メソッド.
	 * @param page 表示させたいページ数
	 * @param size １ページに表示させる従業員数.
	 * @param itemList 商品情報一覧.
	 * @return １ページに表示されるサイズ分の商品一覧情報
	 */
	public Page<Item> showItemListPaging(Integer page,Integer size,List<Item> itemList){
		// 表示させたいページ数を-1しなければうまく動かない
	    page--;
	    // どの商品から表示させるかと言うカウント値
	    int startItemCount = page * size;
	    // 絞り込んだ後の商品リストが入る変数
	    List<Item> list;

	    if (itemList.size() < startItemCount) {
	    	// (ありえないが)もし表示させたい商品カウントがサイズよりも大きい場合は空のリストを返す
	        list = Collections.emptyList();
	    } else {
	    	// 該当ページに表示させる商品一覧を作成
	        int toIndex = Math.min(startItemCount + size, itemList.size());
	        list = itemList.subList(startItemCount, toIndex);
	    }

	    // 上記で作成した該当ページに表示させる商品一覧をページングできる形に変換して返す
	    Page<Item> itemPage
	      = new PageImpl<Item>(list, PageRequest.of(page, size), itemList.size());
	    return itemPage;
	}
	
}
