package com.itany.netclass.dao;

import com.itany.netclass.entity.Chapter;
import com.itany.netclass.exception.DataAccessException;
import com.itany.netclass.query.ChapterQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 章节持久层接口
 *
 * @author Thou
 * @date 2022/8/29
 */
public interface ChapterMapper {

    /**
     * 新增一个章节
     *
     * @param chapter 封装了章节信息的章节对象
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/29
     */
    void saveChapter(Chapter chapter) throws DataAccessException;

    /**
     * 新增一个章节，并返回主键
     *
     * @param chapter 封装了章节信息的章节对象
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/9
     */
    void saveChapterReturnPrimaryKey(Chapter chapter) throws DataAccessException;

    /**
     * 根据 id 删除章节
     *
     * @param id 章节主键
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/29
     */
    void removeChapterById(@Param("id") Integer id) throws DataAccessException;

    /**
     * 根据 id 修改章节
     *
     * @param chapter 封装了章节信息的章节对象
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/29
     */
    void updateChapterById(Chapter chapter) throws DataAccessException;

    /**
     * 根据 id 批量修改章节状态
     *
     * @param ids 章节主键集合
     * @param status 状态
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/9
     */
    void updateStatusByIds(@Param("ids") List<Integer> ids, @Param("status") Integer status) throws DataAccessException;

    /**
     * 根据 id 查询章节
     *
     * @param id 章节主键
     * @return com.thou.entity.Chapter
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/29
     */
    Chapter getChapterById(@Param("id") Integer id) throws DataAccessException;

    /**
     * 根据 id 查询章节，包含资源信息
     *
     * @param id 章节主键
     * @return com.itany.netclass.entity.Chapter
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/9
     */
    Chapter getChapterWithResourceById(@Param("id") Integer id) throws DataAccessException;

    /**
     * 根据 id 查询章节，包含课程信息
     *
     * @param id 章节主键
     * @return com.thou.entity.Chapter
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/29
     */
    Chapter getChapterWithCourseById(@Param("id") Integer id) throws DataAccessException;

    /**
     * 根据 title 查询章节
     *
     * @param title 章节标题
     * @return com.itany.netclass.entity.Chapter
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/9
     */
    Chapter getChapterByTitle(@Param("title") String title) throws DataAccessException;

    /**
     * 根据 title 和 courseId 查询章节
     *
     * @param title 章节标题
     * @param courseId 课程主键
     * @return com.itany.netclass.entity.Chapter
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/11
     */
    Chapter getChapterByTitleAndCourseId(@Param("title") String title, @Param("cid") Integer courseId) throws DataAccessException;

    /**
     * 查询所有章节
     *
     * @return java.util.List<com.thou.entity.Chapter>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/8/29
     */
    List<Chapter> listChapters() throws DataAccessException;

    /**
     * 根据条件查询指定 courseId 的章节信息
     *
     * @param chapterQuery 封装了章节查询参数的章节查询对象
     * @return java.util.List<com.itany.netclass.entity.Chapter>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/8
     */
    List<Chapter> listChaptersByCourseId(ChapterQuery chapterQuery) throws DataAccessException;

    /**
     * 根据 courseId 查找当前课程下的所有章节的 id 集合
     *
     * @param courseId 课程主键
     * @return java.util.List<java.lang.Integer>
     * @throws DataAccessException 数据访问异常，当数据库访问失败时抛出此异常
     * @author Thou
     * @date 2022/9/9
     */
    List<Integer> listChapterIdsByCourseId(@Param("cid") Integer courseId) throws DataAccessException;
}
