package com.recruit.service.business;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruit.dao.business.PositiontypeDao;
import com.recruit.entity.position.Positiontype;
import com.recruit.exception.RBCException;
import com.recruit.service.global.BaseService;
import com.recruit.util.StringUtil;

@Service
public class PositiontypeService extends BaseService<Positiontype> {

	@Autowired
	private PositiontypeDao positiontypeDao;

	public void add(Positiontype type) {
		if (StringUtils.isBlank(type.getName()))
			throw new RBCException("名称不能为空");
		if (type.getLevel() == -1)
			throw new RBCException("类型级别为必选项");
		if (type.getLevel() == 0)
			type.setParentid(null);
		if (type.getLevel() != 0 && StringUtils.isBlank(type.getParentid()))
			throw new RBCException("非一级类型必须要有上级分类");
		if (StringUtils.isBlank(type.getSort()))
			throw new RBCException("请输入排序信息");
		type.setId(StringUtil.uuid());
		type.setCreatetime(new Date());
		type.setStatus(0);
		positiontypeDao.save(type);
	}

	public void deletePos(String id) {
		Positiontype type = positiontypeDao.findById(id, Positiontype.class);
		if (type == null)
			throw new RBCException("没有找到要删除的信息");
		if(type.getStatus()!=0)
			throw new RBCException("只能对未使用的数据进行删除操作");
		type.setStatus(-9);
		positiontypeDao.update(type);
	}

	public void updatePos(Positiontype t) {
		Positiontype type = positiontypeDao.findById(t.getId(),
				Positiontype.class);
		if (type == null)
			throw new RBCException("没有找到要修改的数据信息");
		if (type.getStatus() != 0)
			throw new RBCException("只能对未使用的数据进行修改");
		type.setName(t.getName());
		if ("".equals(type.getParentid())) {
			type.setParentid(null);
		} else {
			type.setParentid(t.getParentid());
		}
		type.setLevel(t.getLevel());
		if (StringUtils.isBlank(t.getSort()))
			throw new RBCException("请输入排序信息");
		type.setSort(t.getSort());
		positiontypeDao.update(type);
	}

	public void audit(String id) {
		Positiontype type = positiontypeDao.findById(id, Positiontype.class);
		if (type == null)
			throw new RBCException("没有找到要操作的数据信息");
		if (type.getStatus() != 0)
			throw new RBCException("只能对未使用的数据进行操作");
		type.setStatus(1);
		positiontypeDao.update(type);
	}

	public void unAudit(String id) {
		Positiontype type = positiontypeDao.findById(id, Positiontype.class);
		if (type == null)
			throw new RBCException("没有找到要操作的数据信息");
		if (type.getStatus() != 1)
			throw new RBCException("只能对正在使用的数据进行操作");
		type.setStatus(0);
		positiontypeDao.update(type);
	}

}
