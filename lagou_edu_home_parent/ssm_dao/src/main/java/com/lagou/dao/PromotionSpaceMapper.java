package com.lagou.dao;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceMapper {
    /*
    获取所有广告位
     */
    public List<PromotionSpace>  findAllPromotionSpace();

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
     * 根据id查询广告位信息
     * */
    PromotionSpace findPromotionSpaceById(int id);




}
