package com.itany.netclass.query;

import com.itany.netclass.entity.Chapter;

import java.util.Date;

/**
 * 课程章节查询对象，封装 request 请求中课程章节相关的查询参数
 *
 * @author Thou
 * @date 2022/9/3
 */
public class ChapterQuery extends Chapter {

    private static final long serialVersionUID = 7607523398044284944L;
    /**
     * 创建日期开始日期
     */
    private Date createDateStart;
    /**
     * 创建日期结束日期
     */
    private Date createDateEnd;
}
