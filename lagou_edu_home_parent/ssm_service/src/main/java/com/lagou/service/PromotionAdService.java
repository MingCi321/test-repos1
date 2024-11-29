package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.vo.PromotionAdVo;

public interface PromotionAdService {
    /**
     * 分页获取所有的广告列表
     **/
    public PageInfo findAllAdByPage(PromotionAdVo adVo);

    /**
     * 添加广告
     * @param promotionAd
     */
    void savePromotionAd(PromotionAd promotionAd);

    /**
     * 修改广告
     * @param promotionAd
     */
    void updatePromotionAd(PromotionAd promotionAd);

    /**
     * 根据id查询广告信息
     * */
    PromotionAd findPromotionAdById(int id);

    /**
     * 修改广告上下架状态
     * @param promotionAd
     */
    void updatePromotionAdStatus(int id,int status);
}
