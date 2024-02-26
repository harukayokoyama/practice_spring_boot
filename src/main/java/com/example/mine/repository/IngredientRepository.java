package com.example.mine.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.mine.entity.Ingredient;
import com.example.mine.entity.IngredientExample;
import com.example.mine.mapper.IngredientMapper;

/**
 * IngredientRepository
 */
@Repository
public class IngredientRepository {
	@Autowired
	IngredientMapper ingredientMapper;

	/**
	 * insert
	 * @param recipe
	 * @return 主キー
	 */
	public int insert(Ingredient ingredient) {
		return ingredientMapper.insertSelective(ingredient);
	}

	public int update(Ingredient ingredient) {
		IngredientExample example = new IngredientExample();
		example.createCriteria().andIdEqualTo(ingredient.getId());
		return ingredientMapper.updateByExampleSelective(ingredient, example);
	}

	public void delete(int id) {
		IngredientExample example = new IngredientExample();
		example.createCriteria().andIdEqualTo(id);
		ingredientMapper.deleteByExample(example);
	}
	
	public List<Ingredient> selectByRecipeId(Integer recipeId) {
		IngredientExample example = new IngredientExample();
		example.createCriteria().andRecipeIdEqualTo(recipeId);
		return ingredientMapper.selectByExample(example);
	}
	
	/**
	 * レシピIDで削除
	 * @param recipeId
	 */
	public void deleteById(Integer recipeId) {
		IngredientExample example = new IngredientExample();
		example.createCriteria().andRecipeIdEqualTo(recipeId);
		ingredientMapper.deleteByExample(example);
	}
}