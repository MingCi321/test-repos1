package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.vo.UserVo;

public interface UserService {
    /*
查询所有用户
*/
    public PageInfo findAllUserByPage(UserVo userVo);
}
