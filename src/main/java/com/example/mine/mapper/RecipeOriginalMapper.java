package com.example.mine.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.example.mine.domain.RecipeDetailDto;
import com.example.mine.domain.RecipeDto;

@Mapper
public interface RecipeOriginalMapper {
	/**
	 * 制限数を上限にレシピ情報を取得する
	 * 
	 * @param userName 登録ユーザー名
	 * @param limit 制限数
	 * @return
	 */
    List<RecipeDto> select(@Param("userName") String userName, @Param("limit") Integer limit);

    /**
     * レシピ詳細情報を取得する
     * @param recipeId
     * @return
     */
    List<RecipeDetailDto> selectDetail(Integer recipeId);
}