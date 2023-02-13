package com.example.demo.form;

import java.io.Serializable;
import java.util.List;

import dto.ItemData;
import jakarta.validation.Valid;

/**
 * 商品情報を受け取るフォームクラス.
 * 
 * @author moriharanariki
 *
 */
public class InsertItemFormList implements Serializable  {
	
	@Valid
	private List<ItemData> itemFormList;

	public List<ItemData> getItemFormList() {
		return itemFormList;
	}

	
	public void setItemFormList(List<ItemData> insertItemFormList) {
		this.itemFormList = insertItemFormList;
	}

	@Override
	public String toString() {
		return "InsertItemForm [insertItemFormList=" + itemFormList + "]";
	}

}
