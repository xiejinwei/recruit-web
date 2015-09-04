package com.recruit.entity.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户信息表
 * 
 * @author xiejinwei
 * 
 */
@Entity
@Table(name = "s_userinfo")
public class Userinfo implements Serializable {

	private static final long serialVersionUID = -1787667674099217949L;

	private String id;// 用户id
	private String fullname;// 真实姓名
	private int gender;// 性别 0：男(默认) 1：女
	private int education;// 学历 0:大专1:本科2:硕士3:博士4:其他
	private int workyear;// 工作年限0:应届 1:1〜3年 2:3〜5年 3:5年以上
	private String phone;// 手机号
	private String email;// 接收面试的邮箱
	private int workstatus;// 目前工作状态0:离职1：在职2:不找
	private String faceurl;// 头象
	private int userstatus;// 用户状态
	private String seldescription;// 自我描述
	private int viewtime;// 浏览次数
	private int delivertime;// 投递次数
	private String resumename;// 简历名称
	private Date createtime;// 添加时间

	public Userinfo() {
		super();
	}

	public Userinfo(String id) {
		super();
		this.id = id;
	}

	@Id
	@Column(length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(length = 15)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(length = 55)
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getEducation() {
		return education;
	}

	public void setEducation(int education) {
		this.education = education;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getWorkyear() {
		return workyear;
	}

	public void setWorkyear(int workyear) {
		this.workyear = workyear;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getWorkstatus() {
		return workstatus;
	}

	public void setWorkstatus(int workstatus) {
		this.workstatus = workstatus;
	}

	@Column(length = 199)
	public String getFaceurl() {
		return faceurl;
	}

	public void setFaceurl(String faceurl) {
		this.faceurl = faceurl;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(int userstatus) {
		this.userstatus = userstatus;
	}

	@Column(length = 500)
	public String getSeldescription() {
		return seldescription;
	}

	public void setSeldescription(String seldescription) {
		this.seldescription = seldescription;
	}

	@Column(length = 4, nullable = false, columnDefinition = "int default 0")
	public int getViewtime() {
		return viewtime;
	}

	public void setViewtime(int viewtime) {
		this.viewtime = viewtime;
	}

	@Column(length = 4, nullable = false, columnDefinition = "int default 0")
	public int getDelivertime() {
		return delivertime;
	}

	public void setDelivertime(int delivertime) {
		this.delivertime = delivertime;
	}

	@Column(length = 15)
	public String getResumename() {
		return resumename;
	}

	public void setResumename(String resumename) {
		this.resumename = resumename;
	}

	@Column(length = 99)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
