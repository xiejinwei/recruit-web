package com.recruit.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.recruit.aspact.Author;
import com.recruit.entity.company.Company;
import com.recruit.entity.user.User;
import com.recruit.service.business.CompanyService;

/**
 * 公司控制器
 * 
 * @author xiejinwei
 * 
 */
@Controller
@RequestMapping("/webcompany")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	/**
	 * 进入公司中心
	 * 
	 * @url /webcompany/tocompanyhome
	 * 
	 * @param request
	 * @return
	 */
	@Author(author = true)
	@RequestMapping("/tocompanyhome")
	public String toCompanyHome(HttpServletRequest request,Model model) {
		User user = (User) request.getSession().getAttribute("suser");
		Company company = companyService.findById(user.getId(), Company.class);
		if (company == null)
			company = new Company(user.getId());
		model.addAttribute("comp", company);
		return "web/myhome";
	}
	
	// 上传公司logo
	@Author(author = true)
	@RequestMapping("/updatelogo")
	public String updatelogo(HttpServletRequest request,Model model,@RequestParam(value = "logo") MultipartFile logo) {
		Company company = companyService.updateLogo(request,logo);
		model.addAttribute("comp", company);
		return "web/myhome";
		
	}

	@RequestMapping("/addbaseinfo")
	public String addbaseinfo(Model model, Company c) {
		return "";
	}

}
