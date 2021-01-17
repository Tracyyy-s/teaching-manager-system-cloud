package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.RolePermission;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.RolePermissionMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;
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
@RequestMapping("RolePermissionMapperController")
public class RolePermissionMapperController {
    @Resource
    RolePermissionMapper rolePermissionMapper;

    @RequestMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("roleId") Integer roleId, @RequestParam("permissionId") Integer permissionId) {
        return rolePermissionMapper.deleteByPrimaryKey(roleId, permissionId);
    }

    @RequestMapping("insert")
    int insert(@RequestBody RolePermission record) {
        return rolePermissionMapper.insert(record);
    }

    @RequestMapping("selectAll")
    List<RolePermission> selectAll() {
        return rolePermissionMapper.selectAll();
    }

    @RequestMapping("selectPermissionIdsByRoleIds")
    List<Integer> selectPermissionIdsByRoleIds(@RequestBody List<Integer> roleIds) {
        return rolePermissionMapper.selectPermissionIdsByRoleIds(roleIds);
    }

    @RequestMapping("selectPermissionIdsByRoleId")
    List<Integer> selectPermissionIdsByRoleId(@RequestParam("roleId") Integer roleId) {
        return rolePermissionMapper.selectPermissionIdsByRoleId(roleId);
    }

    @RequestMapping("deleteByRoleId")
    int deleteByRoleId(@RequestParam("roleId") Integer roleId) {
        return rolePermissionMapper.deleteByRoleId(roleId);
    }

    @RequestMapping("insertBatch")
    int insertBatch(@RequestParam("roleId") Integer roleId, @RequestBody List<Integer> permissionIds) {
        return rolePermissionMapper.insertBatch(roleId, permissionIds);
    }

}