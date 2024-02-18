package com.example.mine.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.mine.entity.Users;
import com.example.mine.entity.UsersExample;
import com.example.mine.mapper.UsersMapper;

/**
 * User Repository
 */
@Repository
public class AccountRepository {

	@Autowired
	UsersMapper usersMapper;
	
	/**
	 * usernameで検索する
	 * 
	 * @param userName
	 * @return ユーザー情報
	 */
	public Users findByUserName(String userName) {
		UsersExample exmaple = new UsersExample();
		exmaple.createCriteria().andUserNameEqualTo(userName);
		return usersMapper.selectByExample(exmaple).stream().findFirst().orElse(null);
	}
}