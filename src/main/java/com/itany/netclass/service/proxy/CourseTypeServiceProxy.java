package com.itany.netclass.service.proxy;

import com.github.pagehelper.PageInfo;
import com.itany.netclass.entity.CourseType;
import com.itany.netclass.exception.CourseTypeExistException;
import com.itany.netclass.exception.DataAccessException;
import com.itany.netclass.exception.ServiceException;
import com.itany.netclass.exception.StatusErrorException;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.service.CourseTypeService;
import com.itany.netclass.transaction.TransactionManager;

import java.util.List;

/**
 * 课程类别 ServiceProxy
 *
 * @author Thou
 * @date 2022/9/3
 */
public class CourseTypeServiceProxy implements CourseTypeService {

    private final TransactionManager transactionManager = ObjectFactory.getObject("transactionManager");
    private final CourseTypeService courseTypeService = ObjectFactory.getObject("courseTypeServiceTarget");

    @Override
    public PageInfo<CourseType> listByParentId(Integer pageNo, Integer pageSize, Integer parentId) {
        PageInfo<CourseType> info = null;
        try {
            transactionManager.beginTransaction();
            info = courseTypeService.listByParentId(pageNo, pageSize, parentId);
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
    public PageInfo<CourseType> listParentSiblingsByParentId(Integer pageNo, Integer pageSize, Integer parentId) {
        PageInfo<CourseType> info = null;
        try {
            transactionManager.beginTransaction();
            info = courseTypeService.listParentSiblingsByParentId(pageNo, pageSize, parentId);
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
    public void add(CourseType courseType) throws CourseTypeExistException {
        try {
            transactionManager.beginTransaction();
            courseTypeService.add(courseType);
            transactionManager.commit();
        } catch (CourseTypeExistException e) {
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
    public void modify(CourseType courseType) throws CourseTypeExistException {
        try {
            transactionManager.beginTransaction();
            courseTypeService.modify(courseType);
            transactionManager.commit();
        } catch (CourseTypeExistException e) {
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
    public void modifyStatus(CourseType courseType) throws StatusErrorException {
        try {
            transactionManager.beginTransaction();
            courseTypeService.modifyStatus(courseType);
            transactionManager.commit();
        } catch (StatusErrorException e) {
            transactionManager.rollback();
            throw e;
        }  catch (DataAccessException e) {
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        }
    }

    @Override
    public CourseType get(Integer id) throws CourseTypeExistException {
        CourseType courseType = null;
        try {
            transactionManager.beginTransaction();
            courseType = courseTypeService.get(id);
            transactionManager.commit();
        } catch (CourseTypeExistException e) {
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
        return courseType;
    }

    @Override
    public List<CourseType> listThreeLevelTypes() {
        List<CourseType> list = null;
        try {
            transactionManager.beginTransaction();
            list = courseTypeService.listThreeLevelTypes();
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
    public List<CourseType> listAll() {
        List<CourseType> list = null;
        try {
            transactionManager.beginTransaction();
            list = courseTypeService.listAll();
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
}
