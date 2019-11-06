package com.shendu.ssm.mapper;



import com.shendu.ssm.domain.Permission;

import java.util.List;

public interface IPermissionDao {
	Permission selectByPrimaryKey(Long id);

	List<Permission> selectPermissionList();

	void insertPermission(Permission permission);

	void updatePermission(Permission permission);

	void deletePermission(long id);

    List<Permission> findAll();
}
