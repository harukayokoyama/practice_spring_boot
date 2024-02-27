package com.example.mine.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mine.domain.RecipeDto;
import com.example.mine.domain.RecipeRegisterForm;
import com.example.mine.domain.RecipeRegisterForm.IngredientForm;
import com.example.mine.entity.Ingredient;
import com.example.mine.service.RecipeService;
import com.example.mine.service.TagService;

/**
 * レシピ登録画面・編集画面コントローラ
 */
@Controller
public class RecipeRegisterController {

	@Autowired
	RecipeService recipeService;
	@Autowired	
	TagService tagService;

	@ModelAttribute
	RecipeRegisterForm setUpForm() {
		return new RecipeRegisterForm();
	}

	/**
	 * レシピ登録画面の初期表示
	 * @return
	 */
	@GetMapping("/register")
	public String register(@ModelAttribute RecipeRegisterForm form, Model model,
			@AuthenticationPrincipal UserDetails user) {
		// リストの初期化（入力欄を常に1行以上表示する）
		List<RecipeRegisterForm.IngredientForm> list = new ArrayList<RecipeRegisterForm.IngredientForm>();
		RecipeRegisterForm.IngredientForm ingredient = new RecipeRegisterForm.IngredientForm();
		list.add(ingredient);
		form.setIngredients(list);
		
		// タグを取得（オートコンプリート用）
		List<String> tags = tagService.getTagByUsername(user.getUsername());
		model.addAttribute("tags", tags.isEmpty() ? new ArrayList<>() : tags);

		return "register";
	}

	/**
	 * 材料1行追加
	 * @param form
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/register", params = "addForm")
	public String addList(@ModelAttribute RecipeRegisterForm form, Model model,
			@AuthenticationPrincipal UserDetails user) {

		// リスト最後尾に1行追加
		form.addList();

		List<String> tags = tagService.getTagByUsername(user.getUsername());
		model.addAttribute("tags", tags.isEmpty() ? new ArrayList<>() : tags);

		return "register";
	}

	/**
	 * レシピ登録submitボタン押下後
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value = "/register", params = "next")
	public String register(@Validated @ModelAttribute RecipeRegisterForm form, BindingResult result, Model model,
			@AuthenticationPrincipal UserDetails user)
			throws Exception {
		List<String> tags = checkAndConvertTags(form, result);
		List<Ingredient> ingredients = checkAndConvertIngredients(form, result);
		if (result.hasErrors()) {
			return "register";
		}

		RecipeDto dto = new RecipeDto();
		dto.setRecipeName(form.getRecipeName());
		dto.setMemo(form.getMemo());
		dto.setTags(tags);
		dto.setIngredients(ingredients);
		recipeService.createRecipe(dto, user.getUsername());

		return "redirect:/top";
	}
	
	/**
	 * レシピ編集submitボタン押下後
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value = "/edit", params = "next")
	public String edit(@Validated @ModelAttribute RecipeRegisterForm form, BindingResult result, Model model,
			@AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) throws Exception {
		List<String> tags = checkAndConvertTags(form, result);
		List<Ingredient> ingredients = checkAndConvertIngredients(form, result);
		if (result.hasErrors()) {
			return "edit";
		}

		RecipeDto dto = new RecipeDto();
		dto.setRecipeId(form.getRecipeId());
		dto.setRecipeName(form.getRecipeName());
		dto.setMemo(form.getMemo());
		dto.setTags(tags);
		dto.setIngredients(ingredients);
		recipeService.updateRecipe(dto, user.getUsername());
		
		redirectAttributes.addAttribute("recipeId", form.getRecipeId());
		return "redirect:/single";
	}
	
	/**
	 * formからタグの文字数をチェックしつつリストに整形
	 * @param form
	 * @param result
	 * @return
	 */
	private List<String> checkAndConvertTags(RecipeRegisterForm form, BindingResult result){
		List<String> tags = new ArrayList<>();
		if (!StringUtils.isBlank(form.getTag())) {
			JSONArray tagJsonArray = new JSONArray(form.getTag()); 
			for (int i = 0; i < tagJsonArray.length(); i++) {
				JSONObject tagJson = (JSONObject) tagJsonArray.getJSONObject(i);
				String tag = (String) tagJson.get("value");
				// タグ文字数チェック
				if (tag.length() > 100) {
					result.rejectValue("tags", "Size", new String[] { null, "100" }, null);
					break;
				}
				tags.add(tag);
			}
		}
		return tags;
	}

	/**
	 * formから材料をチェックしつつ材料リストに整形
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	private List<Ingredient> checkAndConvertIngredients(RecipeRegisterForm form, BindingResult result){
		List<Ingredient> ingredients = new ArrayList<>();
		for (IngredientForm ingredientForm : form.getIngredients()) {
			if (StringUtils.isAllEmpty(ingredientForm.getName(), ingredientForm.getQuantity(), String.valueOf(ingredientForm.getId()))) {
				continue;
			} else if (StringUtils.isEmpty(ingredientForm.getName())
					&& !StringUtils.isEmpty(ingredientForm.getQuantity())) {
				// 分量だけであればエラー
				result.rejectValue("ingredients", "ingredientsError");
				continue;
			}

			Ingredient ingredient = new Ingredient();
			ingredient.setId(ingredientForm.getId());
			ingredient.setName(ingredientForm.getName());
			ingredient.setQuantity(ingredientForm.getQuantity());
			ingredients.add(ingredient);
		}
		return ingredients;
	}

}
