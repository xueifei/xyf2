package com.shendu.ssm.service;



import com.shendu.ssm.domain.Permission;
import com.shendu.ssm.domain.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Set;


public interface IPermissionService {
	Set<String> listPermissionName(String name);

	List<Permission> getPermissionListByRole(Role role);

	List<Permission> getPermissionList();

	List<Permission> listPermission(int page,int size );

	void addPermission(Permission permission);

	Permission getPermissionByID(long id);

	void updatePermission(Permission permission);

	void deletePermission(long id);

	boolean needInterceptor(String requestURI);

	Set<String> listPermissionURLs(String userName);

    List<Permission> findAll();
}
