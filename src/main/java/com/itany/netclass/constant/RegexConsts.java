package com.itany.netclass.constant;

/**
 * 正则表达式相关常量
 *
 * @author Thou
 * @date 2022/9/10
 */
public interface RegexConsts {

    /**
     * 登录名格式
     */
    String LOGIN_NAME_REGEX = "^[a-zA-Z0-9_]{6,18}$";
    /**
     * 密码格式
     */
    String PASSWORD_REGEX = "^[^\\s~!@#$%^&*()_\\-+=<>?:\"{}|,./;'\\\\\\[\\]·~！@#￥%…&*（）—\\-+={}|《》？：“”【】、；‘'，。]{6,18}$";
    /**
     * 邮箱格式
     */
    String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
}
