package com.example.demo.domain;

/**
 * ユーザー情報を表すドメインクラス.
 * 
 * @author moriharanariki
 *
 */
public class User {
	/* ID */
	private Integer id;
	/* ユーザー名 */
	private String name;
	/* パスワード */
	private String password;
	/* 権限 */
	private Integer authority;

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", authority=" + authority + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

}
