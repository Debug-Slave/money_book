package com.debugslave.moneybook.core.user.servc;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.debugslave.moneybook.comm.ResultData;
import com.debugslave.moneybook.comm.utils.StringUtils;
import com.debugslave.moneybook.core.user.UserVO;
import com.debugslave.moneybook.core.user.dao.UserDao;

@Service("userService")
public class UserService {
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	public ResultData registUserData(UserVO userVO) {
		
		ResultData result = new ResultData(HttpStatus.INTERNAL_SERVER_ERROR);
		
		UserVO searchUser = userDao.getUserById(userVO);
		
		if (searchUser != null) return new ResultData("동일한 아이디를 사용하는 사용자가 존재합니다. \\n 다른 아이디로 시도해보세요.", HttpStatus.CONFLICT);
		
		String passSalt = StringUtils.getRandomString(20);
		String encryptPass = StringUtils.getSHA256(userVO.getUserPassword()+passSalt); 
		userVO.setPassSalt(passSalt);
		userVO.setUserPassword(encryptPass);
		
		int istCnt = userDao.insertUserInfo(userVO);
		
		if (istCnt == 1) result = new ResultData("성공", HttpStatus.OK);
		
		return result;
	}
	
	
	public ResultData loginUser(UserVO userVO, HttpSession session) {
		
		ResultData result = new ResultData(HttpStatus.INTERNAL_SERVER_ERROR);
		
		UserVO searchUser = userDao.getUserById(userVO);
		
		if (searchUser == null) return new ResultData("해당 아이디의 사용자가 존재하지 않습니다.", HttpStatus.NOT_FOUND); 
		
		String inputUserPassEncryp = StringUtils.getSHA256(userVO.getUserPassword()+searchUser.getPassSalt());
		
		if(inputUserPassEncryp.equals(searchUser.getUserPassword())) {
			searchUser.setPassSalt("");
			searchUser.setUserPassword("");
			
			session.setAttribute("userSession", searchUser);
			result = new ResultData(HttpStatus.OK);
		} else {
			return new ResultData("해당 사용자의 패스워드가 동일하지 않습니다.", HttpStatus.FORBIDDEN);
		}
		
		return result;
	}
}
