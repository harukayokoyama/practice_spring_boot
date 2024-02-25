package com.example.mine.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mine.entity.Users;
import com.example.mine.repository.AccountRepository;

/**
 * Spring SecurityでDB認証するときに使うService
 */
@Service
public class AccountUserDetailService implements UserDetailsService {

	/** アカウントレポジトリ */
	@Autowired
	private AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

	/**
	 * 認証処理
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Users user = accountRepository.findByUserName(userName);
		return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
	}

	@Transactional
	public void register(String username, String password) {
		accountRepository.create(username, passwordEncoder.encode(password));
	}
}
