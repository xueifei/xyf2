package com.shendu.ssm.service;


import com.shendu.ssm.domain.Role;

public interface IRolePermissionService {
	void updateRolePermission(Role role, long[] permissionIds);
}
