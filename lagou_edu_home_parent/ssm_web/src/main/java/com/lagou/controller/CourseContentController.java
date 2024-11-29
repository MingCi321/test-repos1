package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {
    @Autowired
    private CourseContentService courseContentService;


    @RequestMapping("/findCouserSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(@RequestParam Integer courseId) {
        try {
            List<CourseSection> sectionAndLessonByCourseId = courseContentService.findSectionAndLessonByCourseId(courseId);
            return new ResponseResult(true, 200, "响应成功", sectionAndLessonByCourseId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseResult(false, 500, "响应异常", ex);
        }

    }

    /**
     * 回显课程名称
     *
     * @param courseId
     * @return
     */
    @RequestMapping("/backAppearCourse")
    public ResponseResult findCourseById(@RequestParam Integer courseId) {
        try {
            Course course = courseContentService.findCourseById(courseId);
            ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", course);
            return responseResult;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseResult(false, 500, "响应异常", ex);
        }
    }


    @RequestMapping("/saveCourseSection")
    public ResponseResult saveCourseSection(@RequestBody CourseSection courseSection) {
        try {
            courseContentService.saveCourseSection(courseSection);
            return new ResponseResult(true, 200, "响应成功", "章节保存成功");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseResult(false, 500, "响应异常", ex);
        }
    }

    @RequestMapping("/updateSection")
    public ResponseResult updateSection(@RequestBody CourseSection courseSection) {
        try {
            courseContentService.updateSection(courseSection);
            return new ResponseResult(true, 200, "响应成功", "章节修改成功");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseResult(false, 500, "响应异常", ex);
        }
    }

    /**
     * 修改章节状态
     * 状态，0:隐藏；1：待更新；2：已发布
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(@RequestParam int id, @RequestParam
            int status) {
        try {
            courseContentService.updateSectionStatus(id, status);
//封装最新的状态信息
            Map<String, Integer> map = new HashMap<>();
            map.put("status", status);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
