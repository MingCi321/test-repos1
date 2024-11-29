package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {
    @Autowired
    private CourseContentMapper courseContentMapper;
    
    
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId) {
        try{
            List<CourseSection> courseSectionAndLessonList = courseContentMapper.findSectionAndLessonByCourseId(courseId);
          return courseSectionAndLessonList;
        }catch (Exception ex){
            ex.printStackTrace();
            return  null;
        }
    }

    @Override
    public Course findCourseById(Integer courseId) {
        return courseContentMapper.findCourseById(courseId);
    }

    @Override
    public void saveCourseSection(CourseSection courseSection) {
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);
        courseContentMapper.saveCourseSection(courseSection);
    }

    @Override
    public void updateSection(CourseSection section) {
//补全信息
        Date date = new Date();
        section.setUpdateTime(date);
        courseContentMapper.updateSection(section);
    }

    @Override
    public void updateSectionStatus(int id,int status) {
//封装数据
        CourseSection section = new CourseSection();
        section.setId(id);
        section.setStatus(status);
        section.setUpdateTime(new Date());
        courseContentMapper.updateSectionStatus(section);
    }

}
