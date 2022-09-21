package com.itany.netclass.dao;

import com.itany.netclass.entity.CourseType;
import com.itany.netclass.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ChapterMapperTest {

    @Test
    public void listChapterIdsByCourseId() {
        SqlSession session = MyBatisUtil.getSession();

        ChapterMapper mapper = session.getMapper(ChapterMapper.class);
        List<Integer> ids = mapper.listChapterIdsByCourseId(1);
        System.out.println("ids = " + ids);

        session.commit();
        MyBatisUtil.closeSession();
    }
}