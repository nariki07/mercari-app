package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig {
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	// 静的リソースに対するアクセスはセキュリティ設定を無視する
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/fonts/**");
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
    	http.authorizeHttpRequests()
    			// アクセス権限の設定
        		// アクセス制限の無いURL
    	        .requestMatchers("/login","/loginUser","/error","/toInsert","/insert").permitAll()
    	        // その他は認証済みであること
    	        .anyRequest()
    	        .authenticated();

		http.formLogin() // ログインに関する設定
				.loginPage("/login") // ログイン画面に遷移させるパス(ログイン認証が必要なパスを指定してかつログインされていないとこのパスに遷移される)
				.loginProcessingUrl("/loginUser") // ログインボタンを押した際に遷移させるパス(ここに遷移させれば自動的にログインが行われる)
				.failureUrl("/login?error=true") // ログイン失敗に遷移させるパス
				.defaultSuccessUrl("/",true) // 第1引数:デフォルトでログイン成功時に遷移させるパス
				// 第2引数: true :認証後常に第1引数のパスに遷移
				// false:認証されてなくて一度ログイン画面に飛ばされてもログインしたら指定したURLに遷移
				.usernameParameter("name") // 認証時に使用するユーザ名のリクエストパラメータ名
				.passwordParameter("password"); // 認証時に使用するパスワードのリクエストパラメータ名
		
		http.csrf()
		  		.disable(); //無効にする.ajax使用時にエラーが出るのを回避するため.

		http.logout() // ログアウトに関する設定
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // ログアウトさせる際に遷移させるパス
				.logoutSuccessUrl("/login") // ログアウト後に遷移させるパス(ここではログイン画面を設定)
				.deleteCookies("JSESSIONID") // ログアウト後、Cookieに保存されているセッションIDを削除
				.invalidateHttpSession(true); // true:ログアウト後、セッションを無効にする false:セッションを無効にしない
		return http.build();
	}
	
	
	//パスワードのハッシュ化を行う準備を行う.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
