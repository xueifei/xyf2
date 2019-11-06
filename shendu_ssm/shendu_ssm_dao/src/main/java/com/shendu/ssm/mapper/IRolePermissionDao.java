package com.shendu.ssm.mapper;



import com.shendu.ssm.domain.RolePermission;

import java.util.List;

public interface IRolePermissionDao {
	List<RolePermission> selectListRolePermissionByRID(Long rid);

	void deleteByPrimaryKey(Long id);

	void insertRolePermission(RolePermission rolePermission);
}
