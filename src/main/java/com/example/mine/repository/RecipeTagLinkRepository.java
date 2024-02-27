package com.example.mine.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.mine.entity.RecipeTagLink;
import com.example.mine.entity.RecipeTagLinkExample;
import com.example.mine.mapper.RecipeTagLinkMapper;

/**
 * RecipeTagLink Repository
 */
@Repository
public class RecipeTagLinkRepository {
	@Autowired
	RecipeTagLinkMapper recipeTagLinkMapper;

	/**
	 * insert
	 * @param recipe
	 * @return
	 */
	public int insert(int recipeId, int tagId) {
		RecipeTagLink link = new RecipeTagLink();
		link.setRecipeId(recipeId);
		link.setTagId(tagId);
		return recipeTagLinkMapper.insert(link);
	}
	
	/**
	 * レシピIDで削除
	 * @param recipeId
	 */
	public void deleteByRecipeId(int recipeId) {
		RecipeTagLinkExample recipeTagLinkExample = new RecipeTagLinkExample();
		recipeTagLinkExample.createCriteria().andRecipeIdEqualTo(recipeId);
		recipeTagLinkMapper.deleteByExample(recipeTagLinkExample);
	}
}