package com.shendu.ssm.domain;

import lombok.Data;

import java.util.List;

@Data
public class Role {
	private Long id;

	private String name;

	private String desc;

	private List<Permission> permissions;

}