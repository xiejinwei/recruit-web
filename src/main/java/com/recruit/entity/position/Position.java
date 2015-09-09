package com.recruit.entity.position;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	private int type;// 0:行政1:社会2:校园
	private String companyid;// 发布公司id
	private String positiontypeid;// 职位类别id
	private String typename;// 职位类型名称
	private String department;// 部门
	private int workproperty;// 工作性质0:全职1:兼职2:学生（实习）
	private int minmonthsalary;// 最低月薪
	private int maxmonthsalary;// 最高月薪
	private String workcity;// 工作城市
	private String workexperience;// 工作经验
	private String degrees;// 学历：
	private String positionkeyword;// 职位诱惑（关键字）
	private String positioninfo;// 职位描述
	private String positionaddress;// 工作地址
	private String positionemail;// 简历接收邮箱
	private Date createtime;// 添加时间
	private Date publishtime;// 发布时间
	private int curr;// 是否热门

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

	@Column(length = 11, nullable = false, columnDefinition = "int default 0")
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

	@Column(length = 7)
	public String getWorkexperience() {
		return workexperience;
	}

	public void setWorkexperience(String workexperience) {
		this.workexperience = workexperience;
	}

	@Column(length = 7)
	public String getDegrees() {
		return degrees;
	}

	public void setDegrees(String degrees) {
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

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getCurr() {
		return curr;
	}

	public void setCurr(int curr) {
		this.curr = curr;
	}

	@Transient
	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
