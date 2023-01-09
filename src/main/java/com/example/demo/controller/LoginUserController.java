package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.LoginUser;
import com.example.demo.domain.User;

@Controller
@RequestMapping("")
public class LoginUserController {
	
	@RequestMapping("/login")
	public String toLogin() {
		return "login";
	}
	
}
