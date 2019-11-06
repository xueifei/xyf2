package com.shendu.ssm.mapper;



import com.shendu.ssm.domain.UserRole;

import java.util.List;

public interface IUserRoleDao {
	List<UserRole> getUserRoleByUID(Long uid);

	void deleteByPrimaryKey(Long id);

	void insertUserRole(UserRole userRole);
}
