package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);

    public Course findCourseById(Integer courseId);

    public void  saveCourseSection(CourseSection courseSection);

    public void updateSection(CourseSection section);

    public void updateSectionStatus(int id,int status);
}
