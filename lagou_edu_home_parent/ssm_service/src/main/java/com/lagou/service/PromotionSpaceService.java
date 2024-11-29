package com.lagou.service;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {

    /**
获取所有的广告位
*/
    public List<PromotionSpace> findAllPromotionSpace();

    /**
     * 新增广告位记录
     * @param promotionSpace
     */
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 修改广告位记录
     * @param promotionSpace
     */
    public void updatePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 根据id查询广告位
     * @param id
     * @return
     */
    PromotionSpace findPromotionSpaceById(int id);



}
