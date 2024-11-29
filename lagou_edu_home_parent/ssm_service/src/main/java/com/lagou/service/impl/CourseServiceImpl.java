package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.Teacher;
import com.lagou.domain.vo.CourseVo;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils; //注意不要导错城spring.beans的
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByCondition(CourseVo courseVo) {
        return courseMapper.findCourseByCondition(courseVo);
    }

    @Override
    public void saveCourseTeacher(CourseVo courseVo) {
        try{
            //封装课程信息
            Course course=new Course();
            BeanUtils.copyProperties(course,courseVo);

            //补全信息
            Date date=new Date();
            course.setCreateTime(date);
            course.setUpdateTime(date);

            //保存课程
            courseMapper.saveCourse(course);

            //获取新插入数据的id
            int id=course.getId();

            //封装讲师信息
            Teacher teacher=new Teacher();
            BeanUtils.copyProperties(teacher,courseVo);

            //补全信息
            teacher.setCourseId(id);
            teacher.setCreateTime(date);
            teacher.setUpdateTime(date);

            //保存讲师信息
            courseMapper.saveTeacher(teacher);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public CourseVo findCourseById(Integer id) {
        return courseMapper.findCourseById(id);
    }

    @Override
    public void updateCourseTeacher(CourseVo courseVo) {
        try{
            //封装课程信息
            Course course=new Course();
            BeanUtils.copyProperties(course,courseVo);

            //补全信息
            Date date=new Date();
            course.setUpdateTime(date);

            //保存课程
            courseMapper.updateCourse(course);

            //获取新插入数据的id
            int id=course.getId();

            //封装讲师信息
            Teacher teacher=new Teacher();
            BeanUtils.copyProperties(teacher,courseVo);

            //补全信息
            teacher.setCourseId(id);
            teacher.setUpdateTime(date);

            //保存讲师信息
            courseMapper.updateTeacher(teacher);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void updateCoureseStatus(Integer id,Integer status) {
        try{
            Course course=new Course();
            Date date=new Date();
            course.setId(id);
            course.setStatus(status);
            course.setUpdateTime(date);
            courseMapper.updateCoureseStatus(course);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
