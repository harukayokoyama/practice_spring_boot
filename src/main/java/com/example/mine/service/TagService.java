package com.example.mine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mine.repository.TagRepository;

/**
 * タグサービス
 */
@Service
public class TagService {

	@Autowired
	TagRepository tagRepository;
	
	/**
	 * ユーザーが作成した全タグ取得（重複排除）
	 * @param userName
	 * @return
	 */
	public List<String> getTagByUsername(String userName) {
		return tagRepository.selectByUsername(userName);
	}
}
