package com.itany.netclass.service.impl;

import com.itany.netclass.entity.Course;
import com.itany.netclass.exception.CourseExistException;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourseServiceImplTest {

    @Test
    public void get4Front() {
        try {
            Course course = new CourseServiceImpl().get4Front(1);
            System.out.println("course = " + course);
        } catch (CourseExistException e) {
            e.printStackTrace();
        }
    }
}