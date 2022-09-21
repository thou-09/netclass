package com.itany.netclass.dao;

import com.itany.netclass.entity.CourseType;
import com.itany.netclass.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * CourseTypeMapper Tester
 *
 * @author Thou
 * @date 2022/8/31
 */
public class CourseTypeMapperTest {

    @Test
    public void testSaveCourseType() {
        SqlSession session = MyBatisUtil.getSession();

        CourseTypeMapper mapper = session.getMapper(CourseTypeMapper.class);
        CourseType courseType = new CourseType();
        courseType.setTypeName("");
        mapper.saveCourseType(courseType);

        session.commit();
        MyBatisUtil.closeSession();
    }

    @Test
    public void listSonsIdByParentId() {
        SqlSession session = MyBatisUtil.getSession();

        CourseTypeMapper mapper = session.getMapper(CourseTypeMapper.class);
        List<Integer> ids = new ArrayList<>();

        List<Integer> list = new ArrayList<>();
        list.add(1);

        do {
            ids.addAll(list);
            list = mapper.listSonsIdByParentIds(list);
        } while (list.size() != 0);

        System.out.println(ids);

        mapper.updateStatusByIds(-1 ,ids);

        session.commit();
        MyBatisUtil.closeSession();
    }

    @Test
    public void listAll() {
        SqlSession session = MyBatisUtil.getSession();

        CourseTypeMapper mapper = session.getMapper(CourseTypeMapper.class);
        List<CourseType> list = mapper.listAll();
        System.out.println(list);

        session.commit();
        MyBatisUtil.closeSession();
    }
}