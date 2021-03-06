package com.recruit.service.business;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recruit.dao.user.UserDao;
import com.recruit.dao.user.UserinfoDao;
import com.recruit.entity.user.User;
import com.recruit.entity.user.Userinfo;
import com.recruit.exception.RBCException;
import com.recruit.service.global.BaseService;
import com.recruit.util.MD5Util;
import com.recruit.util.StringUtil;

@Service
public class UserService extends BaseService<User> {
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserinfoDao userinfoDao;

	@Transactional
	public User register(String email, String password,int type) {
		if(StringUtils.isBlank(email))
			throw new RBCException("邮箱地址不能为空");
		if(StringUtils.isBlank(password))
			throw new RBCException("密码不能为空");
		User u = userDao.findSingleEntityByParam("from User u where u.email=?", email.trim());
		if(u!=null)
			throw new RBCException("邮箱地址已被注册");
		User user = new User();
		user.setId(StringUtil.uuid());
		user.setEmail(email);
		user.setUserpass(MD5Util.MD5(password));
		user.setType(type);
		userDao.save(user);
		if(user.getType()==0){
			Userinfo info = new Userinfo();
			info.setId(user.getId());
			info.setCreatetime(new Date());
			info.setUpdatetime(new Date());
			info.setUserstatus(0);
			userinfoDao.save(info);
		}
		return user;
	}

	
}
