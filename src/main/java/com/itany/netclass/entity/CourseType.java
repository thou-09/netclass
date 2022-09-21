package com.itany.netclass.entity;

import com.itany.netclass.annotation.Comment;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 课程类别实体类
 *
 * @author Thou
 * @date 2022/8/30
 */
@Data
public class CourseType implements Serializable {

    private static final long serialVersionUID = -871632657979640753L;
    /**
     * 课程类型主键
     */
    private Integer id;
    /**
     * 课程类型名称
     */
    @Comment("课程类别名称")
    private String typeName;
    /**
     * 课程父类型 id
     */
    private Integer parentId;
    /**
     * 课程状态 1 启用 -1 禁用
     */
    @Comment("课程状态")
    private Integer status;

    /**
     * 子类别集合
     */
    List<CourseType> courseTypes;
    /**
     * 父类别
     */
    CourseType courseType;
}
