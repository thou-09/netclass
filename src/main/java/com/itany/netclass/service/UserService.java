package com.itany.netclass.service;

import com.github.pagehelper.PageInfo;
import com.itany.netclass.entity.User;
import com.itany.netclass.exception.StatusErrorException;
import com.itany.netclass.exception.UserExistException;
import com.itany.netclass.exception.UserNotFindException;
import com.itany.netclass.exception.UserPermissionException;
import com.itany.netclass.query.UserQuery;

import java.text.ParseException;
import java.util.zip.DataFormatException;

/**
 * 用户 Service interface
 *
 * @author Thou
 * @date 2022/8/30
*/
public interface UserService {

    /**
     * 用户注册<br>
     * loginName 不可重复，email 不可重复<br/>
     * create_date 为当前时间<br>
     * role 为 UserConsts 中的常量<br>
     * status 为 UserConsts 中的常量<br>
     * 用户注册可获得 50 积分
     *
     * @param user 封装了用户信息的用户对象
     * @throws DataFormatException 数据格式异常
     * @throws UserExistException 用户已存在异常
     * @author Thou
     * @date 2022/8/31
     */
    void register(User user) throws DataFormatException, UserExistException;

    /**
     * 用户登录<br>
     * 只有 role 为 normal，且 status 为启用的用户才能登录前台<br>
     * 用户登录后修改 signDate 为当前时间
     *
     * @param user 封装了用户信息的用户对象
     * @return com.itany.netclass.entity.User
     * @throws UserNotFindException 用户不存在异常，当根据登录名和密码查找用户不存在时抛出此异常
     * @throws StatusErrorException 状态错误异常，当用户状态为禁用时抛出此异常
     * @throws ParseException 时期解析异常
     * @author Thou
     * @date 2022/9/1
     */
    User login(User user) throws UserNotFindException, StatusErrorException, ParseException;

    /**
     * 管理员登录<br>
     * 只有 role 为 admin，且 status 为启用的用户才能登录前台<br>
     * 用户登录后修改 signDate 为当前时间
     *
     * @param user 封装了登录名和密码的用户对象
     * @return com.itany.netclass.entity.User
     * @throws UserNotFindException 用户不存在异常，当根据登录名和密码查找用户不存在时抛出此异常
     * @throws UserPermissionException 用户权限异常，当用户权限不足时抛出此异常
     * @throws StatusErrorException 状态错误异常，当用户状态为禁用时抛出此异常
     * @author Thou
     * @date 2022/9/3
     */
    User managerLogin(User user) throws UserNotFindException, UserPermissionException,
            StatusErrorException;

    /**
     * 根据条件，分页查询所有用户
     *
     * @param pageNo 当前页
     * @param pageSize 页面大小
     * @param userQuery 查询条件
     * @return com.github.pagehelper.PageInfo<com.itany.netclass.entity.User>
     * @author Thou
     * @date 2022/8/31
     */
    PageInfo<User> listUsers(Integer pageNo, Integer pageSize, UserQuery userQuery);

    /**
     * 根据 id 查询用户
     *
     * @param id 用户主键
     * @return com.itany.netclass.entity.User
     * @throws UserNotFindException 用户不存在异常，当用户不存在时抛出此异常
     * @author Thou
     * @date 2022/9/5
     */
    User getUser(Integer id) throws UserNotFindException;

    /**
     * 修改用户信息
     *
     * @param user 封装了用户信息的用户对象
     * @throws DataFormatException 数据格式错误，当数据不符合格式时抛出此异常
     * @throws UserExistException 用户存在异常，当用户已存在时抛出此异常
     * @author Thou
     * @date 2022/9/3
     */
    void modify(User user) throws DataFormatException, UserExistException;

    /**
     * 用户签到
     *
     * @param user 登录用户
     * @return com.itany.netclass.entity.User
     * @author Thou
     * @date 2022/9/10
     */
    User signIn(User user);

    /**
     * 查询用户金币和积分
     *
     * @param user 登录用户
     * @return com.itany.netclass.entity.User
     * @author Thou
     * @date 2022/9/10
     */
    User getGoldPoint(User user);

    /**
     * 修改用户信息
     *
     * @param user 封装了用户信息的用户对象
     * @return com.itany.netclass.entity.User
     * @throws DataFormatException 数据格式异常
     * @throws UserExistException 邮箱已被使用
     * @author Thou
     * @date 2022/9/13
     */
    User modify4Front(User user) throws DataFormatException, UserExistException;

    /**
     * 忘记密码
     *
     * @param user 封装了用户信息的用户对象
     * @throws UserExistException 用户不存在
     * @author Thou
     * @date 2022/9/13
     */
    void forget(User user) throws UserExistException;
}
