package com.example.mine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mine.repository.RecipeRepository;

/**
 * レシピ登録サービス
 */
@Service
public class RecipeDetailService {

	@Autowired
	RecipeRepository recipeRepository;

}
