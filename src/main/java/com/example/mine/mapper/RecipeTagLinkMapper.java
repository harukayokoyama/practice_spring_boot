package com.example.mine.mapper;

import com.example.mine.entity.RecipeTagLink;
import com.example.mine.entity.RecipeTagLinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RecipeTagLinkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.recipe_tag_link
     *
     * @mbg.generated
     */
    long countByExample(RecipeTagLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.recipe_tag_link
     *
     * @mbg.generated
     */
    int deleteByExample(RecipeTagLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.recipe_tag_link
     *
     * @mbg.generated
     */
    int insert(RecipeTagLink row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.recipe_tag_link
     *
     * @mbg.generated
     */
    int insertSelective(RecipeTagLink row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.recipe_tag_link
     *
     * @mbg.generated
     */
    List<RecipeTagLink> selectByExample(RecipeTagLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.recipe_tag_link
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("row") RecipeTagLink row, @Param("example") RecipeTagLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.recipe_tag_link
     *
     * @mbg.generated
     */
    int updateByExample(@Param("row") RecipeTagLink row, @Param("example") RecipeTagLinkExample example);
}