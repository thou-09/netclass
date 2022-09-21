package com.itany.netclass.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.itany.netclass.annotation.Comment;
import com.itany.netclass.constant.SystemConfigConsts;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 资源实体类
 *
 * @author Thou
 * @date 2022/8/30
 */
@Data
public class Resource implements Serializable {

    private static final long serialVersionUID = -5750254132591346669L;
    /**
     * 资源主键
     */
    private Integer id;
    /**
     * 资源标题
     */
    @Comment("资源标题")
    private String title;
    /**
     * 资源相对路径
     */
    private String path;
    /**
     * 资源封面图片地址
     */
    private String coverImageUrl;
    /**
     * 文件原始名称
     */
    private String originalName;
    /**
     * 文件大小 (MB)
     */
    private String fileSize;
    /**
     * 文件类型 (文件后缀名)
     */
    @Comment("资源类型")
    private String fileType;
    /**
     * mp4 视频的总时间 (单位秒, 保留两位小数)
     */
    private String totalTime;
    /**
     * 点击量
     */
    private Integer clickCount;
    /**
     * 上传时间
     */
    @JSONField(format = SystemConfigConsts.DEFAULT_DATE_PATTERN)
    private Date createDate;
    /**
     * 付款类型, 0 积分 1 金币
     */
    @Comment("资源付款方式")
    private Integer costType;
    /**
     * 下载文件或查看视频需要的积分或金币数量
     */
    @Comment("资源所需费用")
    private Integer costNumber;
    /**
     * 上传用户 id
     */
    private Integer userId;
    /**
     * 章节 id
     */
    private Integer chapterId;
    /**
     * 资源状态 1 启用 -1 禁用
     */
    @Comment("资源状态")
    private Integer status;

    /**
     * 上传用户
     */
    private User user;
    /**
     * 章节
     */
    private Chapter chapter;
    /**
     * 评论
     */
    List<com.itany.netclass.entity.Comment> comments;
}
