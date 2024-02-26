package com.example.mine.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mine.entity.Tag;

@Mapper
public interface TagOriginalMapper {
	/**
	 * レシピIDで取得
	 * @param recipeId
	 * @return
	 */
    List<Tag> selectByRecipeId(Integer recipeId);
}