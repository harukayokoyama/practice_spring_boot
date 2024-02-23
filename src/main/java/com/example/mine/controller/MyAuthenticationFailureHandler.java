package com.example.mine.controller;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * 参考：https://zenn.dev/akitsuji/scraps/c874b4850da164
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		// セッションにログインが失敗したことを埋め込む
		HttpSession session = request.getSession(false);
		if(session != null) {
			request.getSession().setAttribute("LOGIN_FAILURE", exception);
			
		}
		// ログイン画面のURLへのリダイレクト設定
		DefaultRedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, "/login");		
	}
}