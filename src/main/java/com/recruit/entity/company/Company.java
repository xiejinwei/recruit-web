package com.recruit.entity.company;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 企业＼用人单位
 * 
 * @author xiejinwei
 * 
 */
@Entity
@Table(name = "s_company")
public class Company implements Serializable {

	private static final long serialVersionUID = -1092506694269758655L;

	private String id;// 用户id
	private String logo;// 公司logo
	private String name;// 公司名称
	private String fullname;// 全称
	private String slogan;// 口号
	private int auther;// 是否认证 0:否 1:是
	private String trade;// 领域
	private int size;// 规模（0:0〜50 1:50〜150 2:150〜300 3:300〜500 4:500〜10000
						// 5:1000以上）
	private String home;// 主页
	private String stage;// 目前阶段
	private String investors;// 投资机构
	private String address;// 工作地址
	private String city;// 所在城市
	private String intro;// 企业简介
	private String telephone;// 座机
	private Date createtime;

	@Id
	@Column(length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(length = 199)
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(length = 55, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 55)
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(length = 33)
	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getAuther() {
		return auther;
	}

	public void setAuther(int auther) {
		this.auther = auther;
	}

	@Column(length = 55)
	public String getTrade() {
		return trade;
	}

	public void setTrade(String trade) {
		this.trade = trade;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Column(length = 199)
	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	@Column(length = 33)
	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	@Column(length = 55)
	public String getInvestors() {
		return investors;
	}

	public void setInvestors(String investors) {
		this.investors = investors;
	}

	@Column(length = 55)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(length = 10)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(length = 1000)
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(length = 16)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
