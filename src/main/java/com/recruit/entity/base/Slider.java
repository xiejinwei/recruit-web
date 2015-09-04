package com.recruit.entity.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 轮播数据表
 * 
 * <p>
 * 当管理员配置图播图时，审核时要检查同一位置下是否已经存在正在使用的数据，如果存在，则自动将其弃用
 * </p>
 * 
 * @author xjw
 * 
 */
@Entity
@Table(name = "s_slider")
public class Slider implements Serializable {

	private static final long serialVersionUID = -912502510131448841L;

	/**
	 * 首页
	 */
	public static final int SLIDER_HOME_TOP = 0;

	private String id;
	private int type;// 轮播地方
	private String smimg;// 轮播小图地址
	private String img;// 轮播大图
	private int sort;// 显示位置1:第一个2:第二个3:第三个
	private String alt;//显示文字
	private Date createtime;
	private int status;// 状态0:新增1:使用－9:删除

	@Id
	@Column(length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Column(length = 199)
	public String getSmimg() {
		return smimg;
	}

	public void setSmimg(String smimg) {
		this.smimg = smimg;
	}

	@Column(length = 199)
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(length=55)
	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

}
