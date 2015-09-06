package com.recruit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.recruit.exception.RBCException;

public class ExceptionHanddler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex){
		ModelAndView mv = null;
		if (ex instanceof RBCException) {
			if(ex.getMessage().equals("weblogout")){
				mv = new ModelAndView("redirect:/web/author/weblogout");
				return mv;
			}else if(ex.getMessage().equals("logout")){
				mv = new ModelAndView("redirect:/manage/logout");
				return mv;
			}
		}
		mv = new ModelAndView();
		if (ex instanceof RBCException) {
			mv.addObject("exmsg", ex.getMessage());
		} else {
			mv.addObject("exmsg", "服务器异常，请稍后再试");
		}
		mv.setViewName("error");
		return mv;
	}
}
