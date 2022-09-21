package com.itany.netclass.service;

import com.github.pagehelper.PageInfo;
import com.itany.netclass.entity.Course;
import com.itany.netclass.entity.CourseType;
import com.itany.netclass.exception.CourseExistException;
import com.itany.netclass.exception.StatusErrorException;
import com.itany.netclass.query.CourseQuery;

import java.util.List;

/**
 * 课程 Service interface
 *
 * @author Thou
 * @date 2022/9/5
 */
public interface CourseService {

    /**
     * 新增一个课程<br>
     * createDate 为当前时间<br>
     * status 为常量<br>
     * clickNumber 为常量
     *
     * @param course 封装了课程信息的课程对象
     * @throws CourseExistException 课程已存在异常
     * @author Thou
     * @date 2022/9/7
     */
    void add(Course course) throws CourseExistException;

    /**
     * 根据条件分页查询课程信息
     *
     * @param pageNo 当前页
     * @param pageSize 页面尺寸
     * @param courseQuery 封装了查询条件的课程查询对象
     * @return com.github.pagehelper.PageInfo<com.itany.netclass.entity.Course>
     * @author Thou
     * @date 2022/9/7
     */
    PageInfo<Course> list(Integer pageNo, Integer pageSize, CourseQuery courseQuery);

    /**
     * 根据 id 查询课程信息
     *
     * @param id 课程主键
     * @return com.itany.netclass.entity.Course
     * @throws CourseExistException 课程不存在异常
     * @author Thou
     * @date 2022/9/8
     */
    Course getOne(Integer id) throws CourseExistException;

    /**
     * 根据 id 修改课程信息
     *
     * @param course 封装了课程信息的课程对象
     * @throws CourseExistException 课程已存在异常
     * @author Thou
     * @date 2022/9/8
     */
    void modifyById(Course course) throws CourseExistException;

    /**
     * 根据 id 修改课程状态，包括其拥有的所有章节状态，和章节所带资源状态
     *
     * @param course 封装了课程信息的课程对象
     * @author Thou
     * @date 2022/9/9
     */
    void modifyStatusById(Course course);

    /**
     * 查询一级课程类别下所有课程点击量前四的课程
     *
     * @param id 一级课程类别编号
     * @return java.util.List<com.itany.netclass.entity.CourseType>
     * @author Thou
     * @date 2022/9/10
     */
    List<Course> listTopFour(Integer id);

    /**
     * 查询所有课程的点击量前十的课程
     *
     * @return java.util.List<com.itany.netclass.entity.Course>
     * @author Thou
     * @date 2022/9/10
     */
    List<Course> listTopTen();

    /**
     * 根据前台查询条件分页查询课程信息
     *
     * @param pageNo 当前页
     * @param pageSize 页面尺寸
     * @param courseQuery 封装了查询条件的课程查询对象
     * @return com.github.pagehelper.PageInfo<com.itany.netclass.entity.Course>
     * @author Thou
     * @date 2022/9/11
     */
    PageInfo<Course> frontSelect(Integer pageNo, Integer pageSize, CourseQuery courseQuery);

    /**
     * 通过 id 查询课程，课程类别，所有章节和章节的资源，以及课程下的所有评论和评论的用户
     *
     * @param id 课程主键
     * @return com.itany.netclass.entity.Course
     * @throws CourseExistException 课程不存在异常
     * @author Thou
     * @date 2022/9/12
     */
    Course get4Front(Integer id) throws CourseExistException;
}
