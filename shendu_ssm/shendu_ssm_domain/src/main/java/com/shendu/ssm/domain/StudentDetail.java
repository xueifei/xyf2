package com.shendu.ssm.domain;

import lombok.Data;

//学生详情
@Data
public class StudentDetail {
    private Integer id;
    private String name;
    private String address;
    private String stuClass;
    private String phone;
    private String parentPhone;
    private String identity;//身份证号
}
