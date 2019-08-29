package com.debugslave.moneybook.module.report.servc;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.debugslave.moneybook.module.report.dao.ReportDao;

@Service("reportService")
public class ReportService {
	
	@Resource(name="reportDao")
	private ReportDao reportDao;
	
	
	public List<Map<String, String>> getOutgoingCategorySum(Map<String, String> paramMap) throws Exception{
		return reportDao.getOutgoingCategorySum(paramMap);
	}
	
	public List<Map<String, String>> getOutgoingTypeSum(Map<String, String> paramMap) throws Exception {
		return reportDao.getOutgoingTypeSum(paramMap);
	}
	
	public List<Map<String, String>> getIncomeCategorySum(Map<String, String> paramMap) throws Exception {
		return reportDao.getIncomeCategorySum(paramMap);
	}
	
	
}
