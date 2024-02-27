package com.example.mine.controller;

import java.util.ArrayList;

//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mine.domain.RecipeDetailDto;
import com.example.mine.domain.RecipeDto;
import com.example.mine.domain.RecipeRegisterForm;
import com.example.mine.entity.Ingredient;
import com.example.mine.service.RecipeService;
import com.example.mine.service.TagService;

/**
 * レシピ詳細画面コントローラ
 */
@Controller
public class RecipeDetailController {

	@Autowired
	RecipeService recipeService;
	@Autowired
	TagService tagService;

	@ModelAttribute
	RecipeRegisterForm setUpForm() {
		return new RecipeRegisterForm();
	}

	/**
	 * レシピ詳細画面
	 * @return
	 */
	@GetMapping("/single")
	public String single(@RequestParam("recipeId") Integer recipeId, Model model) {
		List<RecipeDetailDto> recipeDetailDtos = recipeService.getRecipeForDetail(recipeId);
		RecipeDetailDto recipe = recipeDetailDtos.get(0);

		// 材料情報 
		List<Ingredient> ingredients = new ArrayList<>();
		for (RecipeDetailDto dto : recipeDetailDtos) {
			Ingredient ingredient = new Ingredient();
			ingredient.setId(dto.getIngredientId());
			ingredient.setName(dto.getIngredientName());
			ingredient.setQuantity(dto.getQuantity());
			ingredients.add(ingredient);
		}

		RecipeDto dto = new RecipeDto();
		dto.setRecipeId(recipeId);
		dto.setRecipeName(recipe.getRecipeName());
		dto.setMemo(recipe.getMemo());
		//		dto.setPath(recipe.getPath());
		//		dto.setPath(recipe.getFileName());
		dto.setIngredients(ingredients);
		dto.setTag(recipe.getTag());

		model.addAttribute("recipe", dto);

		return "single";
	}

	/**
	 * レシピ詳細画面初期表示
	 * @return
	 */
	@GetMapping("/edit")
	public String edit(@RequestParam("recipeId") Integer recipeId, Model model,
			@AuthenticationPrincipal UserDetails user) {
		List<RecipeDetailDto> recipeDetailDtos = recipeService.getRecipeForDetail(recipeId);
		RecipeDetailDto recipe = recipeDetailDtos.get(0);
		// 材料情報 
		List<RecipeRegisterForm.IngredientForm> ingredientsForm = new ArrayList<>();
		for (RecipeDetailDto dto : recipeDetailDtos) {
			RecipeRegisterForm.IngredientForm form = new RecipeRegisterForm.IngredientForm();
			form.setId(dto.getIngredientId());
			form.setName(dto.getIngredientName());
			form.setQuantity(dto.getQuantity());
			ingredientsForm.add(form);
		}

		RecipeRegisterForm form = new RecipeRegisterForm();
		form.setRecipeId(recipeId);
		form.setRecipeName(recipe.getRecipeName());
		form.setMemo(recipe.getMemo());
		// TODO
		//		form.setPath(recipe.getPath());
		//		form.setPath(recipe.getFileName());
		form.setIngredients(ingredientsForm);
		form.setTag(recipe.getTag());

		model.addAttribute("recipeRegisterForm", form);

		// タグを取得（オートコンプリート用）
		List<String> tags = tagService.getTagByUsername(user.getUsername());
		model.addAttribute("tags", tags.isEmpty() ? new ArrayList<>() : tags);

		return "edit";
	}

	/**
	 * 材料1行追加
	 * @param form
	 * @param model
	 * @return
	 */
	@PostMapping(value = "edit", params = "addForm")
	public String addList(@ModelAttribute RecipeRegisterForm form, Model model,
			@AuthenticationPrincipal UserDetails user) {

		// リスト最後尾に1行追加
		form.addList();

		List<String> tags = tagService.getTagByUsername(user.getUsername());
		model.addAttribute("tags", tags.isEmpty() ? new ArrayList<>() : tags);
		return "edit";
	}

	/**
	 * レシピの削除
	 * @param recipeId
	 * @param model
	 * @return
	 */
	@PostMapping("/delete")
	public String delete(@RequestParam("recipeId") Integer recipeId, Model model) {
		recipeService.deleteRecipe(recipeId);

		return "redirect:/top";
	}

}
