package com.itany.netclass.constant;

/**
 * 响应状态码枚举类
 *
 * @author Thou
 * @date 2022/8/30
 */
public enum StatusCodeEnum {
    /**
     * Response 响应状态码和提示信息
     */
    SUCCESS("00000", "成功"),
    USER_REGISTER_ERROR("A0100", "用户注册错误"),
    USER_LOGIN_ERROR("A0101", "用户登录错误"),
    USER_SELECT_ERROR("A0102", "用户查询错误"),
    USER_MODIFY_ERROR("A0103", "用户修改错误"),
    USER_SIGN_IN_ERROR("A0104", "用户签到错误"),
    MANAGER_LOGIN_ERROR("A0105", "管理员登录错误"),
    CODE_IMAGE_ERROR("A0110", "验证码获取错误"),
    COURSE_TYPE_SELECT_ERROR("A0200", "课程类别查询错误"),
    COURSE_TYPE_ADD_ERROR("A0201", "课程类别新增错误"),
    COURSE_TYPE_MODIFY_ERROR("A0202", "课程类别修改错误"),
    COURSE_SELECT_ERROR("A0300", "课程查询错误"),
    COURSE_ADD_ERROR("A0301", "课程新增错误"),
    COURSE_MODIFY_ERROR("A0302", "课程修改错误"),
    CHAPTER_SELECT_ERROR("A0400", "章节查询错误"),
    CHAPTER_ADD_ERROR("A0401", "章节新增错误"),
    CHAPTER_MODIFY_ERROR("A0402", "章节修改错误"),
    RESOURCE_SELECT_ERROR("A0500", "资源查询错误"),
    RESOURCE_ADD_ERROR("A0501", "资源新增错误"),
    RESOURCE_MODIFY_ERROR("A0502", "资源修改错误"),
    COMMENT_SELECT_ERROR("A0600", "评论查询错误"),
    COMMENT_ADD_ERROR("A0601", "评论新增错误"),
    COMMENT_MODIFY_ERROR("A0602", "评论修改错误"),
    GOLD_POINT_SELECT_ERROR("A0700", "积分金币记录查询错误"),
    GOLD_POINT_ADD_ERROR("A0701", "积分金币记录新增错误"),
    GOLD_POINT_MODIFY_ERROR("A0702", "积分金币记录修改错误"),
    ;

    /**
     * 状态码
     */
    private final String statusCode;

    /**
     * 提示信息
     */
    private final String message;

    /**
     * 构造函数
     *
     * @param statusCode 状态码
     * @param message 提示信息
     * @author Thou
     * @date 2022/8/30
     */
    StatusCodeEnum(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     * 获取状态码
     *
     * @return java.lang.String
     * @author Thou
     * @date 2022/8/30
     */
    public String getCode() {
        return statusCode;
    }

    /**
     * 获取提示信息
     *
     * @return java.lang.String
     * @author Thou
     * @date 2022/8/30
     */
    public String getMessage() {
        return message;
    }
}
