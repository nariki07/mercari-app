package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Category;

/**
 * categoryテーブルを操作するリポジトリ.
 * 
 * @author moriharanariki
 *
 */
@Repository
public class CategoryRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Category> CATEGORY_ROW_MAPPER = (rs, i) -> {
		Category category = new Category();
		category.setId(rs.getInt("id"));
		category.setParentId(rs.getInt("parent"));
		category.setCategoryName(rs.getString("name"));
		category.setNameAll(rs.getString("name_all"));
		return category;
	};

	/**
	 * カテゴリ情報の挿入を行う.
	 * 
	 * @param category カテゴリ名
	 * @return　カテゴリ情報
	 */
	public Category insert(Category category) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(category);
		if (category.getId() == null) {
			String sql = "insert into category(parent,name,name_all)" + "values(:parentId,:categoryName,:nameAll);";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			String[] keyColumnNames = { "id" };
			template.update(sql, param, keyHolder, keyColumnNames);
			category.setId(keyHolder.getKey().intValue());
		} else {
			String sql = "insert into category(parent,name,name_all)" + "values(:parentId,:categoryName,:nameAll);";
			template.update(sql, param);
		}
		return category;
	}

	/**
	 * 大カテゴリの名前検索を行う.
	 * 
	 * @param categoryName
	 * @return カテゴリ情報
	 */
	public Category findByNameLargeCategory(String categoryName) {
		String sql = "SELECT id,parent,name,name_all FROM category WHERE name = :categoryName AND parent IS NULL AND name_all IS NULL";
		SqlParameterSource param = new MapSqlParameterSource().addValue("categoryName", categoryName);
		List<Category> categoryList = template.query(sql, param, CATEGORY_ROW_MAPPER);
		if (categoryList.size() == 0) {
			return null;
		}
		return categoryList.get(0);
	}
	
	/**
	 * 大カテゴリの全件検索を行う.
	 * 
	 * @return カテゴリリスト
	 */
	public List<Category> findByLargeCategory(){
		String sql = "SELECT id,parent,name,name_all FROM category WHERE parent IS NULL AND name_all IS NULL";
		List<Category> categoryList = template.query(sql,CATEGORY_ROW_MAPPER);
		return categoryList;
	}

	/**
	 * 中カテゴリの名前検索を行う.
	 * 
	 * @param categoryName カテゴリ名
	 * @return　カテゴリリスト
	 */
	public List<Category> allFindByNameMidiumCategory(String categoryName) {
		String sql = "SELECT id,parent,name,name_all FROM category WHERE name = :categoryName AND parent IS NOT NULL AND name_all IS NULL;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("categoryName", categoryName);
		List<Category> categoryList = template.query(sql, param, CATEGORY_ROW_MAPPER);
		if (categoryList.size() == 0) {
			return null;
		}
		return categoryList;
	}
	
	/**
	 * 中カテゴリの検索を行う.
	 * 
	 * @return カテゴリリスト
	 */
	public List<Category> findByMediumCategory(){
		String sql = "SELECT id,parent,name,name_all FROM category WHERE parent IS NOT NULL AND name_all IS NULL";
		List<Category> categoryList = template.query(sql,CATEGORY_ROW_MAPPER);
		return categoryList;
	}
	
	/**
	 * 親IDで中カテゴリの検索を行います.
	 * 
	 * @param parentId　親ID
	 * @return
	 */
	public List<Category> findByParentIdMediumCategory(Integer parentId){
		String sql = "SELECT id,parent,name,name_all FROM category WHERE parent = :parentId AND name_all IS NULL";
		SqlParameterSource param = new MapSqlParameterSource().addValue("parentId", parentId);
		List<Category> categoryList = template.query(sql,param,CATEGORY_ROW_MAPPER);
		return categoryList;
	}

	/**
	 * 小カテゴリをname_allで検索を行う.
	 * 
	 * @param nameAll
	 * @return
	 */
	public Category findByNameAll(String nameAll) {
		String sql = "SELECT id,parent,name,name_all FROM category WHERE name_all = :nameAll AND parent IS NOT NULL AND name_all IS NOT NULL;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("nameAll", nameAll);
		Category category = template.queryForObject(sql, param, CATEGORY_ROW_MAPPER);
		return category;
	}

	/**
	 * 小カテゴリをカテゴリ名で検索を行う.
	 * 
	 * @param categoryName カテゴリ名
	 * @return カテゴリリスト
	 */
	public List<Category> allFindByNameSmallCategory(String categoryName) {
		String sql = "SELECT id,parent,name,name_all FROM category WHERE name = :categoryName AND parent IS NOT NULL AND name_all IS NOT NULL;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("categoryName", categoryName);
		List<Category> categoryList = template.query(sql, param, CATEGORY_ROW_MAPPER);
		if (categoryList.size() == 0) {
			return null;
		}
		return categoryList;
	}		
	
	/**
	 * 小カテゴリの全件検索を行う.
	 * 
	 * @return カテゴリリスト
	 */
	public List<Category> findBySmallCategory(){
		String sql = "SELECT id,parent,name,name_all FROM category WHERE parent IS NOT NULL AND name_all IS NOT NULL";
		List<Category> categoryList = template.query(sql,CATEGORY_ROW_MAPPER);
		return categoryList;
	}
	
	/**
	 * 親IDで小カテゴリの検索を行います.
	 * 
	 * @param parentId　親
	 * @return
	 */
	public List<Category> findByParentIdSmallCategory(Integer parentId){
		String sql = "SELECT id,parent,name,name_all FROM category WHERE parent = :parentId AND name_all IS NOT NULL";
		SqlParameterSource param = new MapSqlParameterSource().addValue("parentId", parentId);
		List<Category> categoryList = template.query(sql,param,CATEGORY_ROW_MAPPER);
		return categoryList;
	}
	

}
