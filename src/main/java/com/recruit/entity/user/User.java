package com.recruit.entity.user;

import java.io.Serializable;

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
	private String email;// 用户名
	private String userpass;// 密码
	private int type;// 0:用户1:公司

	@Id
	@Column(length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(length = 99, nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length = 32, nullable = false)
	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
