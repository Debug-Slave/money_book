package com.debugslave.moneybook.comm;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public <T> T selectOne(String queryId, Object param) {
		return sqlSession.selectOne(queryId, param);
	}
	
	public <E extends Object> List<E> select(String queryId, Object param) {
		return sqlSession.selectList(queryId, param);
	}
	
	public int insert(String queryId, Object param) {
		return sqlSession.update(queryId, param);
	}
	
	public int update(String queryId, Object param) {
		return sqlSession.update(queryId, param);
	}
	
	public int delete(String queryId, Object param) {
		return sqlSession.update(queryId, param);
	}
}
