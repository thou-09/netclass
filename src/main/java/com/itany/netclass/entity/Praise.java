package com.itany.netclass.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.itany.netclass.constant.SystemConfigConsts;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 点赞记录实体类
 *
 * @author Thou
 * @date 2022/8/30
 */
@Data
public class Praise implements Serializable {

    private static final long serialVersionUID = -477539999135743559L;
    /**
     * 点赞记录主键
     */
    private Integer id;
    /**
     * 用户 id
     */
    private Integer userId;
    /**
     * 被点赞的评论 id
     */
    private Integer commentId;
    /**
     * 点赞时间
     */
    @JSONField(format = SystemConfigConsts.DEFAULT_DATE_PATTERN)
    private Date createDate;

    /**
     * 用户
     */
    private User user;
    /**
     * 被点赞的评论
     */
    private Comment comment;
}
