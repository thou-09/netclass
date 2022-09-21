package com.itany.netclass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netclass.constant.CommentConsts;
import com.itany.netclass.dao.CommentMapper;
import com.itany.netclass.dao.PraiseMapper;
import com.itany.netclass.entity.Comment;
import com.itany.netclass.entity.Praise;
import com.itany.netclass.exception.CommentExistException;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.query.CommentQuery;
import com.itany.netclass.service.CommentService;

import java.util.Date;
import java.util.List;

/**
 * 评论 ServiceImpl
 *
 * @author Thou
 * @date 2022/9/9
 */
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper = ObjectFactory.getObject("commentMapper");
    private final PraiseMapper praiseMapper = ObjectFactory.getObject("praiseMapper");

    @Override
    public PageInfo<Comment> listComments(Integer pageNo, Integer pageSize, CommentQuery commentQuery) {
        PageHelper.startPage(pageNo, pageSize);
        List<Comment> list = commentMapper.listCommentsWithUser(commentQuery);
        return new PageInfo<>(list);
    }

    @Override
    public void modify(Comment comment) throws CommentExistException {
        Comment c = commentMapper.getCommentById(comment.getId());
        if (null == c) {
            throw new CommentExistException("评论不存在");
        }
        commentMapper.updateCommentById(comment);
    }

    @Override
    public void add(Comment comment) {
        comment.setStatus(CommentConsts.COMMENT_STATUS_WAITING);
        comment.setCreateDate(new Date());
        commentMapper.saveComment(comment);
    }

    @Override
    public void praise(Praise praise) throws CommentExistException {
        Praise pra = praiseMapper.getPraiseByUserIdAndCommentId(praise.getUserId(), praise.getCommentId());
        if (pra != null) {
            throw new CommentExistException("您已点赞过该评论");
        }
        // 这行代码不能删掉
        commentMapper.updateCommentById(commentMapper.getCommentById(praise.getCommentId()));

        praise.setCreateDate(new Date());
        praiseMapper.savePraise(praise);
    }
}
