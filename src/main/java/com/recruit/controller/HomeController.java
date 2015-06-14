package com.recruit.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页控制器
 * 
 * @author xiejinwei
 * 
 * @since 2015-06-12
 * 
 */

@Controller
public class HomeController {

	@RequestMapping(value="/",method=RequestMethod.GET)
	public String home(HttpServletRequest request){
		return "home";
	}
}
