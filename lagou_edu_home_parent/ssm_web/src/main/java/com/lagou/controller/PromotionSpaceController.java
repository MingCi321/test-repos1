package com.lagou.controller;

import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/promotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;

    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult  findAllPromotionSpace(){
        try {
            List<PromotionSpace> allPromotionSpace = promotionSpaceService.findAllPromotionSpace();
            return new ResponseResult(true, 200, "响应成功", allPromotionSpace);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseResult(false, 500, "响应异常", ex);
        }
    }

    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace proSpa){
        try
        {
        if(proSpa.getId()==null){
            promotionSpaceService.savePromotionSpace(proSpa);
        }else {
            promotionSpaceService.updatePromotionSpace(proSpa);
        }
        return new ResponseResult(true,200,"响应成功","OK");
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseResult(false, 500, "响应异常", ex);
        }
    }

    /**
     * 根据id查询 ， 广告位信息
     * */
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(@RequestParam int id){
        PromotionSpace promotionSpace =
                promotionSpaceService.findPromotionSpaceById(id);
        ResponseResult result = new ResponseResult(true,200,"响应成功",promotionSpace);
        return result;
    }


}
