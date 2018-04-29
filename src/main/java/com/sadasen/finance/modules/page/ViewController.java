package com.sadasen.finance.modules.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sadasen.finance.base.BaseController;
import com.sadasen.finance.modules.user.entity.User;
import com.sadasen.finance.util.Utils;

/**
 * @date 2018年4月19日
 * @author lei.ys
 * @addr company
 * @desc
 */
@Controller
@RequestMapping("/view")
public class ViewController extends BaseController {

	private static final long serialVersionUID = 1338964243292884261L;

	@GetMapping("/index")
	public ModelAndView viewIndex() {
		User user = Utils.getLoginUser(getRequest());
		ModelAndView mav = new ModelAndView();
		if(null!=user) {
			System.out.println(user.getUserName());
		} else {
			mav.setViewName("redirect:/page/login");
			return mav;
		}
		System.out.println("index viewName : "+"view/index");
		mav.addObject("billId", 1000);
		mav.setViewName("view/index");
		return mav;
	}

	@GetMapping("/{viewName}")
	public String view1(@PathVariable("viewName") String viewName) {
		User user = Utils.getLoginUser(getRequest());
		if(null!=user) {
			System.out.println(user.getUserName());
		} else {
			return "redirect:/page/login";
		}
		System.out.println("viewName : "+"view/"+viewName);
		return "view/"+viewName;
	}

	@GetMapping("/{module}/{viewName}")
	public String view2(@PathVariable("module") String module, @PathVariable("viewName") String viewName) {
		User user = Utils.getLoginUser(getRequest());
		if(null!=user) {
			System.out.println(user.getUserName());
		} else {
			return "redirect:/page/login";
		}
		System.out.println("viewName : "+"view/"+module+ "/" +viewName);
		return "view/"+module+ "/" +viewName;
	}

}
