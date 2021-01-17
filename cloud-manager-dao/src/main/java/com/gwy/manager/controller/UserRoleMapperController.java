package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.UserRole;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.UserRoleMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("UserRoleMapperController")
public class UserRoleMapperController {
    @Resource
    UserRoleMapper userRoleMapper;

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("userId") String userId, @RequestParam("roleId") Integer roleId) {
        return userRoleMapper.deleteByPrimaryKey(userId, roleId);
    }

    @PostMapping("insert")
    int insert(@RequestBody UserRole record) {
        return userRoleMapper.insert(record);
    }


    @PostMapping("insertByBatch")
    int insertByBatch(@RequestBody List<UserRole> userRoles) {
        return userRoleMapper.insertByBatch(userRoles);
    }

    @PostMapping("selectAll")
    List<UserRole> selectAll() {
        return userRoleMapper.selectAll();
    }

    @PostMapping("deleteRoleOfUser")
    int deleteRoleOfUser(@RequestParam("userId") String userId) {
        return userRoleMapper.deleteRoleOfUser(userId);
    }

    @PostMapping("addRolesForUser")
    int addRolesForUser(@RequestParam("userId") String userId, @RequestBody List<Integer> roleIds) {
        return userRoleMapper.addRolesForUser(userId, roleIds);
    }
}