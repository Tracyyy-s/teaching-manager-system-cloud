package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.Permission;
import com.gwy.manager.mapper.PermissionMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@RestController
@RequestMapping("PermissionMapperController")
public class PermissionMapperController {

    @Resource
    private PermissionMapper permissionMapper;

    @RequestMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(Integer permissionId) {
        return permissionMapper.deleteByPrimaryKey(permissionId);
    }


    @RequestMapping("insert")
    int insert(Permission record) {
        return permissionMapper.insert(record);
    }


    @RequestMapping("selectByPrimaryKey")
    Permission selectByPrimaryKey(Integer permissionId) {
        return permissionMapper.selectByPrimaryKey(permissionId);
    }


    @RequestMapping("selectAll")
    List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }


    @RequestMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody Permission record) {
        return permissionMapper.updateByPrimaryKey(record);
    }


    @RequestMapping("selectByIds")
    List<Permission> selectByIds(@RequestBody List<Integer> permissionIds) {
        return permissionMapper.selectByIds(permissionIds);
    }


    @RequestMapping("selectByUserId")
    List<Permission> selectByUserId(String userId) {
        return permissionMapper.selectByUserId(userId);
    }

    /*---根据roleId列表去筛选权限---*/
    @RequestMapping("selectByRoleIds")
    List<Permission> selectByRoleIds(@RequestBody List<Integer> roleIds) {
        System.out.println(roleIds);
        return permissionMapper.selectByRoleIds(roleIds);
    }

    @RequestMapping("selectByRoleId")
    List<Permission> selectByRoleId(@RequestParam("roleId") Integer roleId) {
        return permissionMapper.selectByRoleId(roleId);
    }

    @RequestMapping("selectIdByName")
    Integer selectIdByName(String permissionName) {
        return permissionMapper.selectIdByName(permissionName);
    }

    @RequestMapping("selectAllForMap")
    Map<Integer, Permission> selectAllForMap() {
        return permissionMapper.selectAllForMap();
    }

}