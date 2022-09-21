package com.itany.netclass.dao;

import com.itany.netclass.entity.Praise;
import com.itany.netclass.exception.DataAccessException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 点赞记录持久层接口
 *
 * @author Thou
 * @date 2022/8/31
 */
public interface PraiseMapper {

    /**
     * 新增一个点赞记录
     *
     * @param praise 封装了点赞记录信息的点赞记录对象
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/31
     */
    void savePraise(Praise praise) throws DataAccessException;

    /**
     * 根据 id 删除点赞记录
     *
     * @param id 点赞记录主键
     * @author Thou
     * @date 2022/8/31
     */
    void removePraiseById(@Param("id") Integer id);

    /**
     * 根据 id 查询点赞记录
     *
     * @param id 点赞记录主键
     * @return com.itany.netclass.entity.Praise
     * @author Thou
     * @date 2022/8/31
     */
    Praise getPraiseById(@Param("id") Integer id);

    /**
     * 根据 userId 和 commentId 查询点赞记录
     *
     * @param userId 用户主键
     * @param commentId 评论主键
     * @return com.itany.netclass.entity.Praise
     * @author Thou
     * @date 2022/9/13
     */
    Praise getPraiseByUserIdAndCommentId(@Param("uid") Integer userId, @Param("cid") Integer commentId);

    /**
     * 根据 userId 查询点赞记录
     *
     * @param userId 用户主键
     * @return java.util.List<com.itany.netclass.entity.Praise>
     * @author Thou
     * @date 2022/8/31
     */
    List<Praise> listPraisesByUserId(@Param("uid") Integer userId);

    /**
     * 根据 commentId 查询点赞记录
     *
     * @param commentId 评论主键
     * @return java.util.List<com.itany.netclass.entity.Praise>
     * @author Thou
     * @date 2022/8/31
     */
    List<Praise> listPraisesByCommentId(@Param("cid") Integer commentId);

    /**
     * 根据 commentId 查询点赞记录数量
     *
     * @param commentId 评论主键
     * @return java.lang.Integer
     * @author Thou
     * @date 2022/8/31
     */
    Integer countPraiseByCommentId(@Param("cid") Integer commentId);
}
