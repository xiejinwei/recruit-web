package com.recruit.entity.position;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 招聘职位
 * 
 * @author xiejinwei
 * 
 */

@Entity
@Table(name = "s_position")
public class Position implements Serializable {

	private static final long serialVersionUID = 7893856183062980075L;

	private String id;
	private String name;// 职位名称
	private String companyid;// 发布公司id
	private String positiontypeid;// 职位类别id
	private String department;// 部门
	private int workproperty;// 工作性质0:全职1:兼职2:学生（实习）
	private int minmonthsalary;// 最低月薪
	private int maxmonthsalary;// 最高月薪
	private String workcity;// 工作城市
	private int workexperience;// 工作经验 0:不限 1:应届毕业生 2:1年以内 3:1〜3年 4:3〜5年 5:5〜10年 6:10年以上
	private int degrees;// 学历：0:不限 1:大专 2:本科 3:硕士 4:博士及以上
	private String positionkeyword;// 职位诱惑（关键字）
	private String positioninfo;// 职位描述
	private String positionaddress;// 工作地址
	private String positionemail;// 简历接收邮箱
	private Date createtime;// 添加时间
	private Date publishtime;// 发布时间

	@Id
	@Column(length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(length = 33, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 32, nullable = false)
	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	@Column(length = 32, nullable = false)
	public String getPositiontypeid() {
		return positiontypeid;
	}

	public void setPositiontypeid(String positiontypeid) {
		this.positiontypeid = positiontypeid;
	}

	@Column(length = 33, nullable = false)
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(length = 11)
	public int getWorkproperty() {
		return workproperty;
	}

	public void setWorkproperty(int workproperty) {
		this.workproperty = workproperty;
	}

	@Column(length = 3, nullable = false, columnDefinition = "int default 0")
	public int getMinmonthsalary() {
		return minmonthsalary;
	}

	public void setMinmonthsalary(int minmonthsalary) {
		this.minmonthsalary = minmonthsalary;
	}

	@Column(length = 3, nullable = false, columnDefinition = "int default 0")
	public int getMaxmonthsalary() {
		return maxmonthsalary;
	}

	public void setMaxmonthsalary(int maxmonthsalary) {
		this.maxmonthsalary = maxmonthsalary;
	}

	@Column(length = 11)
	public String getWorkcity() {
		return workcity;
	}

	public void setWorkcity(String workcity) {
		this.workcity = workcity;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getWorkexperience() {
		return workexperience;
	}

	public void setWorkexperience(int workexperience) {
		this.workexperience = workexperience;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getDegrees() {
		return degrees;
	}

	public void setDegrees(int degrees) {
		this.degrees = degrees;
	}

	@Column(length = 99)
	public String getPositionkeyword() {
		return positionkeyword;
	}

	public void setPositionkeyword(String positionkeyword) {
		this.positionkeyword = positionkeyword;
	}

	@Lob
	public String getPositioninfo() {
		return positioninfo;
	}

	public void setPositioninfo(String positioninfo) {
		this.positioninfo = positioninfo;
	}

	@Column(length = 55)
	public String getPositionaddress() {
		return positionaddress;
	}

	public void setPositionaddress(String positionaddress) {
		this.positionaddress = positionaddress;
	}

	@Column(length = 199)
	public String getPositionemail() {
		return positionemail;
	}

	public void setPositionemail(String positionemail) {
		this.positionemail = positionemail;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}

}
