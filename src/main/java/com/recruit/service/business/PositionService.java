package com.recruit.service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruit.dao.business.PositionDao;
import com.recruit.entity.position.Position;
import com.recruit.service.global.BaseService;

@Service
public class PositionService extends BaseService<Position> {

	@Autowired
	private PositionDao positionDao;
	
}
