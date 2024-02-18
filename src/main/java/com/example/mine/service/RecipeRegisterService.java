package com.example.mine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mine.repository.IngredientRepository;
import com.example.mine.repository.RecipeRepository;
import com.example.mine.repository.RecipeTagLinkRepository;
import com.example.mine.repository.TagRepository;

/**
 * レシピ登録サービス
 */
@Service
public class RecipeRegisterService {

	@Autowired
	RecipeRepository recipeRepository;
	@Autowired
	IngredientRepository ingredientRepository;
	@Autowired
	TagRepository tagRepository;
	@Autowired
	RecipeTagLinkRepository recipeTagLinkRepository;

}
