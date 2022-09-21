package com.itany.netclass.dao;

import com.itany.netclass.entity.Resource;
import com.itany.netclass.entity.UserResource;
import com.itany.netclass.exception.DataAccessException;
import com.itany.netclass.query.ResourceQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资源持久层接口
 *
 * @author Thou
 * @date 2022/8/29
 */
public interface ResourceMapper {

    /**
     * 新增一个资源
     *
     * @param resource 封装了资源信息的资源对象
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/29
     */
    void saveResource(Resource resource) throws DataAccessException;

    /**
     * 新增一个资源，并返回对应的主键
     *
     * @param resource 封装了资源信息的资源对象
     * @author Thou
     * @date 2022/8/29
     */
    void savaResourceReturnPrimaryKey(Resource resource);

    /**
     * 根据 id 删除资源
     *
     * @param id 资源主键
     * @author Thou
     * @date 2022/8/29
     */
    void removeResourceById(@Param("id") Integer id);

    /**
     * 根据 id 更新资源
     *
     * @param resource 封装了资源信息的资源对象
     * @author Thou
     * @date 2022/8/29
     */
    void updateResourceById(Resource resource);

    /**
     * 根据 chapterId 批量修改资源状态
     *
     * @param chapterIds 章节主键集合
     * @param status 状态
     * @author Thou
     * @date 2022/9/9
     */
    void updateStatusByChapterIds(@Param("ids") List<Integer> chapterIds, @Param("status") Integer status);

    /**
     * 根据 id 查询资源
     *
     * @param id 资源主键
     * @return com.thou.entity.Resource
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/29
     */
    Resource getResourceById(@Param("id") Integer id) throws DataAccessException;

    /**
     * 根据 chapterId 查询资源
     *
     * @param chapterId 章节主键
     * @return com.itany.netclass.entity.Resource
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/9
     */
    Resource getResourceByChapterId(@Param("cid") Integer chapterId) throws DataAccessException;

    /**
     * 根据 title 查询资源
     *
     * @param title 资源标题
     * @return com.itany.netclass.entity.Resource
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/9
     */
    Resource getResourceByTitle(@Param("title") String title) throws DataAccessException;

    /**
     * 根据 id 查询资源，包含发布用户和所属章节信息
     *
     * @param id 资源主键
     * @return com.thou.entity.Resource
     * @author Thou
     * @date 2022/8/29
     */
    Resource getResourceWithUserAndChapterById(@Param("id") Integer id);

    /**
     * 根据条件查询所有资源
     *
     * @param resourceQuery 封装了资源查询条件的查询对象
     * @return java.util.List<com.thou.entity.Resource>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/29
     */
    List<Resource> listResources(ResourceQuery resourceQuery) throws DataAccessException;

    /**
     * 根据条件查询所有资源，包含上传用户的信息
     *
     * @param resourceQuery 封装了资源查询条件的查询对象
     * @return java.util.List<com.thou.entity.Resource>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/29
     */
    List<Resource> listResourcesWithUser(ResourceQuery resourceQuery) throws DataAccessException;

    /**
     * 根据前台查询条件分页查询用户发布资源
     *
     * @param resourceQuery 封装了资源查询条件的查询对象
     * @return java.util.List<com.itany.netclass.entity.Resource>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/11
     */
    List<Resource> listForFront(ResourceQuery resourceQuery) throws DataAccessException;

    /**
     * 根据 userId 查询用户上传的资源
     *
     * @param userId 用户主键
     * @return java.util.List<com.itany.netclass.entity.Resource>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/12
     */
    List<Resource> listUserResource(@Param("uid") Integer userId) throws DataAccessException;
}
