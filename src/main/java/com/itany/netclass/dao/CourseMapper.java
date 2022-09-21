package com.itany.netclass.dao;

import com.itany.netclass.entity.Course;
import com.itany.netclass.exception.DataAccessException;
import com.itany.netclass.query.CourseQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程持久层接口
 *
 * @author Thou
 * @date 2022/8/29
 */
public interface CourseMapper {

    /**
     * 新增一个课程
     *
     * @param course 封装了课程信息的课程对象
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/30
     */
    void saveCourse(Course course) throws DataAccessException;

    /**
     * 根据 id 删除课程
     *
     * @param id 课程主键
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/30
     */
    void removeCourseById(@Param("id") Integer id) throws DataAccessException;

    /**
     * 根据 id 更新课程
     *
     * @param course 封装了课程信息的课程对象
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/30
     */
    void updateCourseById(Course course) throws DataAccessException;

    /**
     * 根据 id 查询课程
     *
     * @param id 课程主键
     * @return com.thou.entity.Course
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/30
     */
    Course getCourseById(@Param("id") Integer id) throws DataAccessException;

    /**
     * 根据 chapterId 查找对应课程
     *
     * @param id 章节主键
     * @return com.itany.netclass.entity.Course
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/12
     */
    Course getCourseByChapterId(@Param("cid") Integer id) throws DataAccessException;

    /**
     * 根据 id 查询课程信息，包括类别信息
     *
     * @param id 课程主键
     * @return com.itany.netclass.entity.Course
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/8
     */
    Course getCourseWithTypeById(@Param("id") Integer id) throws DataAccessException;

    /**
     * 根据 courseName 查询课程信息
     *
     * @param courseName 课程名称
     * @return com.itany.netclass.entity.Course
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/8
     */
    Course getCourseByCourseName(@Param("name") String courseName) throws DataAccessException;

    /**
     * 根据条件查询所有课程，包含 CourseType 信息
     *
     * @param courseQuery 封装了查询条件的查询对象
     * @return java.util.List<com.thou.entity.Course>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/30
     */
    List<Course> listCoursesWithCourseType(CourseQuery courseQuery) throws DataAccessException;

    /**
     * 查询一级课程类别下所有课程点击量前四的课程信息
     *
     * @param id 一级课程类别编号
     * @return java.util.List<com.itany.netclass.entity.Course>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/10
     */
    List<Course> listTopFour(@Param("id") Integer id) throws DataAccessException;

    /**
     * 查询所有课程的点击量前十的课程
     *
     * @return java.util.List<com.itany.netclass.entity.Course>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/10
     */
    List<Course> listTopTen() throws DataAccessException;

    /**
     * 根据前台查询条件分页查询课程信息，仅限前台查询使用
     *
     * @param courseQuery 封装了查询条件的查询对象
     * @return java.util.List<com.itany.netclass.entity.Course>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/11
     */
    List<Course> listForFront(CourseQuery courseQuery) throws DataAccessException;

    /**
     * 根据 id 查询课程及所有章节和章节的资源
     *
     * @param id 课程主键
     * @return com.itany.netclass.entity.Course
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/12
     */
    Course getCourseWithChaptersAndResourceById(@Param("id") Integer id) throws DataAccessException;
}
