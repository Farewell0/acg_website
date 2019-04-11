package com.wzz.acg.common;

public class Const {

    public static final String CURRENT_USER = "current_user";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";

    public interface Role{
        int ROLE_ADMIN = 0;     // 管理员
        int ROLE_CUSTOMER = 1;  // 普通用户
    }
}
