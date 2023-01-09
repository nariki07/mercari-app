package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.LoginUser;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

/**
 * ログイン後のユーザ情報に権限情報を付与するサービスクラス.
 * 
 * @author moriharanariki
 *
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username:" + username);
		User user = userRepository.findByUserName(username); 
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		
		//権限の数値によって付与する権限を変更する.
		if(user.getAuthority() == 0) {
			authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));// 管理者権限付与
			System.out.println("ROLE_ADMINの権限が付与されました。");
		}else if(user.getAuthority() == 1) {
			authorityList.add(new SimpleGrantedAuthority("ROLE_USER")); // ユーザー権限付与
			System.out.println("ROLE_USERの権限が付与されました。");
		}else {
			throw new BadCredentialsException("Authentication Error");
		}
		return new LoginUser(user,authorityList);
	}
}
