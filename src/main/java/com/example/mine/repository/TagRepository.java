package com.example.mine.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.mine.entity.Tag;
import com.example.mine.entity.TagExample;
import com.example.mine.mapper.RecipeTagLinkMapper;
import com.example.mine.mapper.TagMapper;
import com.example.mine.mapper.TagOriginalMapper;

/**
 * Tag Repository
 */
@Repository
public class TagRepository {
	@Autowired
	TagMapper tagMapper;
	@Autowired
	TagOriginalMapper tagOriginalMapper;
	@Autowired
	RecipeTagLinkMapper recipeTagLinkMapper;

	public int insert(Tag tag) {
		return tagMapper.insertSelective(tag);
	}

	public List<Tag> selectByRecipeId(int recipeId) {
		return tagOriginalMapper.selectByRecipeId(recipeId);
	}
	
	/**
	 * タグ名で取得（1件目）
	 * @param tagName
	 * @return Tag
	 */
	public Tag selectByTagName(String tagName) {
		TagExample example = new TagExample();
		example.createCriteria().andTagNameEqualTo(tagName);
		return tagMapper.selectByExample(example).stream().findFirst().orElse(null);
	}

	/**
	 * タグ取得（文字のみ重複排除）
	 * @param username
	 * @return
	 */
	public List<String> selectByUsername(String username) {
		return tagOriginalMapper.selectByUsername(username);
	}
	
	/**
	 * レシピIDで削除（リンクテーブルも一緒に削除）
	 * @param recipeId
	 */
//	public void deleteByRecipeId(int recipeId) {
//		List<Tag> deletetags = tagOriginalMapper.selectByRecipeId(recipeId);
//		if(deletetags.isEmpty()) {
//			return;
//		}
//		List<Integer> deleteTagIds = new ArrayList<>();
//		for (Tag tag : deletetags) {
//			deleteTagIds.add(tag.getTagId());
//		}
//		TagExample tagExmaple = new TagExample();
//		tagExmaple.createCriteria().andTagIdIn(deleteTagIds);
//		tagMapper.deleteByExample(tagExmaple);
//
//		RecipeTagLinkExample recipeTagLinkExample = new RecipeTagLinkExample();
//		recipeTagLinkExample.createCriteria().andTagIdIn(deleteTagIds);
//		recipeTagLinkMapper.deleteByExample(recipeTagLinkExample);
//	}

}