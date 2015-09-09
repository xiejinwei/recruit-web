package com.recruit.service.business;

import java.util.Date;
import java.util.List;

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
		type.setId(StringUtil.uuid());
		type.setCreatetime(new Date());
		type.setSort(getSort(type));
		type.setStatus(0);
		positiontypeDao.save(type);
	}

	public void deletePos(String id) {
		Positiontype type = positiontypeDao.findById(id, Positiontype.class);
		if (type == null)
			throw new RBCException("没有找到要删除的信息");
		if (type.getStatus() != 0)
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
		type.setSort(getSort(type));
		positiontypeDao.update(type);
	}

	/**
	 * 生成排序编号
	 * 
	 * @param type
	 * @return
	 */
	private String getSort(Positiontype type) {
		Positiontype preType = null;
		if (!StringUtils.isBlank(type.getParentid())) {
			preType = positiontypeDao
					.findEntityByParams(
							"from Positiontype t where t.level=? and t.parentid=? order by t.sort desc",
							type.getLevel(), type.getParentid());
		} else {
			preType = positiontypeDao.findEntityByParams(
					"from Positiontype t where t.level=? order by t.sort desc",
					type.getLevel());
		}
		if (type.getLevel() == 0) {
			if (preType == null)
				return "1";
			else
				return (Long.parseLong(preType.getSort()) + 1) + "";
		} else {
			if (preType == null) {
				Positiontype parentType = positiontypeDao.findById(
						type.getParentid(), Positiontype.class);
				if (parentType == null)
					throw new RBCException("没有找到大类信息");
				return (Long.parseLong(parentType.getSort()) * 10 + 1) + "";
			} else {
				return preType.getSort() + 1;
			}
		}
	}

	public void audit(String id) {
		Positiontype type = positiontypeDao.findById(id, Positiontype.class);
		if (type == null)
			throw new RBCException("没有找到要操作的数据信息");
		if (type.getStatus() != 0)
			throw new RBCException("只能对未使用的数据进行操作");
		type.setStatus(1);
		//如果是大类，则直接将所有小类全部开启
		if(type.getLevel()==0){
			List<Positiontype> types = positiontypeDao.findListByParams("from Positiontype t where t.parentid=? and t.status=0", type.getId());
			if(types!=null){
				for(Positiontype t : types){
					t.setStatus(1);
					positiontypeDao.update(t);
				}
			}
		}
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
