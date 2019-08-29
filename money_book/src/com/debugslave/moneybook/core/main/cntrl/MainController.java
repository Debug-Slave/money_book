package com.debugslave.moneybook.core.main.cntrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.debugslave.moneybook.module.category.servc.CategoryService;

@Controller
public class MainController {
	
	@Resource(name="categoryService")
	private CategoryService categoryService;
	
	@RequestMapping(value= {"/", ""})
	public String mainPage(ModelMap model) {
		
		model.addAttribute("incomeCategoryList", categoryService.getIncomeCategoryList());
		model.addAttribute("outgoingCategoryList", categoryService.getOutgoingCategoryList());
		
		return "user/index";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session, HttpServletResponse response) throws IOException {
		session.invalidate();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html lang=\"ko\">");
		out.println("<head>");
		out.println("<script>");
		out.println("alert('성공적으로 로그아웃 되었습니다.');");
		out.println("location.href='/intro';");
		out.println("</script>");
		out.println("</head>");
		out.println("</html>");

		return "redirect:/intro";
	}
	
	
}
