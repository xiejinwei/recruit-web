package com.recruit.entity.position;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 职位类别
 * 
 * @author xiejinwei
 * 
 */

@Entity
@Table(name = "s_positiontype")
public class Positiontype implements Serializable {

	private static final long serialVersionUID = -6722666132703727189L;

	private String id;
	private String name;// 职位名称
	private int level;// 职位级别0:一级1：二级2:三级
	private String parentid;// 上级id
	private int status;// 使用状态0:未使用1:使用中－9:已删除
	private Date createtime;

	@Id
	@Column(length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(length = 33, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Column(length = 32)
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	@Column(length = 1, nullable = false, columnDefinition = "int default 0")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
