package com.itany.netclass.dao;

import com.itany.netclass.entity.CourseType;
import com.itany.netclass.exception.DataAccessException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程类别持久层接口
 *
 * @author Thou
 * @date 2022/8/30
 */
public interface CourseTypeMapper {

    /**
     * 新增一个课程类别
     *
     * @param courseType 封装了课程类别信息的课程类别对象
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/30
     */
    void saveCourseType(CourseType courseType) throws DataAccessException;

    /**
     * 根据 id 删除课程类别
     *
     * @param id 课程类别主键
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/30
     */
    void removeCourseTypeById(@Param("id") Integer id) throws DataAccessException;

    /**
     * 根据 id 修改课程类别
     *
     * @param courseType 封装了课程类别信息的课程类别对象
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author thou
     * @date 2022/8/30
     */
    void updateCourseTypeById(CourseType courseType) throws DataAccessException;

    /**
     * 批量根据 id 修改状态
     *
     * @param status 状态
     * @param ids id 集合
     * @author Thou
     * @date 2022/9/7
     */
    void updateStatusByIds(@Param("status") Integer status, @Param("ids") List<Integer> ids);

    /**
     * 批量根据 parentId 查询子节点的 id
     *
     * @param parentIds parentId 集合
     * @return java.util.List<java.lang.Integer>
     * @author Thou
     * @date 2022/9/7
     */
    List<Integer> listSonsIdByParentIds(List<Integer> parentIds);

    /**
     * 根据 id 查询课程类别
     *
     * @param id 课程类别主键
     * @return com.itany.netclass.entity.CourseType
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/30
     */
    CourseType getCourseTypeById(@Param("id") Integer id) throws DataAccessException;

    /**
     * 根据 typeName 查询课程类别
     *
     * @param typeName 课程名
     * @return com.itany.netclass.entity.CourseType
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/6
     */
    CourseType getCourseTypeByTypeName(@Param("typeName") String typeName) throws DataAccessException;

    /**
     * 根据 typeName 和 parentId 查询课程类别
     *
     * @param typeName 课程名
     * @param parentId 父类别主键
     * @return com.itany.netclass.entity.CourseType
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/11
     */
    CourseType getCourseTypeByTypeNameAndParentId(@Param("typeName" ) String typeName,
                                                  @Param("pid") Integer parentId) throws DataAccessException;

    /**
     * 根据 parentId 查询课程类别
     *
     * @param parentId 课程类别父 id
     * @return java.util.List<com.itany.netclass.entity.CourseType>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/30
     */
    List<CourseType> listCourseTypesByParentId(@Param("pid") Integer parentId) throws DataAccessException;

    /**
     * 根据 id 查询 parent
     *
     * @param id 课程类别主键
     * @return java.util.List<com.itany.netclass.entity.CourseType>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/6
     */
    CourseType getParent(@Param("id") Integer id) throws DataAccessException;

    /**
     * 查询所有课程类别
     *
     * @return java.util.List<com.itany.netclass.entity.CourseType>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/30
     */
    List<CourseType> listCourseTypes() throws DataAccessException;

    /**
     * 查询所有启用的三级课程类别
     *
     * @return java.util.List<com.itany.netclass.entity.CourseType>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/7
     */
    List<CourseType> listThreeLevelTypes() throws DataAccessException;

    /**
     * 查询所有课程类别，树状结构
     *
     * @return java.util.List<com.itany.netclass.entity.CourseType>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/10
     */
    List<CourseType> listAll() throws DataAccessException;

    /**
     * 根据 id 查询直接父节点
     *
     * @param id 课程类别主键
     * @return com.itany.netclass.entity.CourseType
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/12
     */
    CourseType getParentsById(@Param("id") Integer id) throws DataAccessException;
}
