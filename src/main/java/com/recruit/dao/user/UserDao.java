package com.recruit.dao.user;

import org.springframework.stereotype.Repository;

import com.recruit.dao.global.BaseDao;
import com.recruit.entity.user.User;

@Repository("userDao")
public class UserDao extends BaseDao<User> {

}
