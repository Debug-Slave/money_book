package com.debugslave.moneybook.module.report.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.debugslave.moneybook.comm.AbstractDao;

@Repository("reportDao")
public class ReportDao extends AbstractDao {
	
	public List<Map<String, String>> getOutgoingCategorySum(Map<String, String> paramMap) throws Exception{
		return select("reportMapper.selectOutgoingCategorySum", paramMap);
	}
	
	public List<Map<String, String>> getOutgoingTypeSum(Map<String, String> paramMap) throws Exception {
		return select("reportMapper.selectOutgoingTypeSum", paramMap);
	}
	
	public List<Map<String, String>> getIncomeCategorySum(Map<String, String> paramMap) throws Exception {
		return select("reportMapper.selectIncomeCategorySum", paramMap);
	}
	
}
