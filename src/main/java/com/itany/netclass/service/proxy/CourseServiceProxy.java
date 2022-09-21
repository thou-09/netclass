package com.itany.netclass.service.proxy;

import com.github.pagehelper.PageInfo;
import com.itany.netclass.entity.Course;
import com.itany.netclass.exception.CourseExistException;
import com.itany.netclass.exception.DataAccessException;
import com.itany.netclass.exception.ServiceException;
import com.itany.netclass.exception.StatusErrorException;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.query.CourseQuery;
import com.itany.netclass.service.CourseService;
import com.itany.netclass.transaction.TransactionManager;

import java.util.List;

/**
 * 课程 ServiceProxy
 *
 * @author Thou
 * @date 2022/9/7
 */
public class CourseServiceProxy implements CourseService {

    private final TransactionManager transactionManager = ObjectFactory.getObject("transactionManager");
    private final CourseService courseService = ObjectFactory.getObject("courseServiceTarget");

    @Override
    public void add(Course course) throws CourseExistException {
        try {
            transactionManager.beginTransaction();
            courseService.add(course);
            transactionManager.commit();
        } catch (CourseExistException e) {
            transactionManager.rollback();
            throw e;
        } catch (DataAccessException e) {
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        }
    }

    @Override
    public PageInfo<Course> list(Integer pageNo, Integer pageSize, CourseQuery courseQuery) {
        PageInfo<Course> info = null;
        try {
            transactionManager.beginTransaction();
            info = courseService.list(pageNo, pageSize, courseQuery);
            transactionManager.commit();
        } catch (DataAccessException e) {
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        }
        return info;
    }

    @Override
    public Course getOne(Integer id) throws CourseExistException {
        Course course = null;
        try {
            transactionManager.beginTransaction();
            course = courseService.getOne(id);
            transactionManager.commit();
        } catch (CourseExistException e) {
            transactionManager.rollback();
            throw e;
        } catch (DataAccessException e) {
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        }
        return course;
    }

    @Override
    public void modifyById(Course course) throws CourseExistException {
        try {
            transactionManager.beginTransaction();
            courseService.modifyById(course);
            transactionManager.commit();
        } catch (CourseExistException e) {
            transactionManager.rollback();
            throw e;
        } catch (DataAccessException e) {
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        }
    }

    @Override
    public void modifyStatusById(Course course) {
        try {
            transactionManager.beginTransaction();
            courseService.modifyStatusById(course);
            transactionManager.commit();
        } catch (DataAccessException e) {
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        }
    }

    @Override
    public List<Course> listTopFour(Integer id) {
        List<Course> list = null;
        try {
            transactionManager.beginTransaction();
            list = courseService.listTopFour(id);
            transactionManager.commit();
        } catch (DataAccessException e) {
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        }
        return list;
    }

    @Override
    public List<Course> listTopTen() {
        List<Course> list = null;
        try {
            transactionManager.beginTransaction();
            list = courseService.listTopTen();
            transactionManager.commit();
        } catch (DataAccessException e) {
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        }
        return list;
    }

    @Override
    public PageInfo<Course> frontSelect(Integer pageNo, Integer pageSize, CourseQuery courseQuery) {
        PageInfo<Course> info = null;
        try {
            transactionManager.beginTransaction();
            info = courseService.frontSelect(pageNo, pageSize, courseQuery);
            transactionManager.commit();
        } catch (DataAccessException e) {
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        }
        return info;
    }

    @Override
    public Course get4Front(Integer id) throws CourseExistException {
        Course course = null;
        try {
            transactionManager.beginTransaction();
            course = courseService.get4Front(id);
            transactionManager.commit();
        } catch (CourseExistException e) {
            transactionManager.rollback();
            throw e;
        } catch (DataAccessException e) {
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        }
        return course;
    }
}
