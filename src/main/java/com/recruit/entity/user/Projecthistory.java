package com.recruit.entity.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 项目经验
 * 
 * @author xiejinwei
 * 
 */

@Entity
@Table(name = "s_projecthistory")
public class Projecthistory implements Serializable {

	private static final long serialVersionUID = 5709515157971872053L;

	private String id;
	private String userid;// 用户id
	private String projectname;// 项目名称
	private String projectposition;// 担作职位
	private int startyear;// 开始年
	private int startmonth;// 开始月
	private int endyear;// 完成年
	private int endmonth;// 完成月
	private String projectdescription;// 项目介绍
	private Date createtime;// 添加时间
	private Date updatetime;// 修改时间

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
	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	@Column(length = 55, nullable = false)
	public String getProjectposition() {
		return projectposition;
	}

	public void setProjectposition(String projectposition) {
		this.projectposition = projectposition;
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

	@Column(length = 500, nullable = false)
	public String getProjectdescription() {
		return projectdescription;
	}

	public void setProjectdescription(String projectdescription) {
		this.projectdescription = projectdescription;
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
