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
	private List<ItemData> insertItemFormList;

	public List<ItemData> getInsertItemFormList() {
		return insertItemFormList;
	}

	public void setInsertItemFormList(List<ItemData> insertItemFormList) {
		this.insertItemFormList = insertItemFormList;
	}

	@Override
	public String toString() {
		return "InsertItemForm [insertItemFormList=" + insertItemFormList + "]";
	}

}
