package com.securitys.userdetails;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhaodongchao 2016年9月17日
 */
public class Role {
	private String roleId;
	private String roleName;
	private String roleCode;
	private String description;
	// 与Authority多对多
	private Set<Authority> Authoritys = new HashSet<>();
    
	public String getRoleId() {
		return roleId;
	}

	public Set<Authority> getAuthoritys() {
		return Authoritys;
	}

	public void setAuthoritys(Set<Authority> authoritys) {
		Authoritys = authoritys;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Authoritys == null) ? 0 : Authoritys.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((roleCode == null) ? 0 : roleCode.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (Authoritys == null) {
			if (other.Authoritys != null)
				return false;
		} else if (!Authoritys.equals(other.Authoritys))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (roleCode == null) {
			if (other.roleCode != null)
				return false;
		} else if (!roleCode.equals(other.roleCode))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", roleCode=" + roleCode + ", description="
				+ description + ", Authoritys=" + Authoritys + "]";
	}

	
}
