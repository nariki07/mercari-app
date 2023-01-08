package com.example.demo.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.User;
import com.example.demo.form.InsertUserForm;
import com.example.demo.service.InsertUserService;

/**
 * ユーザー情報を登録するコントローラー.
 * 
 * @author moriharanariki
 *
 */
@Controller
@RequestMapping("")
public class InsertUserController {
	
	@Autowired
	private InsertUserService insertUserService; 
	
	/**
	 * ユーザー登録画面に遷移させる.
	 * 
	 * @return ログイン画面
	 */
	@GetMapping("/toInsert")
	public String toInsert(InsertUserForm insertUserForm) {
		return "register";
	}
	
	/**
	 * ユーザー登録を行います.
	 * 
	 * @return ログイン画面
	 */
	@PostMapping("/insert")
	public String insert(@Validated InsertUserForm insertUserForm,BindingResult result) {
		
		if(result.hasErrors()) {
			return toInsert(insertUserForm);
		}
		
		User user = new User();
		BeanUtils.copyProperties(insertUserForm, user);
		user.setAuthority(null); //とりあえずnullにしておく.
		insertUserService.insertUser(user);
		
		return "redirect:/login";
	}
}
