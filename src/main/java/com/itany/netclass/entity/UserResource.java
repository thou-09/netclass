package com.itany.netclass.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.itany.netclass.annotation.Comment;
import com.itany.netclass.constant.SystemConfigConsts;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户已购买的资源实体类
 *
 * @author Thou
 * @date 2022/8/30
 */
@Data
public class UserResource implements Serializable {

    private static final long serialVersionUID = 5751978703992029153L;
    /**
     * 用户已购买的资源主键
     */
    private Integer id;
    /**
     * 用户 id
     */
    private Integer userId;
    /**
     * 资源 id
     */
    private Integer resourceId;
    /**
     * mp4 视频观看到的时间 (单位秒, 保留两位小数)
     */
    @Comment("视频已播放时长")
    private String seeTime;
    /**
     * 购买日期
     */
    @JSONField(format = SystemConfigConsts.DEFAULT_DATE_PATTERN)
    private Date createDate;
    /**
     * 最近一次查看的日期
     */
    @JSONField(format = SystemConfigConsts.DEFAULT_DATE_PATTERN)
    private Date updateDate;

    /**
     * 用户
     */
    private User user;
    /**
     * 资源
     */
    private Resource resource;
}
