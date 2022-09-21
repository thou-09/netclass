package com.itany.netclass.service.proxy;

import com.github.pagehelper.PageInfo;
import com.itany.netclass.entity.Resource;
import com.itany.netclass.entity.User;
import com.itany.netclass.entity.UserResource;
import com.itany.netclass.exception.DataAccessException;
import com.itany.netclass.exception.GoldPointsErrorException;
import com.itany.netclass.exception.ResourceExistException;
import com.itany.netclass.exception.ServiceException;
import com.itany.netclass.exception.UserNotFindException;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.query.ResourceQuery;
import com.itany.netclass.service.ResourceService;
import com.itany.netclass.transaction.TransactionManager;
import com.itany.netclass.vo.UserResourceVO;

/**
 * 资源 ServiceProxy
 *
 * @author Thou
 * @date 2022/9/9
 */
public class ResourceServiceProxy implements ResourceService {

    private final TransactionManager transactionManager = ObjectFactory.getObject("transactionManager");
    private final ResourceService resourceService = ObjectFactory.getObject("resourceServiceTarget");

    @Override
    public Resource getByChapterId(Integer chapterId) throws ResourceExistException {
        Resource resource = null;
        try {
            transactionManager.beginTransaction();
            resource = resourceService.getByChapterId(chapterId);
            transactionManager.commit();
        } catch (ResourceExistException e) {
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
        return resource;
    }

    @Override
    public Resource getById(Integer id) throws ResourceExistException {
        Resource resource = null;
        try {
            transactionManager.beginTransaction();
            resource = resourceService.getById(id);
            transactionManager.commit();
        } catch (ResourceExistException e) {
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
        return resource;
    }

    @Override
    public PageInfo<Resource> listResourcesWithUser(Integer pageNo, Integer pageSize, ResourceQuery resourceQuery) {
        PageInfo<Resource> info = null;
        try {
            transactionManager.beginTransaction();
            info = resourceService.listResourcesWithUser(pageNo, pageSize, resourceQuery);
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
    public void modifyStatus(Resource resource) {
        try {
            transactionManager.beginTransaction();
            resourceService.modifyStatus(resource);
            transactionManager.commit();
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
    public PageInfo<Resource> frontSelect(Integer pageNo, Integer pageSize, ResourceQuery resourceQuery) {
        PageInfo<Resource> info = null;
        try {
            transactionManager.beginTransaction();
            info = resourceService.frontSelect(pageNo, pageSize, resourceQuery);
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
    public void add(UserResourceVO urVO) throws ResourceExistException {
        try {
            transactionManager.beginTransaction();
            resourceService.add(urVO);
            transactionManager.commit();
        } catch (ResourceExistException e) {
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
    public PageInfo<Resource> listUserResource(Integer pageNo, Integer pageSize, Integer userId) throws UserNotFindException {
        PageInfo<Resource> info = null;
        try {
            transactionManager.beginTransaction();
            info = resourceService.listUserResource(pageNo, pageSize, userId);
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
        return info;
    }

    @Override
    public void modify(UserResourceVO urVO) throws ResourceExistException {
        try {
            transactionManager.beginTransaction();
            resourceService.modify(urVO);
            transactionManager.commit();
        } catch (ResourceExistException e) {
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
    public void delete(Integer id) throws ResourceExistException {
        try {
            transactionManager.beginTransaction();
            resourceService.delete(id);
            transactionManager.commit();
        } catch (ResourceExistException e) {
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
    public Resource search4Front(Integer id) throws ResourceExistException {
        Resource resource = null;
        try {
            transactionManager.beginTransaction();
            resource = resourceService.search4Front(id);
            transactionManager.commit();
        } catch (ResourceExistException e) {
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
        return resource;
    }

    @Override
    public Boolean checkOwned(Integer uid, Integer resourceId) throws ResourceExistException {
        Boolean owned = null;
        try {
            transactionManager.beginTransaction();
            owned = resourceService.checkOwned(uid, resourceId);
            transactionManager.commit();
        } catch (ResourceExistException e) {
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
        return owned;
    }

    @Override
    public User purchase(User user, Integer resourceId) throws ResourceExistException, GoldPointsErrorException {
        User u = null;
        try {
            transactionManager.beginTransaction();
            u = resourceService.purchase(user, resourceId);
            transactionManager.commit();
        } catch (ResourceExistException | GoldPointsErrorException e) {
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
        return u;
    }

    @Override
    public PageInfo<UserResource> listPurchase(Integer pageNo, Integer pageSize, Integer userId) throws UserNotFindException {
        PageInfo<UserResource> info = null;
        try {
            transactionManager.beginTransaction();
            info = resourceService.listPurchase(pageNo, pageSize, userId);
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
        return info;
    }

    @Override
    public void updateUserResource(Integer userId, Integer resourceId, String seeTime) {
        try {
            transactionManager.beginTransaction();
            resourceService.updateUserResource(userId, resourceId, seeTime);
            transactionManager.commit();
        } catch (DataAccessException e) {
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback();
            throw new ServiceException("系统繁忙，请稍后尝试");
        }
    }
}
