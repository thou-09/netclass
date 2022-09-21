package com.itany.netclass.constant;

/**
 * 评论相关常量
 *
 * @author Thou
 * @date 2022/8/30
 */
public interface CommentConsts {

    /**
     * 评论状态：启用
     */
    Integer COMMENT_STATUS_ENABLE = 1;
    /**
     * 评论状态：禁用
     */
    Integer COMMENT_STATUS_DISABLE = -1;
    /**
     * 评论状态：待审核
     */
    Integer COMMENT_STATUS_WAITING = 2;
}
