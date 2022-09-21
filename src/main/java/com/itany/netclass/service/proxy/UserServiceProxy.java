package com.itany.netclass.service.proxy;

import com.github.pagehelper.PageInfo;
import com.itany.netclass.entity.User;
import com.itany.netclass.exception.DataAccessException;
import com.itany.netclass.exception.ServiceException;
import com.itany.netclass.exception.StatusErrorException;
import com.itany.netclass.exception.UserExistException;
import com.itany.netclass.exception.UserNotFindException;
import com.itany.netclass.exception.UserPermissionException;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.query.UserQuery;
import com.itany.netclass.service.UserService;
import com.itany.netclass.transaction.TransactionManager;

import java.text.ParseException;
import java.util.zip.DataFormatException;

/**
 * 用户 ServiceProxy
 *
 * @author Thou
 * @date 2022/8/30
*/
public class UserServiceProxy implements UserService {

    private final TransactionManager transactionManager = ObjectFactory.getObject("transactionManager");
    private final UserService userService = ObjectFactory.getObject("userServiceTarget");

    @Override
    public void register(User user) throws DataFormatException, UserExistException {
        try {
            transactionManager.beginTransaction();
            userService.register(user);
            transactionManager.commit();
        } catch (UserExistException | DataFormatException e) {
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
    public User login(User u) throws UserNotFindException, StatusErrorException {
        User user = null;
        try {
            transactionManager.beginTransaction();
            user = userService.login(u);
            transactionManager.commit();
        } catch (UserNotFindException | StatusErrorException e) {
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
        return user;
    }

    @Override
    public User managerLogin(User u) throws UserNotFindException, UserPermissionException,
            StatusErrorException {
        User user = null;
        try {
            transactionManager.beginTransaction();
            user = userService.managerLogin(u);
            transactionManager.commit();
        } catch (UserNotFindException | UserPermissionException | StatusErrorException e) {
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
        return user;
    }

    @Override
    public PageInfo<User> listUsers(Integer pageNo, Integer pageSize, UserQuery userQuery) {
        PageInfo<User> info = null;
        try {
            transactionManager.beginTransaction();
            info = userService.listUsers(pageNo, pageSize, userQuery);
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
    public User getUser(Integer id) throws UserNotFindException {
        User user = null;
        try {
            transactionManager.beginTransaction();
            user = userService.getUser(id);
            transactionManager.commit();
        } catch (UserNotFindException e) {
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
        return user;
    }

    @Override
    public void modify(User user) throws DataFormatException, UserExistException {
        try {
            transactionManager.beginTransaction();
            userService.modify(user);
            transactionManager.commit();
        } catch (DataFormatException | UserExistException e) {
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
    public User signIn(User u){
        User user = null;
        try {
            transactionManager.beginTransaction();
            user = userService.signIn(u);
            transactionManager.commit();
        } catch (DataAccessException e) {
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        }
        return user;
    }

    @Override
    public User getGoldPoint(User user) {
        User u = null;
        try {
            transactionManager.beginTransaction();
            u = userService.getGoldPoint(user);
            transactionManager.commit();
        } catch (DataAccessException e) {
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        }
        return u;
    }

    @Override
    public User modify4Front(User user) throws DataFormatException {
        User u = null;
        try {
            transactionManager.beginTransaction();
            u = userService.modify4Front(user);
            transactionManager.commit();
        } catch (DataFormatException e) {
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
        return u;
    }

    @Override
    public void forget(User user) throws UserExistException {
        try {
            transactionManager.beginTransaction();
            userService.forget(user);
            transactionManager.commit();
        } catch (UserExistException e) {
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
}
