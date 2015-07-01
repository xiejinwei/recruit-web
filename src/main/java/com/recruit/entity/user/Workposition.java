package com.recruit.entity.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户期望职位与职位信息关联表
 * 
 * @author xiejinwei
 * 
 */

@Entity
@Table(name = "s_workposition")
public class Workposition implements Serializable {

	private static final long serialVersionUID = 4820391907760542870L;

	private String id;
	private String userid;// 用户id
	private String workid;// 用户工作信息id
	private String positionid;// 职位信息id

	@Id
	@Column(length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(length = 32, nullable = false)
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(length = 32, nullable = false)
	public String getWorkid() {
		return workid;
	}

	public void setWorkid(String workid) {
		this.workid = workid;
	}

	@Column(length = 32, nullable = false)
	public String getPositionid() {
		return positionid;
	}

	public void setPositionid(String positionid) {
		this.positionid = positionid;
	}

}
