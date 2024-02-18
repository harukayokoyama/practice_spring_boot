package com.example.mine.entity;

import java.util.Date;

import lombok.Data;

@Data// TODO 追加したけどよかった？
public class Recipe {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.recipe.recipe_id
     *
     * @mbg.generated
     */
    private Integer recipeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.recipe.recipe_name
     *
     * @mbg.generated
     */
    private String recipeName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.recipe.memo
     *
     * @mbg.generated
     */
    private String memo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.recipe.created_by
     *
     * @mbg.generated
     */
    private String createdBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.recipe.created_at
     *
     * @mbg.generated
     */
    private Date createdAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.recipe.recipe_id
     *
     * @return the value of public.recipe.recipe_id
     *
     * @mbg.generated
     */
    public Integer getRecipeId() {
        return recipeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.recipe.recipe_id
     *
     * @param recipeId the value for public.recipe.recipe_id
     *
     * @mbg.generated
     */
    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.recipe.recipe_name
     *
     * @return the value of public.recipe.recipe_name
     *
     * @mbg.generated
     */
    public String getRecipeName() {
        return recipeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.recipe.recipe_name
     *
     * @param recipeName the value for public.recipe.recipe_name
     *
     * @mbg.generated
     */
    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.recipe.memo
     *
     * @return the value of public.recipe.memo
     *
     * @mbg.generated
     */
    public String getMemo() {
        return memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.recipe.memo
     *
     * @param memo the value for public.recipe.memo
     *
     * @mbg.generated
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.recipe.created_by
     *
     * @return the value of public.recipe.created_by
     *
     * @mbg.generated
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.recipe.created_by
     *
     * @param createdBy the value for public.recipe.created_by
     *
     * @mbg.generated
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.recipe.created_at
     *
     * @return the value of public.recipe.created_at
     *
     * @mbg.generated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.recipe.created_at
     *
     * @param createdAt the value for public.recipe.created_at
     *
     * @mbg.generated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}