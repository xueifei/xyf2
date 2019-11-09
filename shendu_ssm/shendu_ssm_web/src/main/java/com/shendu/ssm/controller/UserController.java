package com.shendu.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.shendu.ssm.domain.Permission;
import com.shendu.ssm.domain.Role;
import com.shendu.ssm.domain.User;
import com.shendu.ssm.service.IPermissionService;
import com.shendu.ssm.service.IRoleService;
import com.shendu.ssm.service.IUserRoleService;
import com.shendu.ssm.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理：用户
 */
@Controller
@RequestMapping("admin")
public class UserController {

	@Autowired
	IUserRoleService userRoleService;
	@Autowired
	IUserService userService;
	@Autowired
	IRoleService roleService;
	@Autowired
	IPermissionService permissionService;

	/**
	 * 用户列表
	 */
	@RequiresPermissions("userManage")
	@RequestMapping("listUser")
	public String list(Model model, @RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "4") int size) {
		List<User> listUser1 = userService.listUser(page, size);
		//PageInfo就是一个分页Bean
		PageInfo listUser=new PageInfo(listUser1);
		model.addAttribute("listUser", listUser);

		// 一个用户对应一堆角色
		Map<User, List<Role>> user_roles = new HashMap<>();
		for (User user : listUser1) {
			List<Role> roles = roleService.listRoleByUser(user);
			user_roles.put(user, roles);
		}
		model.addAttribute("user_roles", user_roles);

		return "listUser";
	}

	/**
	 * 添加用户
	 */
	@RequestMapping("addUser")
	public String add(Model model, String name,@RequestParam(name = "password", required = true, defaultValue = "123456") String password,long[] roleIds) {

		// 添加用户
		User user = new User();
        User user1 = editPasswordAndSalt(user, password);
        user1.setName(name);

		userService.addUser(user1);
		// 修改 用户角色 表
		userRoleService.editUserRole(user1, roleIds);
		return "redirect:listUser";
	}

	@RequiresPermissions("addUser1")
	@RequestMapping("addUser1")
	public String add1(Model model) {
		List<Role> listRole = roleService.selectlistRole();
		model.addAttribute("listRole", listRole);

		return "addUser";
	}

	/**
	 * 更改用户，页面
	 */
	@RequiresPermissions("editUser")
	@RequestMapping("editUser")
	public String edit(Model model, long id) {
		List<Role> listRole = roleService.selectlistRole();
		model.addAttribute("listRole", listRole);
		User user = userService.getUserByID(id);
		model.addAttribute("user", user);

		List<Role> roles = roleService.listRoleByUser(user);
		model.addAttribute("currentRoles", roles);

		return "editUser";
	}

	/**
	 * 更改用户，实现
	 */
	@RequestMapping("updateUser")
	public String update(User user, long[] roleIds) {
		// 修改 用户角色 表
		userRoleService.editUserRole(user, roleIds);

		// 修改密码
		String password = user.getPassword();
		// 如果在修改的时候没有设置密码，就表示不改动密码
		if (user.getPassword().length() != 0) {
            User user1 = editPasswordAndSalt(user, password);
            // 修改用户信息
            userService.updateUser(user1);
        } else{
			User u = userService.getUserByID(user.getId());
			user.setPassword(u.getPassword());
			user.setSalt(u.getSalt());
            // 修改用户信息
            userService.updateUser(user);
		}



		return "redirect:listUser";
	}

	/**
	 * 删除用户
	 */
	@RequiresPermissions("deleteUser")
	@RequestMapping("deleteUser")
	public String delete(long id) {
		userService.deleteUser(id);
		return "redirect:listUser";
	}

	@Transactional
	@RequestMapping("editPassword")
	public String editPassword(@RequestParam("name")String name){
		User user = userService.getUserByName(name);
		String password = "123456";
        User user1 = editPasswordAndSalt(user, password);
        userService.updateUser(user1);
		return "index";
	}

	private User editPasswordAndSalt(User user,String password){
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();	// 盐
        int times = 2;	// 加盐次数
        String algorithmName = "md5";	// MD5 加密
        // 加密 密码
        String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        return user;
    }

	/**
	 *查询用户查询
	 */
	@RequestMapping("showUser")
	@ResponseBody
	public ModelAndView showUser(long id) {
		User userByID = userService.getUserByID(id);
		List<Role> roles = roleService.listRoleByUser(userByID);

		for (Role role : roles) {
			List<Permission> permissionListByRole = permissionService.getPermissionListByRole(role);
            for (Permission permission : permissionListByRole) {
                permissionService.createPermissionTreeList(permission,permissionListByRole);
            }
			role.setPermissions(permissionListByRole);
		}
		userByID.setRoles(roles);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user",userByID);
		modelAndView.setViewName("showUser");
		return modelAndView;
	}

    /**
     * 模糊查询
     */
    @RequestMapping("fuzzySearchUser")
    public String fuzzySearchUser(Model model,String name) {
        //System.out.println(name);
        List<User> list = userService.fuzzySearchUser(name);
        //PageInfo就是一个分页Bean
        PageInfo listUser=new PageInfo(list);
        model.addAttribute("listUser",listUser);
        return "listUser";
    }
}