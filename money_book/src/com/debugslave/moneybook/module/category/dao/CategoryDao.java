package com.debugslave.moneybook.module.category.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.debugslave.moneybook.comm.AbstractDao;

@Repository("categoryDao")
public class CategoryDao extends AbstractDao {
	
	public List<Map<String, String>> getIncomeCategoryList () {
		return select("categoryMapper.selectIncomeCategoryList", null);
	}
	
	public List<Map<String, String>> getOutgoingCategoryList () {
		return select("categoryMapper.selectOutgoingCategoryList", null);
	}
	
}
