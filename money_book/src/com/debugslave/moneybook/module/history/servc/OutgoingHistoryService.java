package com.debugslave.moneybook.module.history.servc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.debugslave.moneybook.comm.ResultData;
import com.debugslave.moneybook.module.history.HistoryVO;
import com.debugslave.moneybook.module.history.dao.OutgoingDao;

@Service("outgoingService")
public class OutgoingHistoryService implements HistoryService {
	
	@Resource(name="outgoingDao")
	private OutgoingDao outgoingDao;
	
	@Override
	public ResultData insertHistory(HistoryVO history) throws Exception {
		ResultData result = new ResultData(HttpStatus.INTERNAL_SERVER_ERROR);
		
		int insertRow = outgoingDao.insertOutgoingHistory(history);
		
		if (insertRow == 1) {
			result = new ResultData(HttpStatus.OK);
		}
		
		return result;
	}

	@Override
	public ResultData updateHistory(HistoryVO history) throws Exception {
		ResultData result = new ResultData("구현 중입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		return result;
	}

	@Override
	public ResultData deleteHistory(HistoryVO history) throws Exception {

		ResultData result = new ResultData(HttpStatus.INTERNAL_SERVER_ERROR);
		
		int insertRow = outgoingDao.deleteOutgoingHistory(history);
		
		if (insertRow == 1) {
			result = new ResultData(HttpStatus.OK);
		}
		
		return result;
	}

	@Override
	public List<HashMap<String, String>> selectHistoryList(int userIdx, Date startDate, Date endDate) throws Exception {
		

		HashMap<String, String> paramMap = new HashMap<String, String>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		paramMap.put("userIdx", String.valueOf(userIdx));
		paramMap.put("startDate", dateFormat.format(startDate));
		paramMap.put("endDate", dateFormat.format(endDate));
		try {
			return outgoingDao.selectOutgoingHistory(paramMap);	
		}catch (Exception e) {
			return outgoingDao.selectOutgoingHistory(paramMap);
		}
		
	}

	@Override
	public HistoryVO selectOneHistory(HistoryVO searchVO) throws Exception {
		return outgoingDao.selectOutgoingHistoryInfo(searchVO);
	}

}
