package com.example.mine.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.InvalidSessionStrategy;

import com.example.mine.controller.MyAuthenticationFailureHandler;

import jakarta.servlet.http.HttpServletRequest;

//@Profile("production")// spring security無効化する位一部
@Configuration
@EnableWebSecurity //Spring Security有効化
public class SecurityConfig {
	/** ログインurl */
	public static String LOGIN_URL1 = "/";
	public static String LOGIN_URL2 = "/login";
	public static String ERROR_URL = "/error";
	public static String LOGIN_SUCCESS_URL = "/login/success";

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		/** ログインの設定？ */
		http.formLogin(login -> login
				// ログイン画面　
				.loginPage(LOGIN_URL2)
				// このurlにくると認証処理が実行される
				.loginProcessingUrl(LOGIN_URL2)
				// ログイン成功後url
				.defaultSuccessUrl(LOGIN_SUCCESS_URL)
				// usernameのパラメータ名
				.usernameParameter("username")
				// passwordのパラメータ名
				.passwordParameter("password")
				// 失敗時の遷移先
				.failureHandler(new MyAuthenticationFailureHandler())
				.permitAll())// 認証不要
				/** ログアウト設定 */
				.logout(logout -> logout
						.deleteCookies("JSESSIONID") // ログアウトしたら cookieのJSESSIONID を削除
						.invalidateHttpSession(true)// ログアウトしたらセッションを無効にする
						.logoutSuccessUrl(LOGIN_URL1))
				/** アクセス制限設定 */
				.authorizeHttpRequests(authz -> authz
						.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()// 静的ファイルはアクセス制限しない
						/** ログイン処理に使うURLにはアクセス制限しない */
						.requestMatchers(LOGIN_URL1).permitAll()
						.requestMatchers(LOGIN_URL2).permitAll()
						.requestMatchers(ERROR_URL).permitAll()
						.requestMatchers("signup").permitAll()
						.anyRequest().authenticated());

		/** セッション管理 */
		http.sessionManagement().invalidSessionStrategy(createInvalidSessionStrategy());

		return http.build();
	}

	/**
	 * セッションタイムアウト時の処理
	 * @return
	 */
	private InvalidSessionStrategy createInvalidSessionStrategy() {
		return (req, res) -> {
			if ("XMLHttpRequest".equals(((HttpServletRequest) req).getHeader("X-Requested-With"))) {
				// AJAXの場合401ヘッダーを付与
				res.setStatus(HttpStatus.UNAUTHORIZED.value());
			} else {
				// その他の場合、ログイン画面を表示
				req.getSession(true);
				res.sendRedirect(req.getContextPath() + "/login");
			}
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}