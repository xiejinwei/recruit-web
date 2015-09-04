package com.recruit.entity.company;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//创业团队

@Entity
@Table(name = "s_group")
public class Group implements Serializable {

	private static final long serialVersionUID = 162820266713415003L;
	private String id;
	private String cid;// 公司id
	private String username;// 名称
	private String job;// 职位
	private String img;// 头像
	private String intro;// 介绍
	private String home;// 主页

	@Id
	@Column(length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(length = 32)
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@Column(length = 15)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(length = 11)
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Column(length = 199)
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Column(length = 399)
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(length = 199)
	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

}
