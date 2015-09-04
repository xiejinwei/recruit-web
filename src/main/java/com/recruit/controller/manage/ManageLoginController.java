package com.recruit.controller.manage;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.recruit.entity.user.User;
import com.recruit.exception.RBCException;
import com.recruit.service.business.UserService;
import com.recruit.util.MD5Util;

@Controller
@RequestMapping("/manage")
public class ManageLoginController {

	@Autowired
	private UserService userService;

	// 进入后台登录
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		return "manage/login";
	}

	// 登录，验证并跳转
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, String username,
			String userpass, Model model) {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(userpass)) {
			model.addAttribute("error", "用户名和密码不能为空");
			return "manage/login";
		}
		User user = userService.findByName("from User u where u.email=?",
				username);
		if (user == null || !MD5Util.MD5(userpass).equals(user.getUserpass())) {
			model.addAttribute("error", "用户名或密码错误");
			model.addAttribute("username", username);
			return "manage/login";
		}
		request.getSession().setAttribute("suser", user);
		return "redirect:/manage/positiontype/list";
	}

	// 后台管理首页
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("suser");
		if (user == null) {
			return "manage/login";
		}
		return "manage/index";
	}

	// 安全退出
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("suser");
		if (user == null)
			throw new RBCException("未登录不能执行退出登录操作");
		request.getSession().removeAttribute("suser");
		return "manage/login";
	}
}
