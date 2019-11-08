package com.shendu.ssm.service;


import com.shendu.ssm.domain.Role;
import com.shendu.ssm.domain.User;

import java.util.List;
import java.util.Set;

public interface IRoleService{
	Set<String> listRoleName(String name);
	List<Role> listRoleByName(String name);

	List<Role> listRoleByUser(User user);

	List<Role> listRole(int page,int size );

	List<Role> selectlistRole();

	void addRole(Role role);

	Role getRole(long id);

	void updateRole(Role role);

	void deleteRoleByID(long id);

    List<Role> fuzzyRole(String name);
}
