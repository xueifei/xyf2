package com.shendu.ssm.mapper;



import com.shendu.ssm.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserDao {
	User getUserByName(String name);

	List<User> selectUserList();

	void insertUser(User user);

	void deleteUser(long id);

	User getUserByID(long id);

	void updateUser(User user);

    List<User> fuzzySearchUser(@Param("name") String name);

    User editPassword(String name);
}
