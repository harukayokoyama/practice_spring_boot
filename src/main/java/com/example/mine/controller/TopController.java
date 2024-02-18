package com.example.mine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mine.service.RecipeService;

@Controller
public class TopController {

	@Autowired
	RecipeService recipeService;
	/** TOP画面に表示するレシピ数の上限（TODO 仮） */
//	private static final Integer LIMIT = 20;

	/**
	 * トップ画面
	 * @return
	 */
	@GetMapping("/top")
	public String top(@AuthenticationPrincipal UserDetails user, Model model) {
		model.addAttribute("recipes", recipeService.getRecipeForTop(user.getUsername(), null));
		return "top";
	}

}
