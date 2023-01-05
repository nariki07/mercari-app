package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Item;


/**
 * itemテーブルを操作するリポジトリ.
 * 
 * @author moriharanariki
 *
 */
@Repository
public class ItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setConditionId(rs.getInt("condition"));
		item.setCategory(rs.getInt("category"));
		item.setBrand(rs.getString("brand"));
		item.setPrice(rs.getDouble("price"));
		item.setShipping(rs.getInt("shipping"));
		item.setDescription(rs.getString("description"));
		return item;
	};
	
	/**
	 * 商品名とカテゴリIDとブランド名で検索を行う.
	 * 
	 * @param name 商品名
	 * @param category カテゴリ
	 * @param brand ブランド名
	 * @return　商品情報一覧
	 */
	public List<Item> findByNameAndCategoryAndBrand(String name,Integer category,String brand){
		String sql = "SELECT id,name,condition,category,brand,price,shipping,description FROM items " 
				+ "WHERE name LIKE :itemSerch AND category = :category AND brand LIKE :brandSerch;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("itemSerch", "%" + name + "%").addValue("category", category).addValue("brandSerch", "%" + brand + "%");

		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);

		return itemList;
		
	}
	
	/**
	 * 商品の登録を行います.
	 * 
	 * @param item 商品情報
	 */
	public void insert(Item item) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		String sql = "insert into items(name,condition,category,brand,price,shipping,description)"
				+ "values(:name,:conditionId,:category,:brand,:price,:shipping,:description);";
		template.update(sql, param);
	}
	
	/**
	 * 主キー検索を行います.
	 * 
	 * @param itemId 商品ID
	 * @return 商品情報
	 */
	public Item load(Integer itemId) {
		String sql = "SELECT id,name,condition,category,brand,price,shipping,description FROM items WHERE id =:itemId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("itemId", itemId);
		Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
		return item;
	}
	
	/**
	 * 商品情報の更新を行う.
	 * 
	 * @param item　商品情報
	 */
	public void update(Item item) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);

		String updateSql = "UPDATE items SET name=:name,condition=:conditionId,category=:category,brand=:brand,price=:price,"
				+ " shipping=:shipping,description=:description WHERE id=:id";
		template.update(updateSql, param);
	}
}
