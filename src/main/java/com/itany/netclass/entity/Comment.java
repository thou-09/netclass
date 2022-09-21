package com.itany.netclass.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.itany.netclass.constant.SystemConfigConsts;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 评价实体类
 *
 * @author Thou
 * @date 2022/8/30
 */
@Data
public class Comment implements Serializable {

    private static final long serialVersionUID = -2327197845466017752L;
    /**
     * 评论主键
     */
    private Integer id;
    /**
     * 评论内容
     */
    @com.itany.netclass.annotation.Comment("评论内容")
    private String context;
    /**
     * 创建时间
     */
    @JSONField(format = SystemConfigConsts.DEFAULT_DATE_PATTERN)
    private Date createDate;
    /**
     * 发布的用户 id
     */
    private Integer userId;
    /**
     * 被评价的资源 id
     */
    private Integer resourceId;
    /**
     * 评论状态 1 启用 -1 禁用 2 待审核
     */
    @com.itany.netclass.annotation.Comment("评论状态")
    private Integer status;

    /**
     * 发布的用户
     */
    private User user;
    /**
     * 被评论的资源
     */
    private Resource resource;
    /**
     * 点赞数
     */
    private Integer priseCount;
}
