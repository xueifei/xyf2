package com.shendu.ssm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TagType implements BaseEnum{
    MENU(1, "菜单"),
    URL(2, "链接");

    private Integer value; // 值
    private String text; // 描述
    private TagType(int value, String text){
        this.value = value;
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
