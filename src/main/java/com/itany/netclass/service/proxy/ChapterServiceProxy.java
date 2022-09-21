package com.itany.netclass.service.proxy;

import com.github.pagehelper.PageInfo;
import com.itany.netclass.entity.Chapter;
import com.itany.netclass.exception.ChapterExistException;
import com.itany.netclass.exception.CourseExistException;
import com.itany.netclass.exception.DataAccessException;
import com.itany.netclass.exception.ResourceExistException;
import com.itany.netclass.exception.ServiceException;
import com.itany.netclass.exception.StatusErrorException;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.query.ChapterQuery;
import com.itany.netclass.service.ChapterService;
import com.itany.netclass.transaction.TransactionManager;
import com.itany.netclass.vo.CourseResourceVO;

/**
 * 章节 ServiceProxy
 *
 * @author Thou
 * @date 2022/9/8
 */
public class ChapterServiceProxy implements ChapterService {

    private final TransactionManager transactionManager = ObjectFactory.getObject("transactionManager");
    private final ChapterService chapterService = ObjectFactory.getObject("chapterServiceTarget");

    @Override
    public PageInfo<Chapter> listChapters(Integer pageNo, Integer pageSize, ChapterQuery userQuery) {
        PageInfo<Chapter> info = null;
        try {
            transactionManager.beginTransaction();
            info = chapterService.listChapters(pageNo, pageSize, userQuery);
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
    public void add(CourseResourceVO crVO) throws ChapterExistException, ResourceExistException, CourseExistException {
        try {
            transactionManager.beginTransaction();
            chapterService.add(crVO);
            transactionManager.commit();
        } catch (ChapterExistException | ResourceExistException | CourseExistException e) {
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

    @Override
    public Chapter getChapterWithResourceById(Integer id) throws ChapterExistException {
        Chapter chapter = null;
        try {
            transactionManager.beginTransaction();
            chapter = chapterService.getChapterWithResourceById(id);
            transactionManager.commit();
        } catch (ChapterExistException e) {
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
        return chapter;
    }

    @Override
    public void modify(CourseResourceVO crVO) throws ChapterExistException, ResourceExistException {
        try {
            transactionManager.beginTransaction();
            chapterService.modify(crVO);
            transactionManager.commit();
        } catch (ChapterExistException | ResourceExistException e) {
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

    @Override
    public void modifyStatus(Chapter chapter) throws StatusErrorException, ChapterExistException {
        try {
            transactionManager.beginTransaction();
            chapterService.modifyStatus(chapter);
            transactionManager.commit();
        } catch (ChapterExistException | StatusErrorException e) {
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
