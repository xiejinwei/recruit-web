package com.recruit.controller.manage;

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
import com.recruit.entity.company.Recommend;
import com.recruit.service.business.CompanyService;
import com.recruit.service.business.RecommendService;
import com.recruit.util.HTMLUtil;
import com.recruit.util.Page;
import com.recruit.util.StringUtil;

@Controller
@RequestMapping("/manage/recommend")
public class RecommendController {

	@Autowired
	private RecommendService recommendService;
	@Autowired
	private CompanyService companyService;

	@Author(author = true)
	@RequestMapping("/list")
	public String list(
			HttpServletRequest request,
			Model model,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "type", defaultValue = "-1") int type) {

		Page page = new Page(pageNo, pageSize);
		List<Recommend> list = recommendService.pageList("from Recommend s ",
				page, "order by s.createtime desc");
		model.addAttribute("list", list);
		String url = StringUtil.getRequestGetUrl(request, "type", type);
		model.addAttribute("url", HTMLUtil.getNumberPageHTML(page, url));
		return "manage/recommend/list";
	}

	/**
	 * 进入选择公司
	 * 
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Author(author = true)
	@RequestMapping(value = "/choosecompany", method = RequestMethod.GET)
	public String choosecompany(HttpServletRequest request, Model model,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		List<Recommend> recommends = recommendService
				.findListByParams("from Recommend");
		model.addAttribute("recommends", recommends);
		Page page = new Page(pageNo, pageSize);
		List<Company> list = companyService.pageList("from Company c", page,
				"order by c.name");
		model.addAttribute("list", list);
		String url = StringUtil.getRequestGetUrl(request);
		model.addAttribute("url", HTMLUtil.getNumberPageHTML(page, url));
		return "manage/recommend/companys";
	}
}
