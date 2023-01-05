package com.example.demo.form;

/**
 * 商品情報を受け取るフォームクラス.
 * 
 * @author moriharanariki
 *
 */
public class InsertItemForm {

	/* 商品名 */
	private String name;
	/* 価格 */
	private double price;
	/* カテゴリ */
	private Integer category;
	/* ブランド名 */
	private String brand;
	/* 状態 */
	private Integer conditionId;
	/* 説明 */
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getConditionId() {
		return conditionId;
	}

	public void setConditionId(Integer conditionId) {
		this.conditionId = conditionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "InsertItemForm [name=" + name + ", price=" + price + ", category=" + category + ", brand=" + brand
				+ ", conditionId=" + conditionId + ", description=" + description + "]";
	}

}
