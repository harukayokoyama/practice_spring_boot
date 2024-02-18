package com.example.mine.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	/**
	 * ログイン画面表示
	 * @return
	 */
	@GetMapping("/")
	public String rootForm() {
	    return "login";
	}

	/**
	 * ログイン画面表示
	 * @return
	 */
	@GetMapping("/login")
	public String loginForm() {
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
