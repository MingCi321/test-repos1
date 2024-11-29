package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {
    /*
分页获取所有的广告列表
*/
    public List<PromotionAd> findAllAdByPage();

    /**
     * 创建添加广告记录
     * @param promotionAd
     */
    void savePromotionAd(PromotionAd promotionAd);

    /**
     * 更新广告对象
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
    void updatePromotionAdStatus(PromotionAd promotionAd);

}
