package com.example.mine.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.mine.domain.RecipeDetailDto;
import com.example.mine.domain.RecipeDto;
import com.example.mine.entity.Recipe;
import com.example.mine.entity.RecipeExample;
import com.example.mine.mapper.RecipeMapper;
import com.example.mine.mapper.RecipeOriginalMapper;

/**
 * Recipe Repository
 */
@Repository
public class RecipeRepository {
	@Autowired
	RecipeMapper recipeMapper;// private finalはいるのかな？
	@Autowired
	RecipeOriginalMapper recipeOriginalMapper;

	public void insert(Recipe recipe) {
		recipeMapper.insertSelective(recipe);
	}
	
	public void update(Recipe recipe) {
		RecipeExample example = new RecipeExample();
		example.createCriteria().andRecipeIdEqualTo(recipe.getRecipeId());
		recipeMapper.updateByExampleSelective(recipe, example);
	}
	
	/**
	 * レシピ情報を取得(簡易)
	 * 
	 * @param limit 取得制限数
	 */
	public List<RecipeDto> select(String userName, Integer limit) {
		return recipeOriginalMapper.select(userName, limit);
	}

	/**
	 * レシピ情報を取得(詳細)
	 * 
	 * @param recipeId
	 */
	public List<RecipeDetailDto> selectDetail(Integer recipeId) {
		return recipeOriginalMapper.selectDetail(recipeId);
	}
	
	public Recipe selectById(Integer recipeId) {
		RecipeExample example = new RecipeExample();
		example.createCriteria().andRecipeIdEqualTo(recipeId);
		return recipeMapper.selectByExample(example).stream().findFirst().orElse(null);
	}
}