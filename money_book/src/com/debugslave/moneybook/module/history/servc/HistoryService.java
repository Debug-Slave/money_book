package com.debugslave.moneybook.module.history.servc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.debugslave.moneybook.comm.ResultData;
import com.debugslave.moneybook.module.history.HistoryVO;

public interface HistoryService {
	
	ResultData insertHistory(HistoryVO history) throws Exception;
	
	ResultData updateHistory(HistoryVO history) throws Exception;
	
	ResultData deleteHistory(HistoryVO history) throws Exception;
	
	List<HashMap<String, String>> selectHistoryList(int userIdx, Date startDate, Date endDate) throws Exception;
	
	HistoryVO selectOneHistory(HistoryVO searchVO) throws Exception;
}
