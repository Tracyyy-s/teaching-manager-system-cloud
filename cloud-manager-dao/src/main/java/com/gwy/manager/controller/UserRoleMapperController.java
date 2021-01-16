package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.UserRole;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.UserRoleMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    int deleteByPrimaryKey(@Param("userId") String userId, @Param("roleId") Integer roleId){
        return  userRoleMapper.deleteByPrimaryKey(userId, roleId);
    }

    @PostMapping("insert")
    int insert(UserRole record){
        return  userRoleMapper.insert(record);
    }


    @PostMapping("insertByBatch")
    int insertByBatch(@Param("userRoles") List<UserRole> userRoles){
        return  userRoleMapper.insertByBatch(userRoles);
    }
    @PostMapping("selectAll")
    List<UserRole> selectAll(){
        return  userRoleMapper.selectAll();
    }
    @PostMapping("deleteRoleOfUser")
    int deleteRoleOfUser(@Param("userId") String userId){
        return  userRoleMapper.deleteRoleOfUser(userId);
    }
    @PostMapping("addRolesForUser")
    int addRolesForUser(@Param("userId") String userId, @Param("roleIds") List<Integer> roleIds){
        return  userRoleMapper.addRolesForUser(userId, roleIds);
    }
}