package com.debugslave.moneybook.module.history.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.debugslave.moneybook.comm.AbstractDao;
import com.debugslave.moneybook.module.history.HistoryVO;

@Repository("outgoingDao")
public class OutgoingDao extends AbstractDao{
	

	public int insertOutgoingHistory(HistoryVO history) {
		return insert("outgoingMapper.insertOutgoingHistory", history);
	}
	
	public int deleteOutgoingHistory(HistoryVO history) {
		return insert("outgoingMapper.deleteOutgoingHistory", history);
	}
	
	public List<HashMap<String, String>> selectOutgoingHistory(HashMap<String, String> paramMap) {
		return select("outgoingMapper.selectOutgoingHistoryList", paramMap);
	}
	
	public HistoryVO selectOutgoingHistoryInfo(HistoryVO searchVO) {
		return selectOne("outgoingMapper.selectOutgoingHistoryInfo", searchVO);
	}
}
