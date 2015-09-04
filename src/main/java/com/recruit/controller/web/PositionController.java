package com.recruit.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		if (type == 0) {

		} else if (type == 1) {

		} else if (type == 2) {

		}
		return "web/list";
	}

}
