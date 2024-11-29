package com.lagou.service;
import com.lagou.domain.Role;
import com.lagou.domain.vo.RoleMenuVo;

import java.util.List;


public interface RoleService {

    public List<Role> findAllRole(Role role);

    /**
     * 根据ID查询角色关联菜单
     * */
    List<String> findMenuByRoleId(Integer roleId);

    /**
     * 插入角色-权限关联记录
     * @param roleMenuVo
     */
    void RoleContextMenu(RoleMenuVo roleMenuVo);


}



