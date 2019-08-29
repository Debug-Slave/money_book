package com.debugslave.moneybook.module.category.servc;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.debugslave.moneybook.module.category.dao.CategoryDao;

@Service("categoryService")
public class CategoryService {
	
	@Resource(name="categoryDao")
	private CategoryDao categoryDao;
	
	public List<Map<String, String>> getIncomeCategoryList () {
		return categoryDao.getIncomeCategoryList();
	}
	
	public List<Map<String, String>> getOutgoingCategoryList () {
		return categoryDao.getOutgoingCategoryList();
	}
}
