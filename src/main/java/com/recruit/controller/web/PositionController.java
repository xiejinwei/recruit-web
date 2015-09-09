package com.recruit.controller.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.recruit.aspact.Author;
import com.recruit.entity.company.Company;
import com.recruit.entity.position.Position;
import com.recruit.entity.position.Positiontype;
import com.recruit.entity.user.User;
import com.recruit.exception.RBCException;
import com.recruit.service.business.CompanyService;
import com.recruit.service.business.PositionService;
import com.recruit.service.business.PositiontypeService;

@Controller
@RequestMapping("/webposition")
public class PositionController {

	@Autowired
	private PositionService positionService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private PositiontypeService positiontypeService;

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
	 * 进入发布职位页面
	 * 
	 * @url /webposition/create
	 * 
	 * @param request
	 * @return
	 */
	@Author(author = true)
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(HttpServletRequest request, Model model) {
		User user = (User) request.getSession().getAttribute("suser");
		if (user.getType() != 1)
			throw new RBCException("非企业用户不能发布招聘信息");
		Company company = companyService.findById(user.getId(), Company.class);
		model.addAttribute("comp", company);
		List<Positiontype> types = positiontypeService
				.findListByParams("from Positiontype t where t.status=1");
		model.addAttribute("types", types);
		// 获取该公司下所有已发布的岗位
		List<Position> positions = positionService.findListByParams(
				"from Position p where p.companyid=?", company.getId());
		model.addAttribute("positions", positions);
		return "web/createposition";
	}

	/**
	 * 保存职位
	 * 
	 * @url /webposition/save
	 * @param request
	 * @param position
	 * @return
	 */
	@Author(author = true)
	@RequestMapping("/save")
	public String create(HttpServletRequest request, Position position) {
		User user = (User) request.getSession().getAttribute("suser");
		if (user == null || user.getType() != 1)
			return "redirect:/web/author/weblogout";
		positionService.addPostion(user, position);
		return "redirect:positions?type="+position.getType();
	}
}
