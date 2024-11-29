package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.Teacher;
import com.lagou.domain.vo.CourseVo;

import java.util.List;

public interface CourseMapper {
    /*
   多条件查询课程信息
     */
    public List<Course> findCourseByCondition(CourseVo couseVo);
/*新增课程
 */
    public void saveCourse(Course course);
/*
新增教师信息
 */
    public void saveTeacher(Teacher teacher);

    /**
     * 根据课程ID查询课程（关联查询教师信息）
     * @param id
     * @return
     */
    public CourseVo findCourseById(Integer id);

    /**
     * 保存课程信息
     * @param course
     */
    public void updateCourse(Course course);

    /**
     * 保存教师信息
     * @param teacher
     */
    public void updateTeacher(Teacher teacher);

    /**
     * 修改课程状态
     * @param course
     */
    public void updateCoureseStatus(Course course);

}
