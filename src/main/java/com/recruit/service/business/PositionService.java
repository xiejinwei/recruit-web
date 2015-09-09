package com.recruit.service.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruit.dao.business.PositionDao;
import com.recruit.dao.business.PositiontypeDao;
import com.recruit.entity.position.Position;
import com.recruit.entity.position.Positiontype;
import com.recruit.entity.user.User;
import com.recruit.exception.RBCException;
import com.recruit.service.global.BaseService;
import com.recruit.util.StringUtil;

@Service
public class PositionService extends BaseService<Position> {

	@Autowired
	private PositionDao positionDao;
	@Autowired
	private PositiontypeDao positiontypeDao;

	public void addPostion(User user, Position position) {
		position.setId(StringUtil.uuid());
		position.setCompanyid(user.getId());
		Positiontype type = positiontypeDao.findEntityByParams("from Positiontype t where t.name=?", position.getTypename());
		if(type==null)
			throw new RBCException("没有找到当前职业类型");
		position.setPositiontypeid(type.getId());
		position.setCreatetime(new Date());
		positionDao.save(position);
	}
	
}
