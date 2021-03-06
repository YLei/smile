package com.emx.platform.common;

public enum ResultEnum {
	SUCCESS(200, "操作成功"),
    ERROR(201, "操作失败"),
    USER_ERROR(103, "用户名或密码不正确"),
    TOKEN_ERROR(105, "token无效"),
    PERMISSION_DENIED(403,"权限不足"),
    TYPE_MENU(1,"菜单"),
    TYPE_BUTTON(2,"按钮"),
    EXIST_CHILD_NODE(10101, "存在子节点，不可删除"),
    SYS_ROLENAME_EXIST(1,"角色名称已存在"),
    STATUS_NORMAL(1,"启用"),
    STATUS_CANCEL(2,"停用"),
    USER_IS_EXISTS(102,"用户名已存在"),
    DATA_IS_NULL(104,"数据为空"),
    ;

    private Integer code;
    private String msg;
 
    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
 
    public Integer getCode() {
        return code;
    }
 
    public String getMsg() {
        return msg;
    }
}
