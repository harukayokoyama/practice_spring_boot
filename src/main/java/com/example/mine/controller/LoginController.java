package com.example.mine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mine.domain.SignUpForm;
import com.example.mine.service.AccountUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	AccountUserDetailService accountUserDetailService;

	@ModelAttribute
	SignUpForm setUpForm() {
		return new SignUpForm();
	}

	/**
	 * ログイン画面表示
	 * @return
	 */
	@GetMapping({ "/", "login" })
	public String login(Model model, HttpServletRequest request,
			@RequestParam(value = "logout", required = false) final String logout) {

		// セッションにログイン失敗時の情報がある場合、エラーメッセージを画面に埋め込む。
		// エラーメッセージは一度だけ表示されればよいので、そのあとセッションをクリアする
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("LOGIN_FAILURE") != null) {
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
	public String loginSuccess(@AuthenticationPrincipal UserDetails user, Model model) {
		model.addAttribute("username", user.getUsername());
		return "redirect:/top";
	}

	/**
	 * アカウント登録画面表示
	 * @return
	 */
	@GetMapping("/signup")
	public String showCreateAcocunt() {
		return "signup";
	}

	/**
	 * ユーザー登録
	 * @param form
	 * @param result
	 * @param model
	 * @return
	 * @throws ServletException 
	 */
	@PostMapping("/signup")
	public String createAcocunt(@Validated @ModelAttribute SignUpForm form, BindingResult result, Model model, 
			HttpServletRequest request) throws ServletException {
		if (result.hasErrors()) {
			return "signup";
		}

		try {
			accountUserDetailService.register(form.getUsername(), form.getPassword());
		} catch (DataAccessException e) {
			model.addAttribute("signupError", "ユーザー名 " + form.getUsername() + "は既に登録されています");
			return "signup";
		}
		
		// 作成後自動でログイン
		SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken == false) {
            SecurityContextHolder.clearContext();
        }
        request.login(form.getUsername(), form.getPassword());
        
		return "redirect:/top";
	}
}
