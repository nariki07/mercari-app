package dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class ItemData implements Serializable {
	/* 商品名 */
	@NotBlank(message = "error:may not be empty")
	private String name;
	/* 価格 */
	@NotBlank(message = "error:may not be empty")
	private String price;
	/* カテゴリ */
	@NotBlank(message = "error:may not be empty")
	private String category;
	/* ブランド名 */
	@NotBlank(message = "error:may not be empty")
	private String brand;
	/* 状態 */
	@NotEmpty(message = "error:may not be empty")
	private String conditionId;
	/* 説明 */
	@NotBlank(message = "error:may not be empty")
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
}
