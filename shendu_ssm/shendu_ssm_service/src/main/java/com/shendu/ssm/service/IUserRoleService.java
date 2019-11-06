package com.shendu.ssm.service;


import com.shendu.ssm.domain.User;

public interface IUserRoleService {
	void editUserRole(User user, long[] roleIds);
}
