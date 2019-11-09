package com.shendu.ssm.mapper;



import com.shendu.ssm.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRoleDao {
	Role selectByPrimaryKey(Long id);

	List<Role> selectRoleList();

	void insertRole(Role role);

	void updateRole(Role role);

	void deleteRoleByID(long id);

    List<Role> fuzzyRole(@Param("name")String name);

}
