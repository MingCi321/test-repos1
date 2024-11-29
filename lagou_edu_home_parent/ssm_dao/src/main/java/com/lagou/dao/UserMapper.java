package com.lagou.dao;

import com.lagou.domain.User;
import com.lagou.domain.vo.UserVo;

import java.util.List;

public interface UserMapper {
    /*
查询所有用户
*/
    public List<User> findAllUserByPage(UserVo userVo);

}
