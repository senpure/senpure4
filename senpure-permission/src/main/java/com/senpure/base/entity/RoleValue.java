package com.senpure.base.entity;


import com.senpure.base.PermissionConstant;

import javax.persistence.*;


@Entity
@Table(name = PermissionConstant.DATA_BASE_PREFIX+ "_ROLE_VALUE")
public class RoleValue extends LongAndVersionEntity{
	private static final long serialVersionUID = 4387681837130997965L;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId")
	private Role role;
	@Column(name = "roleKey",length = 24,nullable = false)
	private String key;
	@Column(name = "roleValue",length = 24,nullable = false)
	private String value;

	private String description;
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
