package com.itany.netclass.service;

import com.github.pagehelper.PageInfo;
import com.itany.netclass.entity.Resource;
import com.itany.netclass.entity.User;
import com.itany.netclass.entity.UserResource;
import com.itany.netclass.exception.GoldPointsErrorException;
import com.itany.netclass.exception.ResourceExistException;
import com.itany.netclass.exception.UserNotFindException;
import com.itany.netclass.query.ResourceQuery;
import com.itany.netclass.vo.UserResourceVO;

/**
 * 资源 Service interface
 *
 * @author Thou
 * @date 2022/9/9
 */
public interface ResourceService {

    /**
     * 根据 chapterId 查询资源
     *
     * @param chapterId 章节主键
     * @return com.itany.netclass.entity.Resource
     * @throws ResourceExistException 资源不存在异常
     * @author Thou
     * @date 2022/9/9
     */
    Resource getByChapterId(Integer chapterId) throws ResourceExistException;

    /**
     * 根据 id 查询资源
     *
     * @param id 资源主键
     * @return com.itany.netclass.entity.Resource
     * @throws ResourceExistException 资源不存在异常
     * @author Thou
     * @date 2022/9/9
     */
    Resource getById(Integer id) throws ResourceExistException;

    /**
     * 根据条件分页查询资源，包含上传用户信息
     *
     * @param pageNo 当前页
     * @param pageSize 页面大小
     * @param resourceQuery 封装了资源查询参数的对象
     * @return com.github.pagehelper.PageInfo<com.itany.netclass.entity.Resource>
     * @author Thou
     * @date 2022/9/9
     */
    PageInfo<Resource> listResourcesWithUser(Integer pageNo, Integer pageSize, ResourceQuery resourceQuery);

    /**
     * 根据 id 修改资源状态
     *
     * @param resource 封装了资源信息的资源对象
     * @author Thou
     * @date 2022/9/9
     */
    void modifyStatus(Resource resource);

    /**
     * 根据前台查询条件分页查询用户发布资源
     *
     * @param pageNo 当前页
     * @param pageSize 页面大小
     * @param resourceQuery 封装了资源查询参数的对象
     * @return com.github.pagehelper.PageInfo<com.itany.netclass.entity.Resource>
     * @author Thou
     * @date 2022/9/11
     */
    PageInfo<Resource> frontSelect(Integer pageNo, Integer pageSize, ResourceQuery resourceQuery);

    /**
     * 用户上传资源
     *
     * @param urVO 封装了用户资源信息的值对象
     * @throws ResourceExistException 资源已存在异常，当资源名称相同时抛出此异常
     * @author Thou
     * @date 2022/9/12
     */
    void add(UserResourceVO urVO) throws ResourceExistException;

    /**
     * 根据 userId 查询用户上传的资源
     *
     * @param pageNo 当前页
     * @param pageSize 页面大小
     * @param userId 用户主键
     * @return com.github.pagehelper.PageInfo<com.itany.netclass.entity.Resource>
     * @throws UserNotFindException 用户不存在异常
     * @author Thou
     * @date 2022/9/12
     */
    PageInfo<Resource> listUserResource(Integer pageNo, Integer pageSize, Integer userId) throws UserNotFindException;

    /**
     * 修改指定 id 的资源信息
     *
     * @param urVO 封装了用户资源信息的值对象
     * @throws ResourceExistException 资源已存在异常，当资源名称相同时抛出此异常
     * @author Thou
     * @date 2022/9/12
     */
    void modify(UserResourceVO urVO) throws ResourceExistException;

    /**
     * 删除指定 id 的资源信息
     *
     * @param id 资源主键
     * @throws ResourceExistException 资源已存在异常，当资源名称相同时抛出此异常
     * @author Thou
     * @date 2022/9/12
     */
    void delete(Integer id) throws ResourceExistException;

    /**
     * 根据 id 查询资源，包含评论信息，课程信息（前台使用）
     *
     * @param id 资源主键
     * @return com.itany.netclass.entity.Resource
     * @throws ResourceExistException 资源不存在异常
     * @author Thou
     * @date 2022/9/12
     */
    Resource search4Front(Integer id) throws ResourceExistException;

    /**
     * 检查用户是否购买了资源
     *
     * @param uid 用户主键
     * @param resourceId 资源主键
     * @return boolean
     * @throws ResourceExistException 资源不存在异常
     * @author Thou
     * @date 2022/9/12
     */
    Boolean checkOwned(Integer uid, Integer resourceId) throws ResourceExistException;

    /**
     * 购买资源，返回更新后的用户信息，保存在 session 中
     *
     * @param user 登录用户
     * @param resourceId 资源编号
     * @return com.itany.netclass.entity.User
     * @throws ResourceExistException 资源不存在异常
     * @throws GoldPointsErrorException 积分金币不足
     * @author Thou
     * @date 2022/9/12
     */
    User purchase(User user, Integer resourceId) throws ResourceExistException, GoldPointsErrorException;

    /**
     * 根据 userId 查询用户已购买的资源
     *
     * @param pageNo 当前页
     * @param pageSize 页面大小
     * @param userId 用户主键
     * @return com.github.pagehelper.PageInfo<com.itany.netclass.entity.Resource>
     * @throws UserNotFindException 用户不存在异常
     * @author Thou
     * @date 2022/9/13
     */
    PageInfo<UserResource> listPurchase(Integer pageNo, Integer pageSize, Integer userId) throws UserNotFindException;

    /**
     * 更新 userResource 信息
     *
     * @param userId 用户主键
     * @param resourceId 资源主键
     * @param seeTime 观看时间点
     * @return java.lang.Boolean
     * @author Thou
     * @date 2022/9/13
     */
    void updateUserResource(Integer userId, Integer resourceId, String seeTime);
}
