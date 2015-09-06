package com.recruit.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.recruit.entity.position.Position;
import com.recruit.entity.user.User;
import com.recruit.service.business.PositionService;

@Controller
@RequestMapping("/webposition")
public class PositionController {

	@Autowired
	private PositionService positionService;

	/**
	 * 职位列表
	 * 
	 * @url /webposition/positions
	 * 
	 * @param request
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param type
	 * @param name
	 * @return
	 */
	@RequestMapping("/positions")
	public String positions(
			HttpServletRequest request,
			Model model,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "type", defaultValue = "0") int type,
			String name) {
		model.addAttribute("type", type);
		return "web/list";
	}

	/**
	 * 进入新增职位
	 * @url /webposition/create
	 * 
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createPostion() {
		return "web/createposition";
	}
	
	//保存职位
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPostion(HttpServletRequest request,Position position){
		User user = (User) request.getSession().getAttribute("suser");
		if(user ==null || user.getType()!=1)
			return "redirect:/web/author/weblogout";
		positionService.addPostion(user,position);
		return "redirect:positions";
	}
}
