package com.example.mine.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	/**
	 * ログイン画面表示
	 * @return
	 */
	@GetMapping({"/","login"})
	public String login(Model model, HttpServletRequest request, @RequestParam(value = "logout",required = false)final String logout) {
		
		// セッションにログイン失敗時の情報がある場合、エラーメッセージを画面に埋め込む。
		// エラーメッセージは一度だけ表示されればよいので、そのあとセッションをクリアする
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("LOGIN_FAILURE") != null) {
			model.addAttribute("errorMessage", "ログインに失敗しました。ユーザー名、パスワードを確認してください");
			session.removeAttribute("LOGIN_FAILURE");
		}
		
		return "login";
	}
	
	/**
	 * ログイン成功後処理
	 * @param user
	 * @param model
	 * @return
	 */
	@GetMapping("/login/success")
	public String loginSuccess(@AuthenticationPrincipal UserDetails user,Model model) {
		model.addAttribute("username", user.getUsername());
	    return "redirect:/top";
	}
}
