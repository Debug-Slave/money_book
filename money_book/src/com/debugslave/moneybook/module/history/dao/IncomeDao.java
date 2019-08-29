package com.debugslave.moneybook.module.history.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.debugslave.moneybook.comm.AbstractDao;
import com.debugslave.moneybook.module.history.HistoryVO;

@Repository("incomeDao")
public class IncomeDao extends AbstractDao{
	
	public int insertIncomeHistory(HistoryVO history) {
		return insert("incomeMapper.insertIncomeHistory", history);
	}
	
	public int deleteIncomeHistory(HistoryVO history) {
		return insert("incomeMapper.deleteIncomeHistory", history);
	}
	
	public List<HashMap<String, String>> selectIncomeHistory(HashMap<String, String> paramMap) {
		return select("incomeMapper.selectIncomeHistoryList", paramMap);
	}
	
	public HistoryVO selectIncomeHistoryInfo(HistoryVO searchVO) {
		return selectOne("incomeMapper.selectIncomeHistoryInfo", searchVO);
	}
}
