package com.recruit.service.business;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.recruit.dao.business.CompanyDao;
import com.recruit.entity.company.Company;
import com.recruit.entity.user.User;
import com.recruit.exception.RBCException;
import com.recruit.service.global.BaseService;
import com.recruit.util.FileUtil;

@Service
public class CompanyService extends BaseService<Company> {
	
	@Autowired
	private CompanyDao companyDao;

	public Company updateLogo(HttpServletRequest request, MultipartFile logo) {
		User user = (User) request.getSession().getAttribute("suser");
		Company company = companyDao.findById(user.getId(), Company.class);
		if(company==null)
			throw new RBCException("请先添加公司信息");
		if(logo==null)
			throw new RBCException("请选择logo图片");
		String path = FileUtil.upladFile(request, logo);
		String url = FileUtil.getServerurl(path);
		if(path!=null)
			company.setLogo(url);
		companyDao.update(company);
		return company;
	}

	public void addCompany(User user, String id, String name, String slogan) {
		if(StringUtils.isBlank(id)){
			//新增
			Company company = companyDao.findById(user.getId(), Company.class);
			if(company!=null)
				throw new RBCException("id参数传入错误");
			company = new Company();
			company.setId(user.getId());
			company.setName(name);
			company.setSlogan(slogan);
			company.setCreatetime(new Date());
			companyDao.save(company);
		}else{
			//修改
			Company company = companyDao.findById(id, Company.class);
			if(company==null)
				throw new RBCException("没有找到公司信息");
			if(!company.getId().equals(id))
				throw new RBCException("只能操作自己公司的信息");
			company.setName(name);
			company.setSlogan(slogan);
			companyDao.update(company);
		}
	}
	
}
