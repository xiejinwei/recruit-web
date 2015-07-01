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
@Table(name = "s_user")
public class User implements Serializable {

	private static final long serialVersionUID = -3809577546127859031L;

	private String id;
	private String username;// 用户名
	private String userpass;// 密码
	private String fullname;// 真实姓名
	private int sex;// 性别 0：男(默认) 1：女
	private String education;// 学历 0:大专1:本科2:硕士3:博士4:其他
	private String workyear;// 工作年限0:应届 1:1〜3年 2:3〜5年 3:5年以上
	private String phone;// 手机号
	private String email;// 电子邮箱
	private int workstatus;// 目前工作状态0:离职1：在职2:不找
	private String faceurl;// 头象
	private int userstatus;// 用户状态
	private String seldescription;// 自我描述
	private int viewtime;// 浏览次数
	private int delivertime;// 投递次数
	private Date createtime;// 添加时间

	@Id
	@Column(length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(length = 33, nullable = false, unique = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(length = 15, nullable = false, unique = true)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(length = 55)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(length = 32, nullable = false)
	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	@Column(length = 55)
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public String getWorkyear() {
		return workyear;
	}

	public void setWorkyear(String workyear) {
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

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof User))
			return false;
		final User s = (User) obj;
		if (getId().equals(s.getId()))
			return true;
		return false;
	}
}
