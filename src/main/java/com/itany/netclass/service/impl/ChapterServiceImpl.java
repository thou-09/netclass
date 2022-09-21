package com.itany.netclass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.netclass.constant.ChapterConsts;
import com.itany.netclass.constant.CourseTypeConsts;
import com.itany.netclass.constant.ResourceConsts;
import com.itany.netclass.dao.ChapterMapper;
import com.itany.netclass.dao.CourseMapper;
import com.itany.netclass.dao.ResourceMapper;
import com.itany.netclass.entity.Chapter;
import com.itany.netclass.entity.Course;
import com.itany.netclass.entity.CourseType;
import com.itany.netclass.entity.Resource;
import com.itany.netclass.exception.ChapterExistException;
import com.itany.netclass.exception.CourseExistException;
import com.itany.netclass.exception.ResourceExistException;
import com.itany.netclass.exception.StatusErrorException;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.query.ChapterQuery;
import com.itany.netclass.service.ChapterService;
import com.itany.netclass.vo.CourseResourceVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 章节 ServiceImpl
 *
 * @author Thou
 * @date 2022/9/8
 */
public class ChapterServiceImpl implements ChapterService {

    private final CourseMapper courseMapper = ObjectFactory.getObject("courseMapper");
    private final ChapterMapper chapterMapper = ObjectFactory.getObject("chapterMapper");
    private final ResourceMapper resourceMapper = ObjectFactory.getObject("resourceMapper");

    @Override
    public PageInfo<Chapter> listChapters(Integer pageNo, Integer pageSize, ChapterQuery userQuery) {
        PageHelper.startPage(pageNo, pageSize);
        List<Chapter> list = chapterMapper.listChaptersByCourseId(userQuery);
        return new PageInfo<>(list);
    }

    @Override
    public void add(CourseResourceVO crVO) throws ChapterExistException, ResourceExistException, CourseExistException {
        String chapterTitle = crVO.getChapterTitle();
        Chapter ch = chapterMapper.getChapterByTitleAndCourseId(chapterTitle, crVO.getCourseId());
        if (null != ch) {
            throw new ChapterExistException("章节标题已被使用");
        }
        String resourceTitle = crVO.getResourceTitle();
        Resource r = resourceMapper.getResourceByTitle(resourceTitle);
        if (null != r) {
            throw new ResourceExistException("资源标题已被使用");
        }

        Course course = courseMapper.getCourseById(crVO.getCourseId());
        if (null == course) {
            throw new CourseExistException("章节所属课程不存在");
        }
        Integer status = course.getStatus();

        Chapter chapter = new Chapter();
        chapter.setCourseId(crVO.getCourseId());
        chapter.setTitle(chapterTitle);
        chapter.setInfo(crVO.getChapterInfo());
        chapter.setCreateDate(new Date());
        chapter.setStatus(status);
        chapterMapper.saveChapterReturnPrimaryKey(chapter);

        Resource resource = new Resource();
        resource.setTitle(resourceTitle);
        resource.setPath(crVO.getResourcePath());
        resource.setCoverImageUrl(crVO.getResourceCoverImageUrl());
        resource.setOriginalName(crVO.getResourceOriginalName());
        resource.setFileSize(crVO.getResourceFileSize());
        resource.setFileType(crVO.getResourceFileType());
        resource.setTotalTime(crVO.getResourceTotalTime());
        resource.setClickCount(ResourceConsts.DEFAULT_CLICK_COUNT);
        resource.setCreateDate(new Date());
        resource.setCostType(crVO.getResourceCostType());
        resource.setCostNumber(crVO.getResourceCostNumber());
        resource.setUserId(crVO.getUserId());
        resource.setChapterId(chapter.getId());
        resource.setStatus(status);
        resourceMapper.saveResource(resource);
    }

    @Override
    public Chapter getChapterWithResourceById(Integer id) throws ChapterExistException {
        Chapter chapter = chapterMapper.getChapterWithResourceById(id);
        if (null == chapter) {
            throw new ChapterExistException("章节不存在");
        }
        return chapter;
    }

    @Override
    public void modify(CourseResourceVO crVO) throws ChapterExistException, ResourceExistException {
        String chapterTitle = crVO.getChapterTitle();
        Chapter ch = chapterMapper.getChapterByTitleAndCourseId(chapterTitle, crVO.getCourseId());
        if (null != ch) {
            boolean isSame = ch.getId().equals(crVO.getChapterId());
            if (!isSame) {
                throw new ChapterExistException("章节标题重复");
            }
        }
        String resourceTitle = crVO.getResourceTitle();
        Resource r = resourceMapper.getResourceByTitle(resourceTitle);
        if (null != r) {
            boolean isSame = r.getId().equals(crVO.getResourceId());
            if (!isSame) {
                throw new ResourceExistException("资源标题重复");
            }
        }

        Chapter chapter = new Chapter();
        chapter.setId(crVO.getChapterId());
        chapter.setTitle(chapterTitle);
        chapter.setInfo(crVO.getChapterInfo());

        Resource resource = new Resource();
        resource.setId(crVO.getResourceId());
        resource.setTitle(resourceTitle);
        resource.setPath(crVO.getResourcePath());
        resource.setCoverImageUrl(crVO.getResourceCoverImageUrl());
        resource.setOriginalName(crVO.getResourceOriginalName());
        resource.setFileSize(crVO.getResourceFileSize());
        resource.setFileType(crVO.getResourceFileType());
        resource.setTotalTime(crVO.getResourceTotalTime());
        resource.setCostType(crVO.getResourceCostType());
        resource.setCostNumber(crVO.getResourceCostNumber());

        chapterMapper.updateChapterById(chapter);
        resourceMapper.updateResourceById(resource);
    }

    @Override
    public void modifyStatus(Chapter chapter) throws StatusErrorException, ChapterExistException {
        Integer id = chapter.getId();
        Integer status = chapter.getStatus();

        Chapter ch = chapterMapper.getChapterById(id);
        if (null == ch) {
            throw new ChapterExistException("章节不存在");
        }

        // 修改状态为启用
        if (ChapterConsts.CHAPTER_STATUS_ENABLE.equals(status)) {
            Course course = courseMapper.getCourseById(ch.getCourseId());
            if (null != course) {
                Integer parentStatus = course.getStatus();
                if (CourseTypeConsts.COURSE_TYPE_STATUS_DISABLE.equals(parentStatus)) {
                    throw new StatusErrorException("当前课程已被禁用，无法更改章节状态");
                }
            }
        }

        chapterMapper.updateChapterById(chapter);
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        resourceMapper.updateStatusByChapterIds(ids, status);
    }
}
