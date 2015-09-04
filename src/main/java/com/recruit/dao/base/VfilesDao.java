package com.recruit.dao.base;


import java.util.Date;

import org.springframework.stereotype.Repository;

import com.recruit.dao.global.BaseDao;
import com.recruit.entity.user.Vfiles;
import com.recruit.util.StringUtil;

@Repository("vfilesDao")
public class VfilesDao extends BaseDao<Vfiles>{

	public void add(String fileName,int issm,String path,String srcid,int type,String url) {
		Vfiles file = new Vfiles();
		file.setId(StringUtil.uuid());
		file.setCreatetime(new Date());
		file.setFilename(fileName);
		file.setIssm(issm);
		file.setPath(path);
		file.setSrcid(srcid);
		file.setType(type);
		file.setUrl(url);
		save(file);
	}

}
