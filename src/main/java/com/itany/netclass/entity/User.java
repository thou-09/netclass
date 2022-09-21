package com.itany.netclass.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.itany.netclass.annotation.Comment;
import com.itany.netclass.constant.SystemConfigConsts;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 *
 * @author Thou
 * @date 2022/8/30
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -6045001978530090566L;
    /**
     * 用户主键
     */
    private Integer id;
    /**
     * 用户名, 登录名
     */
    @Comment("登录名")
    private String loginName;
    /**
     * 用户昵称
     */
    @Comment("用户昵称")
    private String nickname;
    /**
     * 密码
     */
    @Comment("密码")
    private String password;
    /**
     * 角色
     */
    @Comment("角色")
    private String role;
    /**
     * 邮箱
     */
    @Comment("邮箱")
    private String email;
    /**
     * 最近一次签到的日期
     */
    @JSONField(format = SystemConfigConsts.DEFAULT_DATE_PATTERN)
    private Date signDate;
    /**
     * 用户创建日期
     */
    @JSONField(format = SystemConfigConsts.DEFAULT_DATE_PATTERN)
    private Date createDate;
    /**
     * 用户状态 1 启用 -1 禁用
     */
    @Comment("用户状态")
    private Integer status;

    /**
     * 积分总数
     */
    private Integer totalPoint;
    /**
     * 金币总数
     */
    private Integer totalGold;
    /**
     * 今天是否已签到
     */
    private Boolean signInToday;
}
