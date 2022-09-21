package com.itany.netclass.dao;

import com.itany.netclass.entity.User;
import com.itany.netclass.exception.DataAccessException;
import com.itany.netclass.query.UserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户持久层接口
 *
 * @author Thou
 * @date 2022/8/29
 */
public interface UserMapper {

    /**
     * 新增一个用户，并返回主键
     *
     * @param user 封装了用户信息的用户对象
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/29
     */
    void saveUserReturnPrimaryKey(User user) throws DataAccessException;

    /**
     * 批量插入用户
     *
     * @param users 封装了用户信息的用户对象集合
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/31
     */
    void saveUsers(List<User> users) throws DataAccessException;

    /**
     * 根据 id 删除用户
     *
     * @param id 用户主键
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/29
     */
    void removeUserById(@Param("id") Integer id) throws DataAccessException;

    /**
     * 根据 id 修改用户<br/>
     * 可修改的字段，password, nickname, email, signDate, status
     *
     * @param user 封装了用户信息的用户对象
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/29
     */
    void updateUserById(User user) throws DataAccessException;

    /**
     * 根据 id 查询用户
     *
     * @param id 用户主键
     * @return com.thou.entity.User
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/29
     */
    User getUserById(@Param("id") Integer id) throws DataAccessException;

    /**
     * 根据 loginName 查询用户
     *
     * @param loginName 登录名
     * @return com.itany.netclass.entity.User
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/10
     */
    User getUserByLoginName(@Param("loginName") String loginName) throws DataAccessException;

    /**
     * 根据 email 查询用户
     *
     * @param email 邮箱
     * @return com.itany.netclass.entity.User
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/6
     */
    User getUserByEmail(@Param("email") String email) throws DataAccessException;

    /**
     * 根据 loginName 和 password 查询用户
     *
     * @param loginName 登录用户名
     * @param password 密码
     * @return com.thou.entity.User
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/29
     */
    User getUserByLoginNameAndPassword(@Param("loginName") String loginName, @Param("password") String password) throws DataAccessException;

    /**
     * 根据条件查询所有用户
     *
     * @param userQuery 查询条件
     * @return java.util.List<com.thou.entity.User>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/29
     */
    List<User> listUsers(UserQuery userQuery) throws DataAccessException;

    /**
     * 根据登录名和邮箱查询用户
     *
     * @param loginName 登录名
     * @param email 邮箱
     * @return com.itany.netclass.entity.User
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/13
     */
    User getUserByLoginNameAndEmail(@Param("loginName") String loginName, @Param("email") String email) throws DataAccessException;
}
