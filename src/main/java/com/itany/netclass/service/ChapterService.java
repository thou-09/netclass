package com.itany.netclass.service;

import com.github.pagehelper.PageInfo;
import com.itany.netclass.entity.Chapter;
import com.itany.netclass.exception.ChapterExistException;
import com.itany.netclass.exception.CourseExistException;
import com.itany.netclass.exception.ResourceExistException;
import com.itany.netclass.exception.StatusErrorException;
import com.itany.netclass.query.ChapterQuery;
import com.itany.netclass.vo.CourseResourceVO;

/**
 * 章节 Service interface
 *
 * @author Thou
 * @date 2022/9/5
 */
public interface ChapterService {

    /**
     * 根据条件，分页查询指定 courseId 的章节信息
     *
     * @param pageNo 当前页
     * @param pageSize 页面大小
     * @param userQuery 查询条件
     * @return com.github.pagehelper.PageInfo<com.itany.netclass.entity.User>
     * @author Thou
     * @date 2022/8/31
     */
    PageInfo<Chapter> listChapters(Integer pageNo, Integer pageSize, ChapterQuery userQuery);

    /**
     * 添加章节信息，并添加资源信息<br>
     * 章节标题不可重复<br>
     * 资源标题不可重复
     *
     * @param crVO 封装了课程资源（章节和资源）信息的值对象
     * @throws ChapterExistException 章节已存在异常，当章节名称相同时抛出此异常
     * @throws ResourceExistException 资源已存在异常，当资源名称相同时抛出此异常
     * @throws CourseExistException 课程不存在异常，当章节所属课程不存在时抛出此异常
     * @author Thou
     * @date 2022/9/9
     */
    void add(CourseResourceVO crVO) throws ChapterExistException, ResourceExistException, CourseExistException;

    /**
     * 根据 id 查询章节信息，包含资源信息
     *
     * @param id 章节主键
     * @return com.itany.netclass.entity.Chapter
     * @throws ChapterExistException 章节不存在异常，当根据 id 查找不到章节时抛出此异常
     * @author Thou
     * @date 2022/9/9
     */
    Chapter getChapterWithResourceById(Integer id) throws ChapterExistException;

    /**
     * 根据 id 修改章节信息，并修改资源信息<br>
     * 章节标题不可重复<br>
     * 资源标题不可重复
     *
     * @param crVO 封装了课程资源（章节和资源）信息的值对象
     * @throws ChapterExistException 章节已存在异常，当章节名称相同（不是自己）时抛出此异常
     * @throws ResourceExistException 资源已存在异常，当资源名称相同（不是自己）时抛出此异常
     * @author Thou
     * @date 2022/9/9
     */
    void modify(CourseResourceVO crVO) throws ChapterExistException, ResourceExistException;

    /**
     * 根据 id 修改章节状态，并且修改资源状态
     *
     * @param chapter 封装了章节信息的章节对象
     * @throws ChapterExistException 章节已存在异常，当章节名称相同（不是自己）时抛出此异常
     * @throws StatusErrorException 状态异常，当前章节所属课程被禁用时，修改章节状态为启用时抛出此异常
     * @author Thou
     * @date 2022/9/9
     */
    void modifyStatus(Chapter chapter) throws StatusErrorException, ChapterExistException;
}
