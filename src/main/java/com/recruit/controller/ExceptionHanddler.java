package com.recruit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.recruit.exception.RBCException;

public class ExceptionHanddler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ex.printStackTrace();
		ModelAndView mv = new ModelAndView();
		if (ex instanceof RBCException) {
			if(ex.getMessage().equals("weblogout")){
				mv.setViewName("web/login");
				return mv;
			}else if(ex.getMessage().equals("logout")){
				mv.setViewName("manage/login");
				return mv;
			}
		}
		if (ex instanceof RBCException) {
			mv.addObject("exmsg", ex.getMessage());
		} else {
			mv.addObject("exmsg", "服务器异常，请稍后再试");
		}
		mv.setViewName("error");
		return mv;
	}
}
