package com.recruit.entity.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 教育经历
 * 
 * @author xiejinwei
 * 
 */

@Entity
@Table(name = "s_schoolhistory")
public class Schoolhistory implements Serializable {

	private static final long serialVersionUID = -2593860768784619035L;

	private String id;
	private String userid;// 用户id
	private String schoolname;// 学校名称
	private int educational;// 学历0:其他 1:大专2：本科3:研究生＼硕士4:博士及以上
	private String major;// 专业
	private int startyear;// 开始年
	private int endyear;// 毕业年
	private Date createtime;

	@Id
	@Column(length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(length = 32, nullable = false)
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(length = 55, nullable = false)
	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getEducational() {
		return educational;
	}

	public void setEducational(int educational) {
		this.educational = educational;
	}

	@Column(length = 33)
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(length = 4, nullable = false, columnDefinition = "int default 0")
	public int getStartyear() {
		return startyear;
	}

	public void setStartyear(int startyear) {
		this.startyear = startyear;
	}

	@Column(length = 4, nullable = false, columnDefinition = "int default 0")
	public int getEndyear() {
		return endyear;
	}

	public void setEndyear(int endyear) {
		this.endyear = endyear;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
