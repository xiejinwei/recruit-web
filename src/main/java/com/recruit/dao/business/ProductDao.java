package com.recruit.dao.business;

import org.springframework.stereotype.Repository;

import com.recruit.dao.global.BaseDao;
import com.recruit.entity.company.Product;

@Repository("productDao")
public class ProductDao extends BaseDao<Product> {

}
