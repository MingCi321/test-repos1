package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.vo.CourseVo;

import java.util.List;

public interface CourseService {
    public List<Course>  findCourseByCondition(CourseVo courseVo);

    public void saveCourseTeacher(CourseVo courseVo);

    public CourseVo findCourseById(Integer integer);

    public void updateCourseTeacher(CourseVo courseVo);

    public void updateCoureseStatus(Integer id,Integer status);
}
