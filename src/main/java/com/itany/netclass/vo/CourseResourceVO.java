package com.itany.netclass.vo;

import com.itany.netclass.annotation.Comment;
import lombok.Data;

import java.io.Serializable;

/**
 * 课程资源
 *
 * @author Thou
 * @date 2022/9/8
 */
@Data
public class CourseResourceVO implements Serializable {

    private static final long serialVersionUID = -8628053886101888015L;
    /**
     * 章节主键
     */
    private Integer chapterId;
    /**
     * 章节标题
     */
    @Comment("章节标题")
    private String chapterTitle;
    /**
     * 章节简介
     */
    @Comment("章节简介")
    private String chapterInfo;
    /**
     * 资源主键
     */
    private Integer resourceId;
    /**
     * 资源封面图片地址
     */
    private String resourceCoverImageUrl;
    /**
     * 资源相对路径
     */
    private String resourcePath;
    /**
     * 资源原始文件名
     */
    private String resourceOriginalName;
    /**
     * 资源文件大小（MB）
     */
    private String resourceFileSize;
    /**
     * 资源文件类型（后缀）
     */
    @Comment("资源文件类型")
    private String resourceFileType;
    /**
     * mp4 文件总时长（单位秒，保留两位小数）
     */
    private String resourceTotalTime;
    /**
     * 资源标题
     */
    @Comment("资源标题")
    private String resourceTitle;
    /**
     * 资源付款方式
     */
    @Comment("资源付款方式")
    private Integer resourceCostType;
    /**
     * 资源所需费用
     */
    @Comment("资源所需费用")
    private Integer resourceCostNumber;
    /**
     * 所属课程主键
     */
    private Integer courseId;
    /**
     * 上传用户主键
     */
    private Integer userId;
}
