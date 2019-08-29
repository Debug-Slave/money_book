package com.debugslave.moneybook.core.user.cntrl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.debugslave.moneybook.comm.ResultData;
import com.debugslave.moneybook.core.user.UserVO;
import com.debugslave.moneybook.core.user.servc.UserService;

@Controller
public class UserController {
	
	@Resource(name="userService")
	private UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	
	@RequestMapping(value="/user/regist", method=RequestMethod.POST, produces = "application/text; charset=utf8")
	public ResponseEntity<String> registUser(@ModelAttribute UserVO userInfo) {
		
		ResultData data = null;
		
		if("".equals(userInfo.getUserId())) {
			data = new ResultData("사용자 아이디는 반드시 입력해야합니다.", HttpStatus.BAD_REQUEST);
		} else if ("".equals(userInfo.getUserName())) {
			data = new ResultData("사용자 이름은 반드시 입력해야합니다.", HttpStatus.BAD_REQUEST);
		} else if ("".equals(userInfo.getUserPassword())) {
			data = new ResultData("사용자 패스워드는 반드시 입력해야합니다.", HttpStatus.BAD_REQUEST);
		} else if ("".equals(userInfo.getUserConfirmPassword())) {
			data = new ResultData("사용자 패스워드 확인 값은 반드시 입력해야합니다.", HttpStatus.BAD_REQUEST);
		} else if (!userInfo.getUserPassword().equals(userInfo.getUserConfirmPassword())) {
			data = new ResultData("사용자 패스워드와 패스워드 확인 값이 동일하지 않습니다.", HttpStatus.BAD_REQUEST);
		}
		
		if (data == null) data = userService.registUserData(userInfo);
		
		ResponseEntity<String> resultEntity = 
				new ResponseEntity<String>(data.getMsg(), data.getResultCode());
		
		return resultEntity;
	}

	@RequestMapping(value="/user/login", method=RequestMethod.POST, produces = "application/text; charset=utf8") 
	public ResponseEntity<String> loginUser(@ModelAttribute UserVO userInfo, HttpSession session) {
		
		ResultData data = null;
		
		if("".equals(userInfo.getUserId())) {
			data = new ResultData("사용자 아이디는 반드시 입력해야 합니다.", HttpStatus.BAD_REQUEST);
		} else if ("".equals(userInfo.getUserPassword())) {
			data = new ResultData("사용자 패스워드는 반드시 입력해야 합니다.", HttpStatus.BAD_REQUEST);
		}
		
		if (data == null) data = userService.loginUser(userInfo, session);
		
		ResponseEntity<String> resultEntity = 
				new ResponseEntity<String>(data.getMsg(), data.getResultCode());
		
		return resultEntity;
	}
	
}
