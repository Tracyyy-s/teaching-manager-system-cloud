package com.gwy.manager.service;

import com.gwy.manager.domain.dto.ResultVO;
import com.gwy.manager.domain.entity.User;

public interface UserService {
    /**
     * 获得所有用户
     * @return  结果集
     */
    ResultVO getAllUsers();

    /**
     * 通过学院id和userId获得用户
     * @param adminNo   管理员用户id
     * @param userId 用户id
     * @return  查询结果
     */
    ResultVO getUserById(String adminNo, String userId);
    /**
     * 通过用户名进行模糊匹配
     * @param adminNo   管理员角色
     * @param deptId 学院id
     * @param name  匹配名字
     * @return  结果集
     */
    ResultVO getUserMatchNameInDept(String adminNo, String deptId, String name);
    /**
     * 修改用户信息
     * @param user 预修改的用户
     * @return  影响行数
     */
    ResultVO updateUser(User user);
    /**
     * 根据用户id获得用户
     * @return  结果集
     */
    ResultVO getUserById(String userId);
}
