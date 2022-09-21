package com.itany.netclass.query;

import com.itany.netclass.entity.Comment;

import java.util.Date;

/**
 * 评论查询对象，封装 request 请求中评论相关的查询参数
 *
 * @author Thou
 * @date 2022/9/3
 */
public class CommentQuery extends Comment {

    private static final long serialVersionUID = -2407811159555223654L;
    /**
     * 发布用户的用户名
     */
    @com.itany.netclass.annotation.Comment("用戶名")
    private String userLoginName;
    /**
     * 创建日期开始日期
     */
    private Date createDateStart;
    /**
     * 创建日期结束日期
     */
    private Date createDateEnd;
}
