package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * ユーザー情報受け取るフォーム.
 * 
 * @author moriharanariki
 *
 */
public class InsertUserForm {
	
	/* ユーザー名 */
	@NotBlank(message = "username entry is required.")
	private String name;
	/* パスワード */
	@Size(min = 8, max = 127, message = "password must be 8 to 127 characters long")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[.?/-])[a-zA-Z0-9.?/-]{8,24}$", message = "Please enter in uppercase letters, lowercase letters, numbers and symbols.")
	private String password;

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

	@Override
	public String toString() {
		return "InsertUserForm [name=" + name + ", password=" + password + "]";
	}
}
