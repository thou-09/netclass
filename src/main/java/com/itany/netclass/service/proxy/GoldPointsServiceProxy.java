package com.itany.netclass.service.proxy;

import com.github.pagehelper.PageInfo;
import com.itany.netclass.entity.GoldPoints;
import com.itany.netclass.entity.User;
import com.itany.netclass.exception.DataAccessException;
import com.itany.netclass.exception.GoldPointsErrorException;
import com.itany.netclass.exception.ServiceException;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.service.GoldPointsService;
import com.itany.netclass.transaction.TransactionManager;

/**
 * 积分金币 ServiceProxy
 *
 * @author Thou
 * @date 2022/9/13
 */
public class GoldPointsServiceProxy implements GoldPointsService {

    private final TransactionManager transactionManager = ObjectFactory.getObject("transactionManager");
    private final GoldPointsService goldPointsService = ObjectFactory.getObject("goldPointsServiceTarget");

    @Override
    public PageInfo<GoldPoints> list(Integer pageNo, Integer pageSize, Integer userId) {
        PageInfo<GoldPoints> info = null;
        try {
            transactionManager.beginTransaction();
            info = goldPointsService.list(pageNo, pageSize, userId);
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
    public User exchange(GoldPoints goldPoints, User loginUser) throws GoldPointsErrorException {
        User user = null;
        try {
            transactionManager.beginTransaction();
            user = goldPointsService.exchange(goldPoints, loginUser);
            transactionManager.commit();
        } catch (GoldPointsErrorException e) {
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
}
