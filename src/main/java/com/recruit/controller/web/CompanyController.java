package com.recruit.controller.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.recruit.aspact.Author;
import com.recruit.entity.company.Company;
import com.recruit.entity.company.Product;
import com.recruit.entity.position.Position;
import com.recruit.entity.user.User;
import com.recruit.exception.RBCException;
import com.recruit.service.business.CompanyService;
import com.recruit.service.business.PositionService;
import com.recruit.service.business.PositiontypeService;
import com.recruit.service.business.ProductService;
import com.recruit.util.Page;

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
	@Autowired
	private PositiontypeService positiontypeService;
	@Autowired
	private PositionService positionService;
	@Autowired
	private ProductService productService;

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
	public String toCompanyHome(HttpServletRequest request, Model model) {
		User user = (User) request.getSession().getAttribute("suser");
		if (user == null || user.getType() == 0) {
			return "redirect:/web/author/weblogout";
		}
		// 公司
		Company company = companyService.findById(user.getId(), Company.class);
		if (company == null)
			company = new Company();
		model.addAttribute("comp", company);
		// 产品
		Product product = productService.findById(company.getId(), Product.class);
		model.addAttribute("product", product);
		return "web/myhome";
	}

	// 上传公司logo
	@Author(author = true)
	@RequestMapping("/updatelogo")
	public String updatelogo(HttpServletRequest request, Model model,
			@RequestParam(value = "logo") MultipartFile logo) {
		Company company = companyService.updateLogo(request, logo);
		model.addAttribute("comp", company);
		return "web/myhome";

	}

	/**
	 * 添加公司基本信息
	 * 
	 * @url /webcompany/addbaseinfo
	 * @param request
	 * @param model
	 * @param id
	 * @param name
	 * @param slogan
	 * @return
	 */
	@Author(author = true)
	@RequestMapping("/addbaseinfo")
	public String addbaseinfo(HttpServletRequest request, Model model, String id,String fullname, String name, String slogan) {
		User user = (User) request.getSession().getAttribute("suser");
		if (user.getType() != 1)
			throw new RBCException("非企业用户不能维护企业信息");
		companyService.addCompany(user, id, name, fullname,slogan);
		return "redirect:tocompanyhome";
	}

	// 添加公司产品信息
	@Author(author = true)
	@RequestMapping("/addproduct")
	public String updatecompbase(HttpServletRequest request, String pname, String purl, String pintro) {
		productService.updateProduct(request, pname, pintro, purl);
		return "redirect:tocompanyhome";
	}

	// 上传公司大图
	@ResponseBody
	@Author(author = true)
	@RequestMapping("/uploadcompimg")
	public String uploadcompimg(HttpServletRequest request, @RequestParam(value = "myfiles") MultipartFile myfiles,
			@RequestParam(value = "type", defaultValue = "0") int type) {
		return companyService.uploadProductImg(request, myfiles, type);
	}

	// 修改公司简介
	@Author(author = true)
	@RequestMapping("/updateintro")
	public String updateintro(HttpServletRequest request, String compintro) {
		companyService.updateCompintro(request, compintro);
		return "redirect:tocompanyhome";
	}

	// 修改公司规模等
	@Author(author = true)
	@RequestMapping("/updatecompinfo")
	public String updatecompinfo(HttpServletRequest request, String city, String trade, String size, String home) {
		companyService.udateCpmpinfo(request, city, trade, size, home);
		return "redirect:tocompanyhome";
	}

	// 融资情问
	@Author(author = true)
	@RequestMapping("/stageform")
	public String stageform(HttpServletRequest request, String stage) {
		companyService.updateStage(request, stage);
		return "redirect:tocompanyhome";
	}

	// 公司始人信息
	@Author(author = true)
	@RequestMapping("/memberform")
	public String memberform(HttpServletRequest request, String membername, String job, String weibo,
			String memberinfo) {
		companyService.updateMemberinfo(request, membername, job, weibo, memberinfo);
		return "redirect:tocompanyhome";
	}

	/**
	 * 获取公司职位列表
	 * 
	 * @url /webcompany/positions
	 * 
	 * @param request
	 * @return
	 */
	@Author(author = true)
	@RequestMapping("/positions")
	public String positions(HttpServletRequest request,Model model,@RequestParam(value="pageNo",defaultValue="1")int pageNo,@RequestParam(value="pageSize",defaultValue="10")int pageSize) {
		//TODO
		List<Position> positions = positionService.pageList("from Position p", new Page(pageNo, pageSize), " order by p.createtime desc", "");
		return "web/positions";
	}

}
