package com.debugslave.moneybook.core.intro.cntrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IntroController {
	
	
	@RequestMapping(value="intro")
	public String introPage() {
		return "intro/intro";
	}
	
	
}
