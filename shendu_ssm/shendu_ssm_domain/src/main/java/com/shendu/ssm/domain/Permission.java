package com.shendu.ssm.domain;

import lombok.Data;

import java.util.Set;

@Data
public class Permission {
	private Long id;
	private Permission parent;
	private Set<Permission> children;
	private String name;
	private String url;
	private String code;
	private String desc;
	private int sort;
	private int available;

	private TagType tagType;

}