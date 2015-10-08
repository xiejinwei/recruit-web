package com.recruit.entity.company;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//公司产品
@Entity
@Table(name = "s_product")
public class Product implements Serializable {

	private static final long serialVersionUID = 2816034445903785989L;

	private String id;// 公司id
	private String cid;
	private String img;// 产品图片
	private String title;// 产品名称
	private String url;// 网址
	private String intro;// 简介

	@Id
	@Column(length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(length = 32, nullable = false)
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@Column(length = 199)
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Column(length = 55)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(length = 199)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(length = 500)
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

}
