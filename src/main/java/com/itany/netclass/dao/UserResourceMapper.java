package com.itany.netclass.dao;

import com.itany.netclass.entity.UserResource;
import com.itany.netclass.exception.DataAccessException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户已购资源持久层接口
 *
 * @author Thou
 * @date 2022/8/31
 */
public interface UserResourceMapper {

    /**
     * 新增一个用户已购资源
     *
     * @param userResource 封装了用户已购资源信息的用户已购资源对象
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/31
     */
    void saveUserResource(UserResource userResource) throws DataAccessException;

    /**
     * 根据 id 删除用户已购资源
     *
     * @param id 用户已购资源主键
     * @author Thou
     * @date 2022/8/31
     */
    void removeUserResourceById(@Param("id") Integer id);

    /**
     * 根据 id 修改用户已购资源，可修改字段 seeTime，updateDate
     *
     * @param userResource 封装了用户已购资源信息的用户已购资源对象
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/31
     */
    void updateUserResourceById(UserResource userResource) throws DataAccessException;

    /**
     * 根据 id 查询用户已购资源
     *
     * @param id 用户已购资源主键
     * @return com.itany.netclass.entity.UserResource
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/31
     */
    UserResource getUserResourceById(@Param("id") Integer id) throws DataAccessException;

    /**
     * 根据 userId 查询用户已购资源
     *
     * @param userId 用户主键
     * @return java.util.List<com.itany.netclass.entity.UserResource>
     * @author Thou
     * @date 2022/8/31
     */
    List<UserResource> listUserResourcesByUserId(@Param("uid") Integer userId);

    /**
     * 根据 userId 和 resourceId 查询用户已购资源
     *
     * @param userId 用户主键
     * @param resourceId 资源主键
     * @return com.itany.netclass.entity.UserResource
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/12
     */
    UserResource getUserResourceByUserIdAndResourceId(@Param("uid") Integer userId, @Param("rid") Integer resourceId)
            throws DataAccessException;

    /**
     * 根据 userId 查询用户购买的资源
     *
     * @param userId 用户主键
     * @return java.util.List<com.itany.netclass.entity.Resource>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/13
     */
    List<UserResource> listPurchase(@Param("uid") Integer userId) throws DataAccessException;
}
