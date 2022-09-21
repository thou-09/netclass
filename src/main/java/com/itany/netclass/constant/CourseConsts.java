package com.itany.netclass.constant;

/**
 * 课程相关常量
 *
 * @author Thou
 * @date 2022/8/30
 */
public interface CourseConsts {

    /**
     * 课程状态：启用
     */
    Integer COURSE_STATUS_ENABLE = 1;
    /**
     * 课程状态：禁用
     */
    Integer COURSE_STATUS_DISABLE = -1;
    /**
     * 课程推荐等级：普通
     */
    Integer RECOMMENDATION_GRADE_NORMAL = 0;
    /**
     * 课程推荐等级：推荐
     */
    Integer RECOMMENDATION_GRADE_RECOMMENDED = 1;
    /**
     * 默认点击量
     */
    Integer DEFAULT_CLICK_NUMBER = 0;
}
