package com.recruit.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.recruit.entity.company.Company;
import com.recruit.entity.user.User;
import com.recruit.entity.user.Userinfo;
import com.recruit.exception.RBCException;
import com.recruit.service.business.CompanyService;
import com.recruit.service.business.UserService;
import com.recruit.service.user.UserinfoService;
import com.recruit.util.MD5Util;

//用户

@Controller
@RequestMapping("/web/author")
public class AuthorController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserinfoService userinfoService;
	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "web/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, String email,
			String password, Model model) {
		User user = userService.findEntityByParam(
				"from User u where u.email=?", email);
		if (user == null)
			throw new RBCException("当前邮箱还未注册");
		if (!user.getUserpass().equals(MD5Util.MD5(password)))
			throw new RBCException("用户名或密码错误");
		request.getSession().setAttribute("suser", user);
		if (user.getType() == 1) {
			// 公司
			return "redirect:/webcompany/tocompanyhome";
		} else {
			// 用户
			return "redirect:tojianli";
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "web/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String redister(int type, String email, String password, Model model) {
		User user = userService.register(email, password, type);
		if (type == 0) {
			model.addAttribute("user", new Userinfo(user.getId()));
			return "web/jianli";
		} else {
			model.addAttribute("comp", new Company());
			return "web/myhome";
		}
	}


	// 修改用户信息
	@RequestMapping("/updateresumename")
	public String updateresumename(String id, String resumename) {
		if (StringUtils.isBlank(id))
			throw new RBCException("主键不能为空");
		userinfoService.updateResumename(id, resumename);
		return "redirect:tojianli?id=" + id;
	}

	// 添加基本信息
	@RequestMapping("/addinfo")
	public String addBaseinfo(String id, String email) {
		// return "redirect:tojianli?id=" + info.getId();
		return "web/jianli";
	}

	/**
	 * 进入用户简历中心
	 * 
	 * @url /web/author/tojianli
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/tojianli")
	public String toJianli(HttpServletRequest request, Model model) {
		User user = (User) request.getSession().getAttribute("suser");
		if (user==null || user.getType() == 1) {
			return "redirect:/web/author/weblogout";
		}
		Userinfo userinfo = userinfoService.findById(user.getId(),
				Userinfo.class);
		model.addAttribute("user", user);
		model.addAttribute("info", userinfo);
		return "web/jianli";
	}

	/**
	 * 网站用户安全退出
	 * 
	 * @url /web/author/weblogout
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/weblogout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("suser");
		return "redirect:login";
	}

}
