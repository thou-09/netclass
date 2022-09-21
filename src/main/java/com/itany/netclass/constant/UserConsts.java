package com.itany.netclass.constant;

/**
 * 用户相关常量
 *
 * @author Thou
 * @date 2022/8/30
 */
public interface UserConsts {

    /**
     * 用户昵称最大长度
     */
    Integer USER_NICKNAME_MAX_LENGTH = 60;
    /**
     * 用户状态：启用
     */
    Integer USER_STATUS_ENABLE = 1;
    /**
     * 用户状态：禁用
     */
    Integer USER_STATUS_DISABLE = -1;
    /**
     * 用户角色：admin
     */
    String USER_ROLE_ADMIN = "admin";
    /**
     * 用户角色：normal
     */
    String USER_ROLE_NORMAL = "normal";
}
