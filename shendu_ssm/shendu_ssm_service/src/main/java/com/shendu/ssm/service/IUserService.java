package com.shendu.ssm.service;



import com.shendu.ssm.domain.User;

import java.util.List;

public interface IUserService {
	User getUserByName(String name);
	List<User> listUser(int page,int size);
	void addUser(User user);
	void deleteUser(long id);
	User getUserByID(long id);
	void updateUser(User user);

    //模糊查询
    List<User> fuzzySearchUser(String name);

}
