package com.example.mine.entity;

import java.util.Date;

public class Ingredient {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.ingredient.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.ingredient.recipe_id
     *
     * @mbg.generated
     */
    private Integer recipeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.ingredient.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.ingredient.quantity
     *
     * @mbg.generated
     */
    private String quantity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.ingredient.created_by
     *
     * @mbg.generated
     */
    private String createdBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.ingredient.created_at
     *
     * @mbg.generated
     */
    private Date createdAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.ingredient.id
     *
     * @return the value of public.ingredient.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.ingredient.id
     *
     * @param id the value for public.ingredient.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.ingredient.recipe_id
     *
     * @return the value of public.ingredient.recipe_id
     *
     * @mbg.generated
     */
    public Integer getRecipeId() {
        return recipeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.ingredient.recipe_id
     *
     * @param recipeId the value for public.ingredient.recipe_id
     *
     * @mbg.generated
     */
    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.ingredient.name
     *
     * @return the value of public.ingredient.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.ingredient.name
     *
     * @param name the value for public.ingredient.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.ingredient.quantity
     *
     * @return the value of public.ingredient.quantity
     *
     * @mbg.generated
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.ingredient.quantity
     *
     * @param quantity the value for public.ingredient.quantity
     *
     * @mbg.generated
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.ingredient.created_by
     *
     * @return the value of public.ingredient.created_by
     *
     * @mbg.generated
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.ingredient.created_by
     *
     * @param createdBy the value for public.ingredient.created_by
     *
     * @mbg.generated
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.ingredient.created_at
     *
     * @return the value of public.ingredient.created_at
     *
     * @mbg.generated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.ingredient.created_at
     *
     * @param createdAt the value for public.ingredient.created_at
     *
     * @mbg.generated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}