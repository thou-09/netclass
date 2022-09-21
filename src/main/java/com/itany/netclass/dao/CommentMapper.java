package com.itany.netclass.dao;

import com.itany.netclass.entity.Comment;
import com.itany.netclass.entity.User;
import com.itany.netclass.exception.DataAccessException;
import com.itany.netclass.query.CommentQuery;
import com.itany.netclass.query.UserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论持久层接口
 *
 * @author Thou
 * @date 2022/8/31
 */
public interface CommentMapper {

    /**
     * 新增一个评论
     *
     * @param comment 封装了评论信息的评论对象
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/31
     */
    void saveComment(Comment comment) throws DataAccessException;

    /**
     * 根据 id 删除评论
     *
     * @param id 评论主键
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/31
     */
    void removeCommentById(@Param("id") Integer id) throws DataAccessException;

    /**
     * 根据 id 修改评论内容
     *
     * @param comment 封装了评论信息的评论对象
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/9
     */
    void updateCommentById(Comment comment) throws DataAccessException;

    /**
     * 根据 id 查询评论
     *
     * @param id 评论主键
     * @return com.itany.netclass.entity.Comment
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/31
     */
    Comment getCommentById(@Param("id") Integer id) throws DataAccessException;

    /**
     * 根据 userId 查询评论
     *
     * @param userId 用户主键
     * @return java.util.List<com.itany.netclass.entity.Comment>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/31
     */
    List<Comment> listCommentsByUserId(@Param("uid") Integer userId) throws DataAccessException;

    /**
     * 根据 resourceId 查询评论
     *
     * @param resourceId 资源主键
     * @return java.util.List<com.itany.netclass.entity.Comment>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/31
     */
    List<Comment> listCommentsByResourceId(@Param("rid") Integer resourceId) throws DataAccessException;

    /**
     * 根据条件查询评论，包含发布的用户信息
     *
     * @param commentQuery 查询条件
     * @return java.util.List<com.thou.entity.Comment>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/9
     */
    List<Comment> listCommentsWithUser(CommentQuery commentQuery) throws DataAccessException;

    /**
     * 根据 userId 查询评论数量
     *
     * @param userId 用户主键
     * @return java.lang.Integer
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/31
     */
    Integer countCommentByUserId(@Param("uid") Integer userId) throws DataAccessException;

    /**
     * 根据 resourceId 查询评论数量
     *
     * @param resourceId 资源主键
     * @return java.lang.Integer
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/31
     */
    Integer countCommentByResourceId(@Param("rid") Integer resourceId) throws DataAccessException;

    /**
     * 根据 courseId 查询评论，和点赞
     *
     * @param id 课程主键
     * @return java.util.List<com.itany.netclass.entity.Comment>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/12
     */
    List<Comment> listCommentsByCourseId(@Param("id") Integer id) throws DataAccessException;

    /**
     * 根据资源 id 查询评论，和点赞
     *
     * @param id 资源主键
     * @return java.util.List<com.itany.netclass.entity.Comment>
     * @author Thou
     * @date 2022/9/12
     */
    List<Comment> listCommentsWithUserByResourceId(Integer id);
}
