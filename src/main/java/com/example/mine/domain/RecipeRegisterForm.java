package com.example.mine.domain;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * レシピ登録フォーム
 */
@Data
public class RecipeRegisterForm {

	private Integer recipeId;
	
	@NotBlank
	@Length(max=100)
	private String recipeName;

	private String memo;

	@Valid
	private List<IngredientForm> ingredients;

	private String tag;

	private MultipartFile image;
	
	@Data
	public static class IngredientForm {
		private Integer id;
		
		@Length(max=100)
		private String name;

		@Length(max=100)
		private String quantity;
	}
	
	/**
	 * 材料リストに1行追加
	 */
	public void addList() {
		IngredientForm form = new IngredientForm();
		ingredients.add(form);
	}
}
