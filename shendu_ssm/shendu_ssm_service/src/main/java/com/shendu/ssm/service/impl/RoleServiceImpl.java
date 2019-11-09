package com.shendu.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.shendu.ssm.domain.Role;
import com.shendu.ssm.domain.User;
import com.shendu.ssm.domain.UserRole;
import com.shendu.ssm.mapper.IRoleDao;
import com.shendu.ssm.mapper.IUserRoleDao;
import com.shendu.ssm.service.IRoleService;
import com.shendu.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	IUserService userService;
	@Autowired
	IUserRoleDao userRoleDao;
	@Autowired
	IRoleDao roleDao;

	/**
	 * 通过 用户名 获取 他拥有的所有角色名
	 */
	@Override
	public Set<String> listRoleName(String name) {
		Set<String> result = new HashSet<>();
		List<Role> roles = listRoleByName(name);
		for (Role role : roles) {
			result.add(role.getName());
		}
		return result;
	}

	/**
	 * 通过 用户名 获取 他拥有的所有角色
	 */
	@Override
	public List<Role> listRoleByName(String name) {
		List<Role> roles = new ArrayList<>();
		User user = userService.getUserByName(name);
		if (null == user)
			return roles;

		roles = listRoleByUser(user);
		return roles;
	}

	/**
	 * 通过 用户 获取 他拥有的所有角色
	 */
	@Override
	public List<Role> listRoleByUser(User user) {
		List<Role> roles = new ArrayList<>();

		List<UserRole> userRoles = userRoleDao.getUserRoleByUID(user.getId());

		for (UserRole userRole : userRoles) {
			Role role = roleDao.selectByPrimaryKey(userRole.getRid());
			roles.add(role);
		}
		return roles;
	}

	@Override
	public List<Role> selectlistRole() {

		return roleDao.selectRoleList();
	}

	@Override
	public List<Role> listRole(int page,int size) {
		//参数pageNum 是页码值   参数pageSize 代表是每页显示条数
		PageHelper.startPage(page, size);
		return roleDao.selectRoleList();
	}

	@Override
	public void addRole(Role role) {
		roleDao.insertRole(role);
	}

	@Override
	public Role getRole(long id) {
		return roleDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	@Override
	public void deleteRoleByID(long id) {
		roleDao.deleteRoleByID(id);
	}


    @Override
    public List<Role> fuzzyRole(String name) {
        return roleDao.fuzzyRole(name);
    }
}
