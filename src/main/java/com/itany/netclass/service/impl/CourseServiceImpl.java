package com.itany.netclass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netclass.constant.CourseConsts;
import com.itany.netclass.dao.ChapterMapper;
import com.itany.netclass.dao.CommentMapper;
import com.itany.netclass.dao.CourseMapper;
import com.itany.netclass.dao.CourseTypeMapper;
import com.itany.netclass.dao.ResourceMapper;
import com.itany.netclass.entity.Comment;
import com.itany.netclass.entity.Course;
import com.itany.netclass.entity.CourseType;
import com.itany.netclass.exception.CourseExistException;
import com.itany.netclass.exception.StatusErrorException;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.query.CourseQuery;
import com.itany.netclass.service.ChapterService;
import com.itany.netclass.service.CourseService;
import com.itany.netclass.service.ResourceService;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 课程 ServiceImpl
 *
 * @author Thou
 * @date 2022/9/7
 */
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper = ObjectFactory.getObject("courseMapper");
    private final ChapterMapper chapterMapper = ObjectFactory.getObject("chapterMapper");
    private final ResourceMapper resourceMapper = ObjectFactory.getObject("resourceMapper");
    private final CommentMapper commentMapper = ObjectFactory.getObject("commentMapper");
    private final CourseTypeMapper courseTypeMapper = ObjectFactory.getObject("courseTypeMapper");

    @Override
    public void add(Course course) throws CourseExistException {
        Course c = courseMapper.getCourseByCourseName(course.getCourseName());
        if (null != c) {
            throw new CourseExistException("该课程名称已存在，不可重复");
        }
        course.setCreateDate(new Date());
        course.setClickNumber(CourseConsts.DEFAULT_CLICK_NUMBER);
        course.setStatus(CourseConsts.COURSE_STATUS_ENABLE);
        courseMapper.saveCourse(course);
    }

    @Override
    public PageInfo<Course> list(Integer pageNo, Integer pageSize, CourseQuery courseQuery) {
        PageHelper.startPage(pageNo, pageSize);
        List<Course> list = courseMapper.listCoursesWithCourseType(courseQuery);
        return new PageInfo<>(list);
    }

    @Override
    public Course getOne(Integer id) throws CourseExistException {
        Course course = courseMapper.getCourseById(id);
        if (null == course) {
            throw new CourseExistException("当前课程不存在");
        }
        return course;
    }

    @Override
    public void modifyById(Course course) throws CourseExistException {
        Course c = courseMapper.getCourseByCourseName(course.getCourseName());
        if (null != c) {
            boolean isSame = (c.getId()).equals(course.getId());
            if (!isSame) {
                throw new CourseExistException("该课程名称已存在，不可重复");
            }
        }
        courseMapper.updateCourseById(course);
    }

    @Override
    public void modifyStatusById(Course course) {
        Integer id = course.getId();
        Integer status = course.getStatus();

        courseMapper.updateCourseById(course);
        List<Integer> chapterIds = chapterMapper.listChapterIdsByCourseId(id);
        if (!chapterIds.isEmpty()) {
            chapterMapper.updateStatusByIds(chapterIds, status);
            resourceMapper.updateStatusByChapterIds(chapterIds, status);
        }
    }

    @Override
    public List<Course> listTopFour(Integer id) {
        List<Course> list = courseMapper.listTopFour(id);
        list.forEach(c -> {
            if (null == c.getTotalCost()) {
                c.setTotalCost(0);
            }
        });
        return list;
    }

    @Override
    public List<Course> listTopTen() {
        List<Course> list = courseMapper.listTopTen();
        list.forEach(c -> {
            if (null == c.getTotalCost()) {
                c.setTotalCost(0);
            }
        });
        return list;
    }

    @Override
    public PageInfo<Course> frontSelect(Integer pageNo, Integer pageSize, CourseQuery courseQuery) {
        PageHelper.startPage(pageNo, pageSize);
        List<Course> list = courseMapper.listForFront(courseQuery);
        list.forEach(c -> {
            if (null == c.getTotalCost()) {
                c.setTotalCost(0);
            }
        });
        return new PageInfo<>(list);
    }

    @Override
    public Course get4Front(Integer id) throws CourseExistException {
        Course course = courseMapper.getCourseWithChaptersAndResourceById(id);
        if (null == course) {
            throw new CourseExistException("当前课程不存在");
        }
        course.setClickNumber(course.getClickNumber() + 1);
        courseMapper.updateCourseById(course);
        List<Comment> comments = commentMapper.listCommentsByCourseId(course.getId());
        course.setComments(comments);
        CourseType courseType = courseTypeMapper.getParentsById(course.getCourseTypeId());
        course.setCourseType(courseType);
        return course;
    }
}
