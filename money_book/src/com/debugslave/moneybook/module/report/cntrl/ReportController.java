package com.debugslave.moneybook.module.report.cntrl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.debugslave.moneybook.core.user.UserVO;
import com.debugslave.moneybook.module.report.servc.ReportService;

@RestController
public class ReportController {
	
	@Resource(name="reportService")
	private ReportService reportService;
	
	@RequestMapping(value="/report/{startDate}/{endDate}", method=RequestMethod.GET,  
			produces = "application/json; charset=utf8")
	public Map<String, List<Map<String, String>>> getReportData(HttpSession session, 
			@PathVariable("startDate") String startDateStr, @PathVariable("endDate") String endDateStr) throws Exception {
		
		Map<String, String> paramMap = new HashMap<String, String>();
		
		UserVO user = (UserVO)session.getAttribute("userSession");
		int userIdx = user.getUserIdx();
		
		paramMap.put("userIdx", String.valueOf(userIdx));
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date startDate = dateFormat.parse(startDateStr);
		paramMap.put("startDate", dateFormat.format(startDate));
		
		
		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		Date endDate = dateFormat.parse(endDateStr);
		calendar.setTime(endDate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MILLISECOND, -1);
		endDate = calendar.getTime();
		
		paramMap.put("endDate", dateFormat.format(endDate));
		paramMap.put("userIdx", String.valueOf(userIdx));
		
		
		
		Map<String, List<Map<String, String>>> returnValue = 
				new HashMap<String, List<Map<String, String>>>();
		
		List<Map<String, String>> tmpList = reportService.getIncomeCategorySum(paramMap);
		returnValue.put("incomeList", tmpList);
		
		tmpList = reportService.getOutgoingCategorySum(paramMap);
		returnValue.put("outgoingList", tmpList);
		
		tmpList = reportService.getOutgoingTypeSum(paramMap);
		returnValue.put("outgoingType", tmpList);
		
		return returnValue;
	}
	
	
	
}
