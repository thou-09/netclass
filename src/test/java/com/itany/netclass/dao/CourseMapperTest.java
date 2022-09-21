package com.itany.netclass.dao;

import com.itany.netclass.entity.Course;
import com.itany.netclass.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourseMapperTest {

    @Test
    public void getCourseWithChaptersAndResourceById() {
        SqlSession session = MyBatisUtil.getSession();

        CourseMapper mapper = session.getMapper(CourseMapper.class);
        Course c = mapper.getCourseWithChaptersAndResourceById(1);
        System.out.println("c = " + c);

        MyBatisUtil.closeSession();
    }
}