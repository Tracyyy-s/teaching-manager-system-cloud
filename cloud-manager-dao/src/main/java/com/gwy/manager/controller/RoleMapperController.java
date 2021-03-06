package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.Role;
import com.gwy.manager.mapper.RoleMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@RestController
@RequestMapping("RoleMapperController")
public class RoleMapperController {

    @Resource
    RoleMapper roleMapper;

    @RequestMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("roleId") Integer roleId) {
        return roleMapper.deleteByPrimaryKey(roleId);
    }

    @RequestMapping("insert")
    int insert(@RequestBody Role record) {
        return roleMapper.insert(record);
    }

    @RequestMapping("selectByPrimaryKey")
    Role selectByPrimaryKey(@RequestParam("roleId") Integer roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @RequestMapping("selectAll")
    List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    @RequestMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody Role record) {
        return roleMapper.updateByPrimaryKey(record);
    }

    @RequestMapping("selectRoleNameById")
    String selectRoleNameById(@RequestParam("roleId") Integer roleId) {
        return roleMapper.selectRoleNameById(roleId);
    }

    @RequestMapping("selectRoleIdByName")
    Integer selectRoleIdByName(@RequestParam("roleName") String roleName) {
        return roleMapper.selectRoleIdByName(roleName);
    }

    @RequestMapping("selectRoleIdsByNames")
    List<Integer> selectRoleIdsByNames(@RequestBody List<String> roleNames) {
        return roleMapper.selectRoleIdsByNames(roleNames);
    }

    @RequestMapping("selectByUserId")
    List<Role> selectByUserId(@RequestParam("userId") String userId) {
        System.out.println("*******");
        System.out.println(userId);
        List<Role> roles = roleMapper.selectByUserId(userId);
        System.out.println(roles);
        return roles;
    }

}