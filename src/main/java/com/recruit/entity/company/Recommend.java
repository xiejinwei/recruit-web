package com.recruit.entity.company;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 推荐企业
 * 
 * @author xiejinwei
 * 
 */
@Entity
@Table(name="s_recommend")
public class Recommend implements Serializable {

	private static final long serialVersionUID = 3283269509154850855L;

	private String id;// 公司id
	private String cname;// 公司名称
	private String logo;// 公司logo
	private String slogan;// 公司口号
	private Date createtime;// 添加时间

	@Id
	@Column(length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(length = 5, nullable = false)
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(length = 199)
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(length = 33)
	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
