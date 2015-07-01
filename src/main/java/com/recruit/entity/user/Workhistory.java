package com.recruit.entity.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 工作经历表
 * 
 * @author xiejinwei
 * 
 */

@Entity
@Table(name = "s_workhistory")
public class Workhistory implements Serializable {

	private static final long serialVersionUID = -4932180967989012449L;

	private String id;
	private String userid;// 用户id
	private String companyname;// 公司名称
	private String positionname;// 职位名称
	private int startyear;// 开始年
	private int startmonth;// 开始月
	private int endyear;// 结束年
	private int endmonth;// 结束月
	private Date createtime;// 添加时间
	private Date updatetime;// 最后一次修改时间

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

	@Column(length = 99, nullable = false)
	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	@Column(length = 33, nullable = false)
	public String getPositionname() {
		return positionname;
	}

	public void setPositionname(String positionname) {
		this.positionname = positionname;
	}

	@Column(length = 4, nullable = false, columnDefinition = "int default 0")
	public int getStartyear() {
		return startyear;
	}

	public void setStartyear(int startyear) {
		this.startyear = startyear;
	}

	@Column(length = 2, nullable = false, columnDefinition = "int default 0")
	public int getStartmonth() {
		return startmonth;
	}

	public void setStartmonth(int startmonth) {
		this.startmonth = startmonth;
	}

	@Column(length = 4, nullable = false, columnDefinition = "int default 0")
	public int getEndyear() {
		return endyear;
	}

	public void setEndyear(int endyear) {
		this.endyear = endyear;
	}

	@Column(length = 2, nullable = false, columnDefinition = "int default 0")
	public int getEndmonth() {
		return endmonth;
	}

	public void setEndmonth(int endmonth) {
		this.endmonth = endmonth;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}
