package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.User;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.UserMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@RestController
@RequestMapping("UserMapperController")
public class UserMapperController {
    @Resource
    UserMapper userMapper;

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(String userId){
        return userMapper.deleteByPrimaryKey(userId);
    }

    @PostMapping("insert")
    int insert(User record){
        return userMapper.insert(record);
    }

    @PostMapping("selectByPrimaryKey")
    User selectByPrimaryKey(String userId){
        return userMapper.selectByPrimaryKey(userId);
    }

    @PostMapping("selectAll")
    List<User> selectAll(){
        return userMapper.selectAll();
    }

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(User record){
        return userMapper.updateByPrimaryKey(record);
    }

    @PostMapping("selectUsersByDeptId")
    List<User> selectUsersByDeptId(String deptId){
        return userMapper.selectUsersByDeptId(deptId);
    }

    @PostMapping("selectUserNamesByIds")
    List<String> selectUserNamesByIds(@Param("userIds") List<String> userIds){
        return userMapper.selectUserNamesByIds(userIds);
    }

    @PostMapping("selectUserNamesForMapByIds")
    Map<String, Map<String, String>> selectUserNamesForMapByIds(@Param("userIds") List<String> userIds){
        return userMapper.selectUserNamesForMapByIds(userIds);
    }

    @PostMapping("insertUsersByBatch")
    int insertUsersByBatch(@Param("users") List<User> users){
        return userMapper.insertUsersByBatch(users);
    }

    @PostMapping("updatePassword")
    int updatePassword(@Param("userId") String userId,
                       @Param("password") String password){
        return userMapper.updatePassword(userId, password);
    }

    @PostMapping("getUsersMatchNameInDept")
    List<User> getUsersMatchNameInDept(@Param("deptId") String deptId,
                                       @Param("name") String name){
        return userMapper.getUsersMatchNameInDept(deptId, name);
    }

    @PostMapping("selectByUsername")
    User selectByUsername(String username){
        return userMapper.selectByUsername(username);
    }

    @PostMapping("selectUsersByRoleName")
    List<User> selectUsersByRoleName(String roleName){
        return userMapper.selectUsersByRoleName(roleName);
    }

    int updateAvailableDeptIds(@Param("userId") String userId,
                               @Param("deptIds") String deptIds){
        return userMapper.updateAvailableDeptIds(userId, deptIds);
    }
}