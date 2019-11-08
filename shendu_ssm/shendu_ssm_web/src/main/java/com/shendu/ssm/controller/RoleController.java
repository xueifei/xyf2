package com.shendu.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.shendu.ssm.domain.Permission;
import com.shendu.ssm.domain.Role;
import com.shendu.ssm.domain.User;
import com.shendu.ssm.service.IPermissionService;
import com.shendu.ssm.service.IRolePermissionService;
import com.shendu.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理：角色
 */
@Controller
@RequestMapping("admin")
public class RoleController {

	@Autowired
	IRoleService roleService;
	@Autowired
	IRolePermissionService rolePermissionService;
	@Autowired
	IPermissionService permissionService;

	@RequestMapping("listRole")
	public String list(Model model, @RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "4") int size) {
		List<Role> rs1 = roleService.listRole(page, size);
		//PageInfo就是一个分页Bean
		PageInfo rs=new PageInfo(rs1);
		model.addAttribute("rs", rs);

		Map<Role, List<Permission>> role_permissions = new HashMap<>();

		for (Role role : rs1) {
			List<Permission> ps = permissionService.getPermissionListByRole(role);
			role_permissions.put(role, ps);
		}
		model.addAttribute("role_permissions", role_permissions);

		return "listRole";
	}

	@RequestMapping("addRole")
	public String list(Role role,long[] permissionIds) {

		roleService.addRole(role);
		rolePermissionService.updateRolePermission(role, permissionIds);
		return "redirect:listRole";
	}

	@RequestMapping("addRole1")
	public String addRole1(Model model) {
		List<Permission> ps = permissionService.getPermissionList();
		model.addAttribute("ps", ps);
		return "addRole";
	}

	@RequestMapping("editRole")
	public String list(Model model, long id) {
		Role role = roleService.getRole(id);
		model.addAttribute("role", role);

		List<Permission> ps = permissionService.getPermissionList();
		model.addAttribute("ps", ps);

		List<Permission> currentPermissions = permissionService.getPermissionListByRole(role);
		model.addAttribute("currentPermissions", currentPermissions);

		return "editRole";
	}

	@RequestMapping("updateRole")
	public String update(Role role, long[] permissionIds) {
		rolePermissionService.updateRolePermission(role, permissionIds);
		roleService.updateRole(role);
		return "redirect:listRole";
	}

	@RequestMapping("deleteRole")
	public String delete(long id) {
		roleService.deleteRoleByID(id);
		return "redirect:listRole";
	}

    @RequestMapping("fuzzyRole")
    public String fuzzyRole(String name,Model model){
        List<Role> list = roleService.fuzzyRole(name);
        PageInfo rs = new PageInfo(list);
        model.addAttribute("rs",rs);
        return "listRole";
    }


}