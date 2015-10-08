package com.recruit.service.business;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruit.dao.business.CompanyDao;
import com.recruit.dao.business.ProductDao;
import com.recruit.entity.company.Company;
import com.recruit.entity.company.Product;
import com.recruit.entity.user.User;
import com.recruit.exception.RBCException;
import com.recruit.service.global.BaseService;

@Service
public class ProductService extends BaseService<Product> {
	
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private ProductDao productDao;

	public void updateProduct(HttpServletRequest request, String pname, String pintro, String purl) {
		User user = (User) request.getSession().getAttribute("suser");
		Company company = companyDao.findById(user.getId(), Company.class);
		if (company == null)
			throw new RBCException("请先添加公司信息");
		Product product = productDao.findById(company.getId(), Product.class);
		if(product==null){
			product = new Product();
			product.setId(company.getId());
			product.setIntro(pintro);
			product.setTitle(pname);
			product.setUrl(purl);
			productDao.save(product);
		}else{
			product.setIntro(pintro);
			product.setTitle(pname);
			product.setUrl(purl);
			productDao.update(product);
		}
	}

}
