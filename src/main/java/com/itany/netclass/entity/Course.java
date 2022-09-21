package com.itany.netclass.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.itany.netclass.annotation.Comment;
import com.itany.netclass.constant.SystemConfigConsts;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 课程实体类
 *
 * @author Thou
 * @date 2022/8/30
 */
@Data
public class Course implements Serializable {

    private static final long serialVersionUID = -4836611944695410886L;
    /**
     * 课程主键
     */
    private Integer id;
    /**
     * 课程名称
     */
    @Comment("课程名称")
    private String courseName;
    /**
     * 课程简介
     */
    @Comment("课程简介")
    private String courseInfo;
    /**
     * 课程的作者
     */
    @Comment("作者")
    private String author;
    /**
     * 课程封面图片的相对路径
     */
    private String coverImageUrl;
    /**
     * 课程发布时间
     */
    @JSONField(format = SystemConfigConsts.DEFAULT_DATE_PATTERN)
    private Date createDate;
    /**
     * 课程点击量
     */
    private Integer clickNumber;
    /**
     * 课程状态 1 启用 -1 禁用
     */
    @Comment("课程状态")
    private Integer status;
    /**
     * 课程推荐等级 0 普通 1 推荐
     */
    @Comment("推荐等级")
    private Integer recommendationGrade;
    /**
     * 所属的课程类别 id
     */
    @Comment("所属课程类别编号")
    private Integer courseTypeId;

    /**
     * 所属的课程类别
     */
    private CourseType courseType;
    /**
     * 章节
     */
    private List<Chapter> chapters;
    /**
     * 课程总花费
     */
    private Integer totalCost;
    /**
     * 评论
     */
    private List<com.itany.netclass.entity.Comment> comments;
    /**
     * 总时长
     */
    private String timeCount;
}
