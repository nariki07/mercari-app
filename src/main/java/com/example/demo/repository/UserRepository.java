package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

/**
 * usersテーブルを操作するリポジトリ.
 * 
 * @author moriharanariki
 *
 */
@Repository
public class UserRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * Userオブジェクトを生成するローマッパー
	 */
	private static final RowMapper<User> USER_ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);
	
	/**
	 * User情報を挿入します.
	 * 
	 * @param user User情報
	 */
	public void insert(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		String sql = "insert into users(name,password,authority) values(:name,:password,:authority);";
		template.update(sql, param);
	}
	
	/**
	 * ユーザー名で検索を行います.
	 * 
	 * @param username ユーザー名
	 * @return ユーザー情報
	 */
	public User findByUserName(String username) {
		String sql = "SELECT id, name, password, authority FROM users WHERE name=:username;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("username", username);
		User user = template.queryForObject(sql, param, USER_ROW_MAPPER);
		return user;
	}
	
	
}
