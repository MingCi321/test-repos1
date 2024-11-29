package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {
    /**
     * 查询课程下的章节和课时信息
     * @param courseId
     * @return
     */
    public List<CourseSection>  findSectionAndLessonByCourseId(int courseId);

    /**
     * 回显章节的课程信息；根据课程id查询课程
     * @param courseId
     * @return
     */
    public Course findCourseById(int courseId);

    /**
     * 保存章节信息
     */
    public void saveCourseSection(CourseSection courseSection);

    /**
     * 修改章节
     * */
    public void updateSection(CourseSection section);

    /**
     * 修改章节状态
     * */
    public void updateSectionStatus(CourseSection section);

}
