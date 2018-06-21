package com.senpure.base.entity;


import com.senpure.base.PermissionConstant;
import com.senpure.base.annotation.Explain;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = PermissionConstant.DATA_BASE_PREFIX + "_CONTAINER")
public class Container extends VersionEntity{
	private static final long serialVersionUID = -6627339494431946014L;
	@Id
	@GenericGenerator(name = "hibernate", strategy = "assigned")
	private Integer id;
	/**
	 * 父容器，实体
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "parentId", nullable = true)
	@Explain("父容器ID")
	private Container parent;
	@Column(nullable = false, length = 32)
	private String name;
	private String description;
	private Integer level ;
	@Column(nullable = false)
	private Long relation;
	@Column(nullable = false)
	private Long createTime;
	@Column(nullable = false)
	private Date createDate;
	/**
	 * 所属容器层次结构,主要是为了查询,多有用like查询 -1-2-3-12-
	 *
	 */
	@Column(nullable = false, length = 128)
	@Explain("所属容器层次结构,主要是为了查询,多有用like查询 -1-2-3-12-")
	private String containerStructure;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Container getParent() {
		return parent;
	}

	public void setParent(Container parent) {
		this.parent = parent;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getContainerStructure() {
		return containerStructure;
	}

	public void setContainerStructure(String containerStructure) {
		this.containerStructure = containerStructure;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getRelation() {
		return relation;
	}

	public void setRelation(Long relation) {
		this.relation = relation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
