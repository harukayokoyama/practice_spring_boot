package com.example.mine.entity;

public class Users extends UsersKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.users.username
     *
     * @mbg.generated
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.users.password
     *
     * @mbg.generated
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.users.authority
     *
     * @mbg.generated
     */
    private String authority;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.users.username
     *
     * @return the value of public.users.username
     *
     * @mbg.generated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.users.username
     *
     * @param username the value for public.users.username
     *
     * @mbg.generated
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.users.password
     *
     * @return the value of public.users.password
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.users.password
     *
     * @param password the value for public.users.password
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.users.authority
     *
     * @return the value of public.users.authority
     *
     * @mbg.generated
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.users.authority
     *
     * @param authority the value for public.users.authority
     *
     * @mbg.generated
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }
}