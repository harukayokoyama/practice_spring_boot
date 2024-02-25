package com.example.mine.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.mine.entity.Users;
import com.example.mine.entity.UsersExample;
import com.example.mine.mapper.UsersMapper;

/**
 * Account Repository
 */
@Repository
public class AccountRepository {

	@Autowired
	UsersMapper usersMapper;
	
	/**
	 * ユーザー名で検索する
	 * 
	 * @param userName
	 * @return ユーザー情報
	 */
	public Users findByUserName(String userName) {
		UsersExample exmaple = new UsersExample();
		exmaple.createCriteria().andUsernameEqualTo(userName);
		return usersMapper.selectByExample(exmaple).stream().findFirst().orElse(null);
	}
	
	/**
	 * ユーザー登録
	 * @param username
	 * @param password
	 */
	public void create(String username, String password) {
		Users user = new Users();
		user.setUsername(username);
		user.setPassword(password);
		usersMapper.insertSelective(user);
	}
	
}