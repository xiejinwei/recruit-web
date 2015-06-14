package com.recruit.entity.global;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "s_user")
public class User implements Serializable {

	private static final long serialVersionUID = -3809577546127859031L;

	private String id;
	private String username;// 用户名
	private String phone;// 手机号
	private String email;// 电子邮箱
	private Date createtime;// 添加时间
	private String userpass;// 密码
	private String fullname;// 显示名
	private int sex;// 性别 0：男(默认) 1：女
	private String city;// 所在地
	private String img;// 当前使用头像地址
	private int status;// 当前用户状态0:正常 －1:锁定 －9:删除

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

	@Column(length = 99)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(length = 99)
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
