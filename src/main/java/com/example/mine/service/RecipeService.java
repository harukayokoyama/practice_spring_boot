package com.example.mine.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mine.domain.RecipeDetailDto;
import com.example.mine.domain.RecipeDto;
import com.example.mine.entity.Ingredient;
import com.example.mine.entity.Recipe;
import com.example.mine.entity.Tag;
import com.example.mine.repository.IngredientRepository;
import com.example.mine.repository.RecipeRepository;
import com.example.mine.repository.RecipeTagLinkRepository;
import com.example.mine.repository.TagRepository;

/**
 * レシピサービス
 */
@Service
public class RecipeService {

	@Autowired
	RecipeRepository recipeRepository;
	@Autowired
	IngredientRepository ingredientRepository;
	@Autowired
	TagRepository tagRepository;
	@Autowired
	RecipeTagLinkRepository recipeTagLinkRepository;
	
	/**
	 * TOP画面に表示するレシピ情報（簡易）を取得する
	 * 
	 * @param userName
	 * @throws Exception
	 */
	public List<RecipeDto> getRecipeForTop(String userName, Integer limit) {
		return recipeRepository.select(userName, limit);
	}
	

	/**
	 * レシピの登録
	 * 
	 * @param dto
	 * @param userName
	 * @throws Exception
	 */
	@Transactional
	public void createRecipe(RecipeDto dto, String userName) throws Exception {
		// レシピの登録
		Recipe recipe = new Recipe();
		recipe.setRecipeName(dto.getRecipeName());
		recipe.setMemo(dto.getMemo());
		recipe.setCreatedBy(userName);
		recipeRepository.insert(recipe);

		// 材料の登録
		for(Ingredient ingredient : dto.getIngredients()) {
			ingredient.setRecipeId(recipe.getRecipeId());
			ingredient.setCreatedBy(userName);
			ingredientRepository.insert(ingredient);
		}

		// タグの登録
		for(String tag : dto.getTags()) {
			Tag tagForInsert = new Tag();
			tagForInsert.setTagName(tag);
			tagForInsert.setCreatedBy(userName);
			tagRepository.insert(tagForInsert);
			recipeTagLinkRepository.insert(recipe.getRecipeId(), tagForInsert.getTagId());
		}

		// TODO 画像
	}
	
	/**
	 * レシピ全詳細情報を取得する
	 * @param recipeId
	 * @return
	 */
	public List<RecipeDetailDto> getRecipeForDetail(Integer recipeId) {
		return recipeRepository.selectDetail(recipeId);
	}
	
	public Recipe getRecipe(Integer recipeId) {
		return recipeRepository.selectById(recipeId);
	}
	
	public List<Ingredient> getIngredientsByRecipeId(Integer recipeId) {
		return ingredientRepository.selectByRecipeId(recipeId);
	}
	
	public List<Tag> getTagsByRecipeId(Integer recipeId) {
		return tagRepository.selectByRecipeId(recipeId);
	}
	
	/**
	 * レシピの更新
	 * 
	 * @param dto
	 * @param userName
	 * @throws Exception
	 */
	@Transactional
	public void updateRecipe(RecipeDto dto, String userName) throws Exception {
		// レシピの更新
		Recipe recipe = new Recipe();
		recipe.setRecipeId(dto.getRecipeId());
		recipe.setRecipeName(dto.getRecipeName());
		recipe.setMemo(dto.getMemo());
		recipeRepository.update(recipe);

		// 材料の更新
		for(Ingredient ingredient : dto.getIngredients()) {
			if(ingredient.getId() != null) {
				if(StringUtils.isEmpty(ingredient.getName())) {
					// idありかつ中身が空の場合削除
					ingredientRepository.delete(ingredient.getId() );
				}else {
					// idありかつ中身アリの場合更新
					ingredientRepository.update(ingredient);
				}
			}else if(!StringUtils.isEmpty(ingredient.getName())) {
				// idが空で中身がある場合は登録
				ingredient.setRecipeId(recipe.getRecipeId());
				ingredient.setCreatedBy(userName);
				ingredientRepository.insert(ingredient);
			}
			
		}

		// TODO タグの更新　全部削除後再登録？
		tagRepository.deleteByRecipeId(dto.getRecipeId());
		for(String tag : dto.getTags()) {
			Tag tagForInsert = new Tag();
			tagForInsert.setTagName(tag);
			tagForInsert.setCreatedBy(userName);
			tagRepository.insert(tagForInsert);
			recipeTagLinkRepository.insert(recipe.getRecipeId(), tagForInsert.getTagId());
		}

		// TODO 画像
	}
}
