package com.sadasen.finance.modules.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @date 2018年4月21日
 * @author lei.ys
 * @addr company
 * @desc
 */
@Controller
public class IndexController {
	
	@GetMapping("/")
	public String index() {
		return "forward:/page/login";
	}

}
