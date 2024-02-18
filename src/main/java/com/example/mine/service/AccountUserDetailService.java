package com.example.mine.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.mine.entity.Users;
import com.example.mine.repository.AccountRepository;

/**
 * Spring SecurityでDB認証するときに使うService
 */
@Service
public class AccountUserDetailService implements UserDetailsService  {

	/** アカウントレポジトリ */
	@Autowired
	private AccountRepository accountRepository;


	/**
	 * 認証処理
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Users user = accountRepository.findByUserName(userName);
		return new User(user.getUserName(), user.getPassword(), Collections.emptyList());
	}
}
