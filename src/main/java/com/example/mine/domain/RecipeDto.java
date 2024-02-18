package com.example.mine.domain;

import java.util.List;

import com.example.mine.entity.Ingredient;

import lombok.Data;

/**
 * レシピ情報DTO
 */
@Data
public class RecipeDto {
	private Integer recipeId;

	private String recipeName;

	private String memo;
	
	private List<String> tags;

	private String tag;
	
	private List<Ingredient> Ingredients;
	
	private String path;
	
	private String fileName;
} 
