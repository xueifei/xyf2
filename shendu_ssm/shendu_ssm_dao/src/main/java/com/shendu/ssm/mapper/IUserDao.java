package com.shendu.ssm.mapper;



import com.shendu.ssm.domain.User;

import java.util.List;

public interface IUserDao {
	User getUserByName(String name);

	List<User> selectUserList();

	void insertUser(User user);

	void deleteUser(long id);

	User getUserByID(long id);

	void updateUser(User user);
	User editPassword(String name);
}
