package com.debugslave.moneybook.core.user.dao;

import org.springframework.stereotype.Repository;

import com.debugslave.moneybook.comm.AbstractDao;
import com.debugslave.moneybook.core.user.UserVO;

@Repository("userDao")
public class UserDao extends AbstractDao {
	
	public UserVO getUserById(UserVO userVO) {
		return selectOne("userMapper.selectUserInfoById", userVO);
	}
	
	public int insertUserInfo(UserVO userVO) {
		return insert("userMapper.insertUserInfo", userVO);
	}
	
}
