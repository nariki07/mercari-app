package dto;

import java.io.Serializable;

import com.example.demo.validation.ValidateItemType;

@ValidateItemType(fields = { "name", "price", "category", "brand", "conditionId", "description" })
public class ItemData implements Serializable {
	/* 商品名 */
	private String name;
	/* 価格 */
	private String price;
	/* カテゴリ */
	private String category;
	/* ブランド名 */
	private String brand;
	/* 状態 */
	private String conditionId;
	/* 説明 */
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getConditionId() {
		return conditionId;
	}

	public void setConditionId(String conditionId) {
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
		return "ItemData [name=" + name + ", price=" + price + ", category=" + category + ", brand=" + brand
				+ ", conditionId=" + conditionId + ", description=" + description + "]";
	}
}
