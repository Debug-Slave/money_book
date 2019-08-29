package com.debugslave.moneybook.module.history.cntrl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.debugslave.moneybook.comm.ResultData;
import com.debugslave.moneybook.core.user.UserVO;
import com.debugslave.moneybook.module.history.HistoryVO;
import com.debugslave.moneybook.module.history.servc.IncomeHistoryService;
import com.debugslave.moneybook.module.history.servc.OutgoingHistoryService;

@RestController
public class HistoryController {
	
	@Resource(name="incomeService")
	private IncomeHistoryService incomeService;
	
	@Resource(name="outgoingService")
	private OutgoingHistoryService outgoingService;	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	
	
	@RequestMapping(value="/income/{category}", method=RequestMethod.POST, produces = "application/text; charset=utf8")
	public ResponseEntity<String> insertIncomeHistory(@PathVariable("category") String category, 
				@ModelAttribute("searchVO") HistoryVO history, HttpSession session) {
		
		UserVO user = (UserVO)session.getAttribute("userSession");
		history.setUserIdx(user.getUserIdx());
		history.setCateCode(category);
		
		ResultData data = null;
		
		if("".equals(history.getCateCode())) {
			data = new ResultData("카테고리가 선택되지 않았습니다.", HttpStatus.BAD_REQUEST);
		} else if (null == history.getHisDate()) {
			data = new ResultData("날짜가 입력되지 않았습니다.", HttpStatus.BAD_REQUEST);
		} else if (0 == history.getHisMoney()) {
			data = new ResultData("사용 금액에는 0원을 입력할 수 없습니다.", HttpStatus.BAD_REQUEST);
		}
		
		try {
			if (data == null) data = incomeService.insertHistory(history);
		} catch(Exception e) {
			data = new ResultData("서버 에러가 발생하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		ResponseEntity<String> resultEntity = 
				new ResponseEntity<String>(data.getMsg(), data.getResultCode());
		
		return resultEntity;
	}
	

	@RequestMapping(value="/outgoing/{category}", method=RequestMethod.POST, produces = "application/text; charset=utf8")
	public ResponseEntity<String> insertOutgoingHistory(@PathVariable("category") String category, @ModelAttribute("searchVO") HistoryVO history, HttpSession session, HttpServletRequest request) {
		
		UserVO user = (UserVO)session.getAttribute("userSession");
		String outgoingType = request.getParameter("outgoingType");
		history.setHisType(outgoingType);
		history.setUserIdx(user.getUserIdx());
		history.setCateCode(category);
		
		ResultData data = null;
		
		if("".equals(history.getCateCode())) {
			data = new ResultData("카테고리가 선택되지 않았습니다.", HttpStatus.BAD_REQUEST);
		} else if (null == history.getHisDate()) {
			data = new ResultData("날짜가 입력되지 않았습니다.", HttpStatus.BAD_REQUEST);
		} else if (0 == history.getHisMoney()) {
			data = new ResultData("사용 금액에는 0원을 입력할 수 없습니다.", HttpStatus.BAD_REQUEST);
		} else if ("".equals(outgoingType)) {
			data = new ResultData("지출 방법이 선택되지 않았습니다. \\n현금/체크카드나 신용카드를 선택해주세요.", HttpStatus.BAD_REQUEST);
		} else if ("".equals(history.getHisTitle())) {
			data = new ResultData("제목이 입력되지 않았습니다.", HttpStatus.BAD_REQUEST);
		}
		
		try {
			if (data == null) data = outgoingService.insertHistory(history);
		} catch(Exception e) {
			data = new ResultData("서버 에러가 발생하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		ResponseEntity<String> resultEntity = 
				new ResponseEntity<String>(data.getMsg(), data.getResultCode());
		
		return resultEntity;
	}
	
	
	@RequestMapping(value="/history/{viewName}/{startDateStr}", method=RequestMethod.GET,  produces = "application/json; charset=utf8")
	public Map<String, List<HashMap<String, String>>> getHistorys(@PathVariable("viewName") String viewName, 
				@PathVariable("startDateStr") String startDateStr, HttpSession session) throws Exception {
		viewName = viewName.toUpperCase();
		UserVO user = (UserVO)session.getAttribute("userSession");
		int userIdx = user.getUserIdx(); 
		HashMap<String, List<HashMap<String, String>>> result =
				new HashMap<String, List<HashMap<String, String>>>();
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			Calendar calendar = new GregorianCalendar(Locale.KOREA);
			
			Date startDate = dateFormat.parse(startDateStr);
			calendar.setTime(startDate);
			Date endDate = dateFormat.parse(startDateStr);
			
			if (viewName.indexOf("MONTH") >= 0) {
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				startDate = calendar.getTime();
				
				calendar.add(Calendar.MONTH, 1);
				calendar.add(Calendar.MILLISECOND, -0);
				endDate = calendar.getTime();
				
			} else if (viewName.indexOf("WEEK") >= 0) {
				int dow = calendar.get(Calendar.DAY_OF_WEEK)-1;
				calendar.add(Calendar.DAY_OF_MONTH, (dow*-1));
				startDate = calendar.getTime();
				
				calendar.add(Calendar.DAY_OF_MONTH, 7);
				calendar.add(Calendar.MILLISECOND, -1);
				endDate = calendar.getTime();
			} else if (viewName.indexOf("DAY") >= 0) {
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				calendar.add(Calendar.MILLISECOND, -1);
				endDate = calendar.getTime();
			}
			
			List<HashMap<String, String>> tmpList = incomeService.selectHistoryList(userIdx, startDate, endDate); 
			result.put("incomeList", tmpList);
			tmpList = outgoingService.selectHistoryList(userIdx, startDate, endDate);
			result.put("outgoingList", tmpList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping(value="/historyInfo/{hisType}/{hisIdx}", method=RequestMethod.GET,  
			produces = "application/json; charset=utf8")
	public HistoryVO getHistoryInfo(@PathVariable("hisType") String hisType, @PathVariable("hisIdx") int hisIdx, HttpSession session) throws Exception {
		
		HistoryVO result = new HistoryVO();
		UserVO user = (UserVO)session.getAttribute("userSession");
		result.setUserIdx(user.getUserIdx());
		result.sethIdx(hisIdx);
		
		if ("INCOME".equalsIgnoreCase(hisType)) {
			result = incomeService.selectOneHistory(result);
		} else if ("OUTGOING".equalsIgnoreCase(hisType)) {
			result = outgoingService.selectOneHistory(result);
		}
		
		if (result.getHisMemo() == null) result.setHisMemo("");
		
		result.setHisMemo(result.getHisMemo().replaceAll("\\n", "</br>"));
		
		return result;
	}
	
	@RequestMapping(value="/delHistory/{hisType}/{hisIdx}", method=RequestMethod.DELETE,  
			produces = "application/text; charset=utf8")
	public ResponseEntity<String> deleteHistoryInfo(@PathVariable("hisType") String hisType, @PathVariable("hisIdx") int hisIdx, HttpSession session) throws Exception {
		
		HistoryVO result = new HistoryVO();
		UserVO user = (UserVO)session.getAttribute("userSession");
		result.setUserIdx(user.getUserIdx());
		result.sethIdx(hisIdx);
		
		ResultData data = null;
		
		if ("INCOME".equalsIgnoreCase(hisType)) {
			data = incomeService.deleteHistory(result);
		} else if ("OUTGOING".equalsIgnoreCase(hisType)) {
			data = outgoingService.deleteHistory(result);
		}
		
		ResponseEntity<String> resultEntity = 
				new ResponseEntity<String>(data.getMsg(), data.getResultCode());
		
		return resultEntity;
		
		
	}
}
