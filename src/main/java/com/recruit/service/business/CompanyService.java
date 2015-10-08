package com.recruit.service.business;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.recruit.dao.business.CompanyDao;
import com.recruit.dao.business.ProductDao;
import com.recruit.entity.company.Company;
import com.recruit.entity.company.Product;
import com.recruit.entity.user.User;
import com.recruit.exception.RBCException;
import com.recruit.service.global.BaseService;
import com.recruit.util.FileUtil;

@Service
public class CompanyService extends BaseService<Company> {

	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private ProductDao productDao;

	public Company updateLogo(HttpServletRequest request, MultipartFile logo) {
		User user = (User) request.getSession().getAttribute("suser");
		Company company = companyDao.findById(user.getId(), Company.class);
		if (company == null)
			throw new RBCException("请先添加公司信息");
		if (logo == null)
			throw new RBCException("请选择logo图片");
		String path = FileUtil.upladFile(request, logo);
		String url = FileUtil.getServerurl(path);
		if (path != null)
			company.setLogo(url);
		companyDao.update(company);
		return company;
	}

	public void addCompany(User user, String id, String name,String fullname, String slogan) {
		if (StringUtils.isBlank(id)) {
			// 新增
			Company company = companyDao.findById(user.getId(), Company.class);
			if (company != null)
				throw new RBCException("id参数传入错误");
			company = new Company();
			company.setId(user.getId());
			company.setName(name);
			company.setSlogan(slogan);
			company.setFullname(fullname);
			company.setCreatetime(new Date());
			companyDao.save(company);
		} else {
			// 修改
			Company company = companyDao.findById(id, Company.class);
			if (company == null)
				throw new RBCException("没有找到公司信息");
			if (!company.getId().equals(id))
				throw new RBCException("只能操作自己公司的信息");
			company.setName(name);
			company.setFullname(fullname);
			company.setSlogan(slogan);
			companyDao.update(company);
		}
	}

	public String uploadProductImg(HttpServletRequest request, MultipartFile myfiles, int type) {
		if (myfiles.isEmpty())
			throw new RBCException("file is can not empty");
		User user = (User) request.getSession().getAttribute("suser");
		Company company = companyDao.findById(user.getId(), Company.class);
		if (company == null)
			throw new RBCException("请先添加公司信息");
		String path = FileUtil.upladFile(request, myfiles);
		String url = FileUtil.getServerurl(path);
		if (type == 0) {
			// 产品图
			Product product = productDao.findById(company.getId(), Product.class);
			if (product == null) {
				product = new Product();
				product.setId(company.getId());
				product.setImg(url);
				productDao.save(product);
			} else {
				product.setImg(url);
				productDao.update(product);
			}
		} else if (type == 1) {
			company.setMemberimg(url);
			companyDao.update(company);
		}
		return url;
	}

	public void updateCompintro(HttpServletRequest request, String compintro) {
		User user = (User) request.getSession().getAttribute("suser");
		Company company = companyDao.findById(user.getId(), Company.class);
		if (company == null)
			throw new RBCException("请先添加公司信息");
		company.setIntro(compintro);
		companyDao.update(company);
	}

	public void udateCpmpinfo(HttpServletRequest request, String city, String trade, String size, String home) {
		User user = (User) request.getSession().getAttribute("suser");
		Company company = companyDao.findById(user.getId(), Company.class);
		if (company == null)
			throw new RBCException("请先添加公司信息");
		company.setCity(city);
		company.setTrade(trade);
		company.setSize(size);
		company.setHome(home);
		companyDao.update(company);
	}

	public void updateStage(HttpServletRequest request, String stage) {
		User user = (User) request.getSession().getAttribute("suser");
		Company company = companyDao.findById(user.getId(), Company.class);
		if (company == null)
			throw new RBCException("请先添加公司信息");
		company.setStage(stage);
		companyDao.update(company);
	}

	public void updateMemberinfo(HttpServletRequest request, String membername, String job, String weibo,
			String memberinfo) {
		User user = (User) request.getSession().getAttribute("suser");
		Company company = companyDao.findById(user.getId(), Company.class);
		if (company == null)
			throw new RBCException("请先添加公司信息");
		company.setMembername(membername);
		company.setJob(job);
		company.setWeibo(weibo);
		company.setMemberinfo(memberinfo);
		companyDao.update(company);
	}

}
