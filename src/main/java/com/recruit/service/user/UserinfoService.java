package com.recruit.service.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruit.dao.user.UserDao;
import com.recruit.dao.user.UserinfoDao;
import com.recruit.entity.user.User;
import com.recruit.entity.user.Userinfo;
import com.recruit.exception.RBCException;
import com.recruit.service.global.BaseService;

@Service
public class UserinfoService extends BaseService<Userinfo> {

	@Autowired
	private UserinfoDao userinfoDao;
	@Autowired
	private UserDao userDao;
	
	public void updateUser(Userinfo info) {
		Userinfo user = userinfoDao.findById(info.getId(), Userinfo.class);
		if(user==null){
			User u = userDao.findById(info.getId(), User.class);
			if(u==null)
				throw new RBCException("用户信息传递错误");
			user = info;
			userinfoDao.save(user);
		}else{
			//简历名称
			if(info.getResumename()!=null && !info.getResumename().equals(user.getResumename()))
				user.setResumename(info.getResumename());
			//工作年限
			if(info.getWorkyear()!=0 && info.getWorkyear()!=user.getWorkyear())
				user.setWorkyear(info.getWorkyear());
			//工作状态
			if(info.getWorkstatus()!=0 && info.getWorkstatus()!=user.getWorkstatus())
				user.setWorkstatus(info.getWorkstatus());
			userinfoDao.update(user);
		}
		
	}

	public void updateResumename(String id, String resumename) {
		Userinfo user = userinfoDao.findById(id, Userinfo.class);
		if(user==null){
			User u = userDao.findById(id, User.class);
			if(u==null)
				throw new RBCException("用户信息传递错误");
			user = new Userinfo(id);
			user.setResumename(resumename);
			user.setCreatetime(new Date());
			userinfoDao.save(user);
		}else{
			user.setResumename(resumename);
			userinfoDao.update(user);
		}
			
	}

}
