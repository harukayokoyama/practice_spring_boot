package com.example.mine.domain;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * アカウント登録フォーム
 */
@Data
public class SignUpForm {

	@NotBlank
	@Length(max=255)
	private String username;

	@NotBlank
	private String password;
	
}
