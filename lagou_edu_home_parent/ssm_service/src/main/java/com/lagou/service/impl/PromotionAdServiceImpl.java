package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.vo.PromotionAdVo;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper promotionAdMapper;

    public PageInfo findAllAdByPage(PromotionAdVo adVo){
        //startPage()用来开启分页的功能，方法只接受两个参数：页码和每页条数,

        PageHelper.startPage(adVo.getCurrentPage(),adVo.getPageSize());

        //接下来执行数据库查询操作，PageHelper会自动将分页参数注入到查询语句中，实现分页查询
        List<PromotionAd> allAd = promotionAdMapper.findAllAdByPage();

        //使用PageInfo包装查询结果，可以获取分页相关信息
        PageInfo<PromotionAd> adPageInfo = new PageInfo<>(allAd);

        return adPageInfo;
    }

    @Override
    public void savePromotionAd(PromotionAd promotionAd) {
        promotionAdMapper.savePromotionAd(promotionAd);
    }
    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {
        promotionAdMapper.updatePromotionAd(promotionAd);
    }

    @Override
    public PromotionAd findPromotionAdById(int id) {
        return promotionAdMapper.findPromotionAdById(id);
    }

    @Override
    public void updatePromotionAdStatus(int id,int status) {
        PromotionAd  proAd=new PromotionAd();
        proAd.setId(id);
        proAd.setStatus(status);
        proAd.setUpdateTime(new Date());
        promotionAdMapper.updatePromotionAdStatus(proAd);

    }


}
