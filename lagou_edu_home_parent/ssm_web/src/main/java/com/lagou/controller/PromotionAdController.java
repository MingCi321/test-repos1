package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.vo.PromotionAdVo;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RequestMapping("/PromotionAd")
@RestController
public class PromotionAdController {

    @Autowired
    private PromotionAdService adService;

    /*
    分页查询所有广告信息
    */
    @RequestMapping("/findAllPromotionAd")
    public ResponseResult findAllAdByPage(PromotionAdVo adVo) {
        PageInfo allAdByPage = adService.findAllAdByPage(adVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功",
                allAdByPage);
        return responseResult;
    }

    /**
     * 上层课程标题图片
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/promotionAdUpload")
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
     * 新增或更新广告位置
     **/
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {
        try {
            if (promotionAd.getId() == null) {
                Date date = new Date();
                promotionAd.setCreateTime(date);
                promotionAd.setUpdateTime(date);
                adService.savePromotionAd(promotionAd);
                ResponseResult result = new ResponseResult(true, 200, "响应成功",
                        null);
                return result;
            } else {
                Date date = new Date();
                promotionAd.setUpdateTime(date);
                adService.updatePromotionAd(promotionAd);
                ResponseResult result = new ResponseResult(true, 200, "响应成功",
                        null);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据id查询广告信息
     * */
    @RequestMapping("findPromotionAdById")
    public ResponseResult findPromotionAdById(@RequestParam int id){
        try{
            PromotionAd promotionAd= adService.findPromotionAdById(id);
            return new ResponseResult(true,200,"响应成功",promotionAd);
        }catch (Exception ex){
            ex.printStackTrace();
            return  new ResponseResult(false,500,"响应异常",ex);
        }



    }


    /**
     * 根据广告id,修改广告上下架状态
     * **/
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updateCourseStatus(@RequestParam int id, @RequestParam int status)
    {
        try {
//执行修改操作
            if (status == 1) {
                adService.updatePromotionAdStatus(id, status);
            } else {
                adService.updatePromotionAdStatus(id, 0);
            }
//保存修改后的状态,并返回
            Map<String, Integer> map = new HashMap<>();
            map.put("status", status);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
