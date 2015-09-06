package com.recruit.entity.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户职位收藏表
 * 
 * @author xiejinwei
 * 
 */

@Entity
@Table(name = "s_collection")
public class Collections implements Serializable {

	private static final long serialVersionUID = 8675517382117468470L;

	private String id;
	private String userid;
	private String posionid;
	private String posionname;// 职位名称
	private String companyname;// 公司名称
	private Date createtime;// 收藏时间

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
	public String getPosionid() {
		return posionid;
	}

	public void setPosionid(String posionid) {
		this.posionid = posionid;
	}

	@Column(length = 33, nullable = false)
	public String getPosionname() {
		return posionname;
	}

	public void setPosionname(String posionname) {
		this.posionname = posionname;
	}

	@Column(length = 55, nullable = false)
	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
