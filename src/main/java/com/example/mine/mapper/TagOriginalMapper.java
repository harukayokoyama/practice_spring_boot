package com.example.mine.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mine.entity.Tag;

@Mapper
public interface TagOriginalMapper {
    List<Tag> selectByRecipeId(Integer recipeId);
}