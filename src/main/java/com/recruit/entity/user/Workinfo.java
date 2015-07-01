package com.recruit.entity.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户工作信息表
 * 
 * @author xiejinwei
 * 
 */

@Entity
@Table(name = "s_work")
public class Workinfo implements Serializable {

	private static final long serialVersionUID = -605368992942474995L;

	private String id;
	private String userid;// 用户id
	private String workcity;// 期望工作城市
	private int worktype;// 工作类型0:全职1:兼职2:实习
	private int worksalary;// 期望薪资 0:2k以下 1:2k~5k 2:5k~10k 3:10k~20k 4:20k以上
	private Date createtime;// 数据添加时间
	private Date updatetime;// 最后一次更改时间

	@Id
	@Column(length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(length = 32)
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(length = 33)
	public String getWorkcity() {
		return workcity;
	}

	public void setWorkcity(String workcity) {
		this.workcity = workcity;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getWorktype() {
		return worktype;
	}

	public void setWorktype(int worktype) {
		this.worktype = worktype;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getWorksalary() {
		return worksalary;
	}

	public void setWorksalary(int worksalary) {
		this.worksalary = worksalary;
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
