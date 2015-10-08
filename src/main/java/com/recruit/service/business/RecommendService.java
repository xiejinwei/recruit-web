package com.recruit.service.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruit.dao.business.CompanyDao;
import com.recruit.dao.business.RecommendDao;
import com.recruit.entity.company.Company;
import com.recruit.entity.company.Recommend;
import com.recruit.exception.RBCException;
import com.recruit.service.global.BaseService;
import com.recruit.util.StringUtil;

@Service
public class RecommendService extends BaseService<Recommend> {

	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private RecommendDao recommendDao;
	
	public void addRecommend(String cid) {
		Company company = companyDao.findById(cid, Company.class);
		if(company==null)
			throw new RBCException("没有找到公司信息");
		Recommend old = recommendDao.findById(cid, Recommend.class);
		if(old!=null)
			throw new RBCException("当前公司已添加入推广");
		Recommend recommend = new Recommend();
		recommend.setId(company.getId());
		recommend.setCname(company.getName());
		recommend.setCreatetime(new Date());
		recommend.setLogo(company.getLogo());
		recommend.setSlogan(company.getSlogan());
		recommendDao.save(recommend);
	}

	public void deleteRecommend(String id) {
		Recommend recommend = recommendDao.findById(id, Recommend.class);
		if(recommend==null)
			throw new RBCException("没有找到要删除的信息");
		recommendDao.delete(recommend);
	}

}
