package com.itany.netclass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netclass.constant.CourseTypeConsts;
import com.itany.netclass.dao.CourseTypeMapper;
import com.itany.netclass.entity.Course;
import com.itany.netclass.entity.CourseType;
import com.itany.netclass.exception.CourseTypeExistException;
import com.itany.netclass.exception.StatusErrorException;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.service.CourseTypeService;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程类别 ServiceImpl
 *
 * @author Thou
 * @date 2022/9/3
 */
public class CourseTypeServiceImpl implements CourseTypeService {

    private final CourseTypeMapper courseTypeMapper = ObjectFactory.getObject("courseTypeMapper");

    @Override
    public PageInfo<CourseType> listByParentId(Integer pageNo, Integer pageSize, Integer parentId) {
        PageHelper.startPage(pageNo, pageSize);
        List<CourseType> list = courseTypeMapper.listCourseTypesByParentId(parentId);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<CourseType> listParentSiblingsByParentId(Integer pageNo, Integer pageSize, Integer parentId) {
        Integer id = courseTypeMapper.getParent(parentId).getId();
        PageHelper.startPage(pageNo, pageSize);
        List<CourseType> list = courseTypeMapper.listCourseTypesByParentId(id);
        return new PageInfo<>(list);
    }

    @Override
    public void add(CourseType courseType) throws CourseTypeExistException {
        CourseType ct = courseTypeMapper
                .getCourseTypeByTypeNameAndParentId(courseType.getTypeName(), courseType.getParentId());
        if (null != ct) {
            throw new CourseTypeExistException("课程类别已存在");
        }
        Integer parentId = courseType.getParentId();

        if (null == parentId) {
            courseType.setStatus(CourseTypeConsts.COURSE_TYPE_STATUS_ENABLE);
        } else {
            CourseType parent = courseTypeMapper.getCourseTypeById(parentId);
            Integer status = parent.getStatus();
            if (CourseTypeConsts.COURSE_TYPE_STATUS_DISABLE.equals(status)) {
                courseType.setStatus(CourseTypeConsts.COURSE_TYPE_STATUS_DISABLE);
            } else {
                courseType.setStatus(CourseTypeConsts.COURSE_TYPE_STATUS_ENABLE);
            }
        }
        courseTypeMapper.saveCourseType(courseType);
    }

    @Override
    public void modify(CourseType courseType) throws CourseTypeExistException {
        CourseType ct = courseTypeMapper
                .getCourseTypeByTypeNameAndParentId(courseType.getTypeName(), courseType.getParentId());
        if (null != ct) {
            throw new CourseTypeExistException("课程类别已存在");
        }
        courseTypeMapper.updateCourseTypeById(courseType);
    }

    @Override
    public void modifyStatus(CourseType courseType) throws StatusErrorException {
        Integer id = courseType.getId();
        Integer status = courseType.getStatus();

        // 修改状态为启用
        if (CourseTypeConsts.COURSE_TYPE_STATUS_ENABLE.equals(status)) {
            CourseType parent = courseTypeMapper.getParent(id);
            if (null != parent) {
                Integer parentStatus = parent.getStatus();
                if (CourseTypeConsts.COURSE_TYPE_STATUS_DISABLE.equals(parentStatus)) {
                    throw new StatusErrorException("上级课程类别已被禁用，无法更改状态");
                }
            }
        }

        List<Integer> ids = new ArrayList<>();

        List<Integer> list = new ArrayList<>();
        list.add(id);
        do {
            ids.addAll(list);
            list = courseTypeMapper.listSonsIdByParentIds(list);
        } while (list.size() != 0);

        courseTypeMapper.updateStatusByIds(status ,ids);
    }

    @Override
    public CourseType get(Integer id) throws CourseTypeExistException {
        CourseType courseType = courseTypeMapper.getCourseTypeById(id);
        if (null == courseType) {
            throw new CourseTypeExistException("课程类别不存在");
        }
        return courseType;
    }

    @Override
    public List<CourseType> listThreeLevelTypes() {
        return courseTypeMapper.listThreeLevelTypes();
    }

    @Override
    public List<CourseType> listAll() {
        return courseTypeMapper.listAll();
    }
}
