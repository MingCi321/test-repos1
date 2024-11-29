package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.vo.CourseVo;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController  //@ResponseBody +@Controller
@RequestMapping("course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping("/findCourseByCondition")
    public ResponseResult findByCondition(CourseVo courseVo) {
        List<Course> courseList = courseService.findCourseByCondition(courseVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", courseList);
        return responseResult;
    }

    /**
     * 上层课程标题图片
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/fileUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException();
            }
            //2.获取项目部署路径
            String realPath = request.getServletContext().getRealPath("/");
            String webappPath = realPath.substring(0, realPath.indexOf("ssm_web"));

            //3.获取原文件名
            String fileName = file.getOriginalFilename();

            //4.新文件名
            String newFileName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));

            //5.上传文件
            String uploadPath = webappPath + "upload\\";
            File filePath = new File(uploadPath, newFileName);

            //如果目录不存在就创建目录
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
                System.out.println("创建目录：" + filePath);
            }
            file.transferTo(filePath);

            //6.将文件名和文件路径返回
            Map<String, String> map = new HashMap<>();
            map.put("fileName", newFileName);
            map.put("filePath", "http://localhost:7878/upload/" + newFileName);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", map);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存&修改课程信息接口
     *
     * @param courseVO
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateCourse", method = RequestMethod.POST)
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVO) {
        try {
            if (courseVO.getId() == null) {
                courseService.saveCourseTeacher(courseVO);
                ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
                return result;
            }
            else{
                courseService.updateCourseTeacher(courseVO);
                ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据课程id，关联查询课程以及教师信息
     * @param id
     * @return
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(@RequestParam Integer id){
        try{
            CourseVo course = courseService.findCourseById(id);
            ResponseResult result=new ResponseResult(true,200,"响应成功",course);
            return result;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseResult(false,500,"服务异常",null);
    }

    /**
     * 修改课程的上下架状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateCoureseStatus")
    public ResponseResult updateCoureseStatus(@RequestParam Integer id,@RequestParam Integer status){
        try{
            Map<String,Integer> map=new HashMap<>();
            map.put("status",status);
            courseService.updateCoureseStatus(id,status);
            return new ResponseResult(true,200,"响应成功",map);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseResult(false,500,"服务异常",ex);
        }
    }






}
