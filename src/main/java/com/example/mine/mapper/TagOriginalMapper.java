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
    

    /**
     * タグ名取得(重複排除)
     * @param username
     * @return
     */
    List<String> selectByUsername(String username);
}