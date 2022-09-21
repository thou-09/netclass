package com.itany.netclass.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.itany.netclass.annotation.Comment;
import com.itany.netclass.constant.SystemConfigConsts;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 章节实体类
 *
 * @author Thou
 * @date 2022/8/30
 */
@Data
public class Chapter implements Serializable {

    private static final long serialVersionUID = -3363529111293463781L;
    /**
     * 章节主键
     */
    private Integer id;
    /**
     * 对应课程 id
     */
    private Integer courseId;
    /**
     * 章节标题
     */
    @Comment("章节标题")
    private String title;
    /**
     * 章节简介
     */
    @Comment("章节简介")
    private String info;
    /**
     * 章节创建时间
     */
    @JSONField(format = SystemConfigConsts.DEFAULT_DATE_PATTERN)
    private Date createDate;
    /**
     * 章节状态 1 启用 -1 禁用
     */
    @Comment("章节状态")
    private Integer status;

    /**
     * 对应课程
     */
    private Course course;
    /**
     * 对应资源
     */
    private Resource resource;
    /**
     * 对应资源 id
     */
    private Integer resourceId;
}
