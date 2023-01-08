package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class LoginUserController {
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	/**
	 * ログインします.
	 * 
	 * @param form ユーザー情報格納用フォーム
	 * @param model エラー情報格納用
	 * @return　ログインリンクから遷移されていた場合：商品一覧を表示する。ショッピングカート画面から遷移されていた場合：商品一覧を表示する
	 */
	/*
	 * @PostMapping("/loginUser") public String login(@AuthenticationPrincipal
	 * LoginUser loginUser) { //sessionに入っている仮のユーザーIDでオーダー情報を取得。 Order order =
	 * shopCartService.showCartList((int)session.getAttribute("useId"));
	 * System.out.println(order.toString()); //ログイン情報を取得. User user =
	 * loginUser.getUser(); //ログイン情報を利用してオーダーテーブルを取得. Order order2 =
	 * shopCartService.showCartList(user.getId());
	 * System.out.println(order2.toString());
	 * 
	 * if(order2 != null) { for(OrderItem orderItem : order.getOrderItemList()) {
	 * orderItem.setOrderId(order2.getId()); shopCartService.update(orderItem); } }
	 */
}
