package com.itany.netclass.service;

import com.github.pagehelper.PageInfo;
import com.itany.netclass.entity.Comment;
import com.itany.netclass.entity.Praise;
import com.itany.netclass.exception.CommentExistException;
import com.itany.netclass.query.CommentQuery;

/**
 * 评论 Service interface
 *
 * @author Thou
 * @date 2022/9/5
 */
public interface CommentService {

    /**
     * 根据条件分页查询评论信息
     *
     * @param pageNo 当前页
     * @param pageSize 页面尺寸
     * @param commentQuery 封装了查询条件的评论查询对象
     * @return com.github.pagehelper.PageInfo<com.itany.netclass.entity.Course>
     * @author Thou
     * @date 2022/9/9
     */
    PageInfo<Comment> listComments(Integer pageNo, Integer pageSize, CommentQuery commentQuery);

    /**
     * 根据 id 修改评论信息
     *
     * @param comment 封装了评论信息的评论对象
     * @throws CommentExistException 评论不存在异常，当前 id 没有对应评论时抛出此异常
     * @author Thou
     * @date 2022/9/9
     */
    void modify(Comment comment) throws CommentExistException;

    /**
     * 发布评论
     *
     * @param comment 封装了评论信息的评论对象
     * @author Thou
     * @date 2022/9/13
     */
    void add(Comment comment);

    /**
     * 点赞评论
     *
     * @param praise 点赞记录
     * @throws CommentExistException 评论不存在
     * @author Thou
     * @date 2022/9/13
     */
    void praise(Praise praise) throws CommentExistException;
}
