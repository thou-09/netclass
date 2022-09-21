package com.itany.netclass.service.proxy;

import com.github.pagehelper.PageInfo;
import com.itany.netclass.entity.Chapter;
import com.itany.netclass.entity.Comment;
import com.itany.netclass.entity.Praise;
import com.itany.netclass.exception.CommentExistException;
import com.itany.netclass.exception.DataAccessException;
import com.itany.netclass.exception.ServiceException;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.query.CommentQuery;
import com.itany.netclass.service.CommentService;
import com.itany.netclass.transaction.TransactionManager;

/**
 * 评论 ServiceProxy
 *
 * @author Thou
 * @date 2022/9/9
 */
public class CommentServiceProxy implements CommentService {

    private final TransactionManager transactionManager = ObjectFactory.getObject("transactionManager");
    private final CommentService commentService = ObjectFactory.getObject("commentServiceTarget");

    @Override
    public PageInfo<Comment> listComments(Integer pageNo, Integer pageSize, CommentQuery commentQuery) {
        PageInfo<Comment> info = null;
        try {
            transactionManager.beginTransaction();
            info = commentService.listComments(pageNo, pageSize, commentQuery);
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
    public void modify(Comment comment) throws CommentExistException {
        try {
            transactionManager.beginTransaction();
            commentService.modify(comment);
            transactionManager.commit();
        } catch (CommentExistException e) {
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

    @Override
    public void add(Comment comment) {
        try {
            transactionManager.beginTransaction();
            commentService.add(comment);
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
    public void praise(Praise praise) throws CommentExistException {
        try {
            transactionManager.beginTransaction();
            commentService.praise(praise);
            transactionManager.commit();
        } catch (CommentExistException e) {
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
}
