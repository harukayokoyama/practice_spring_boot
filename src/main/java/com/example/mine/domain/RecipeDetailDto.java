package com.example.mine.domain;

import lombok.Data;

/**
 * レシピ情報DTO(詳細情報)
 */
@Data
public class RecipeDetailDto {
	private Integer recipeId;

	private String recipeName;

	private String memo;

    private Integer ingredientId;
    
    private String ingredientName;

    private String quantity;
	
	private String tag;

//	private Integer tag_id;
//
//	private String tag_name;

	private Integer imageId;
	
	private String path;
	
	private String fileName;
} 
