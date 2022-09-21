package com.itany.netclass.service;

import com.github.pagehelper.PageInfo;
import com.itany.netclass.entity.CourseType;
import com.itany.netclass.exception.CourseTypeExistException;
import com.itany.netclass.exception.StatusErrorException;

import java.util.List;

/**
 * 课程类别 Service interface
 *
 * @author Thou
 * @date 2022/9/3
 */
public interface CourseTypeService {

    /**
     * 根据 parentId 分页查询所有课程类别信息
     *
     * @param pageNo 当前页
     * @param pageSize 页面尺寸
     * @param parentId parentId
     * @return com.github.pagehelper.PageInfo<com.itany.netclass.entity.CourseType>
     * @author Thou
     * @date 2022/9/3
     */
    PageInfo<CourseType> listByParentId(Integer pageNo, Integer pageSize, Integer parentId);

    /**
     * 根据 parentId 查询 parent 的所有兄弟（包括自己）
     *
     * @param pageNo 当前页
     * @param pageSize 页面尺寸
     * @param parentId parentId
     * @return com.github.pagehelper.PageInfo<com.itany.netclass.entity.CourseType>
     * @author Thou
     * @date 2022/9/6
     */
    PageInfo<CourseType> listParentSiblingsByParentId(Integer pageNo, Integer pageSize, Integer parentId);

    /**
     * 新增一个课程类别信息
     *
     * @param courseType 封装了课程类别信息的课程类别对象
     * @throws CourseTypeExistException 课程类别存在性异常，当要添加的课程类别已存在时抛出此异常
     * @author Thou
     * @date 2022/9/3
     */
    void add(CourseType courseType) throws CourseTypeExistException;

    /**
     * 修改指定的课程类别信息
     *
     * @param courseType 封装了课程类别信息的课程类别对象
     * @throws CourseTypeExistException 课程类别存在性异常，当要修改的课程类别名称已存在时抛出此异常
     * @author Thou
     * @date 2022/9/3
     */
    void modify(CourseType courseType) throws CourseTypeExistException;

    /**
     * 修改课程类别状态，其子类别也都要修改<br>
     * 当修改状态为禁用时，子类别全部禁用<br>
     * 当修改状态为启用时，先判断其父类是否禁用，如若禁用，抛出异常 StatusErrorException，反之启用其子类别
     *
     * @param courseType 封装了课程类别信息的课程类别对象
     * @throws StatusErrorException 状态异常错误
     * @author Thou
     * @date 2022/9/7
     */
    void modifyStatus(CourseType courseType) throws StatusErrorException;

    /**
     * 根据 id 查询指定的课程类别信息
     *
     * @param id 课程类别主键
     * @return com.itany.netclass.entity.CourseType
     * @throws CourseTypeExistException 课程类别存在性异常，当课程类别不在数据库中时抛出此异常
     * @author Thou
     * @date 2022/9/3
     */
    CourseType get(Integer id) throws CourseTypeExistException;

    /**
     * 查询所有启用的三级课程类别
     *
     * @return java.util.List<com.itany.netclass.entity.CourseType>
     * @author Thou
     * @date 2022/9/7
     */
    List<CourseType> listThreeLevelTypes();

    /**
     * 查询所有课程类别，树状结构
     *
     * @return java.util.List<com.itany.netclass.entity.CourseType>
     * @author Thou
     * @date 2022/9/10
     */
    List<CourseType> listAll();
}
