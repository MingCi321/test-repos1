package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;
import com.lagou.domain.vo.RoleMenuVo;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    @Override
    public List<String> findMenuByRoleId(Integer roleId) {
        List<String> list = roleMapper.findMenuByRoleId(roleId);
        return list;

    }

    @Override
    public void RoleContextMenu(RoleMenuVo roleMenuVo) {
        //清空角色下现有的所有权限，新权限覆盖已有权限
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());
        for(Integer mid : roleMenuVo.getMenuIdList()){
            Date date=new Date();
            Role_menu_relation rmr=new Role_menu_relation();
            rmr.setRoleId(roleMenuVo.getRoleId());
            rmr.setMenuId(mid);
            rmr.setUpdatedby("system");
            rmr.setCreatedBy("system");
            rmr.setCreatedTime(date);
            rmr.setUpdatedTime(date);
            //插入角色-权限关联记录
            roleMapper.RoleContextMenu(rmr);
        }
    }


}
