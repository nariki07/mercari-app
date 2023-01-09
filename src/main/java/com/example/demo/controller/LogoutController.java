package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

/**
 * ログアウト機能を制御するコントローラー.
 * 
 * @author  moriharanariki
 *
 */
@Controller
@RequestMapping("")
public class LogoutController {
	
	
	/**
	 * ログアウトします.
	 * 
	 * @return ログイン画面
	 */
	@GetMapping("/logout")
	public String logout() {
		return "login";
	}

}
