package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.User;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.UserMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("UserMapperController")
public class UserMapperController {
    @Resource
    UserMapper userMapper;

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(String userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @PostMapping("insert")
    int insert(@RequestBody User record) {
        return userMapper.insert(record);
    }

    @PostMapping("selectByPrimaryKey")
    User selectByPrimaryKey(@RequestParam("userId") String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @PostMapping("selectAll")
    List<User> selectAll() {
        System.out.println("dao...allUsers");
        return userMapper.selectAll();
    }

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @PostMapping("selectUsersByDeptId")
    List<User> selectUsersByDeptId(String deptId) {
        return userMapper.selectUsersByDeptId(deptId);
    }

    @PostMapping("selectUserNamesByIds")
    List<String> selectUserNamesByIds(@RequestBody List<String> userIds) {
        return userMapper.selectUserNamesByIds(userIds);
    }

    @PostMapping("selectUserNamesForMapByIds")
    Map<String, Map<String, String>> selectUserNamesForMapByIds(@RequestBody List<String> userIds) {
        return userMapper.selectUserNamesForMapByIds(userIds);
    }

    @PostMapping("insertUsersByBatch")
    int insertUsersByBatch(@RequestBody List<User> users) {
        return userMapper.insertUsersByBatch(users);
    }

    @PostMapping("updatePassword")
    int updatePassword(@RequestParam("userId") String userId,
                       @RequestParam("password") String password) {
        return userMapper.updatePassword(userId, password);
    }

    @PostMapping("getUsersMatchNameInDept")
    List<User> getUsersMatchNameInDept(@RequestParam("deptId") String deptId,
                                       @RequestParam("name") String name) {
        return userMapper.getUsersMatchNameInDept(deptId, name);
    }

    @PostMapping("selectByUsername")
    User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @PostMapping("selectUsersByRoleName")
    List<User> selectUsersByRoleName(String roleName) {
        return userMapper.selectUsersByRoleName(roleName);
    }

    int updateAvailableDeptIds(@RequestParam("userId") String userId,
                               @RequestParam("deptIds") String deptIds) {
        return userMapper.updateAvailableDeptIds(userId, deptIds);
    }
}