package com.itany.netclass.query;

import com.itany.mvc.annotation.RequestMapping;
import com.itany.netclass.annotation.Comment;
import com.itany.netclass.entity.Course;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 课程查询对象，封装 request 请求中课程相关的查询参数
 *
 * @author Thou
 * @date 2022/9/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CourseQuery extends Course {

    private static final long serialVersionUID = 8026925407616001303L;
    /**
     * 创建日期开始日期
     */
    private Date createDateStart;
    /**
     * 创建日期结束日期
     */
    private Date createDateEnd;

    // 前台相关
    /**
     * 课程类别编号
     */
    @Comment("课程类别编号")
    private Integer courseTypeId;
    /**
     * 课程类别等级
     */
    @Comment("课程类别等级")
    private Integer typeLevel;
    /**
     * 排序方式：最新 1 or 最热 2
     */
    @Comment("排序方式")
    private Integer sortType;
    /**
     * 隐藏已参加（购买）
     */
    @Comment("是否隐藏已参加课程")
    private Boolean hideLearned;
    /**
     * 登录用户编号
     */
    @Comment("登录用户编号")
    private Integer loginUserId;
}
