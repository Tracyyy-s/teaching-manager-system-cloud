package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@RestController
@RequestMapping("UserRoleMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER")
public interface UserRoleInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@Param("userId") String userId, @Param("roleId") Integer roleId);

    @PostMapping("insert")
    int insert(UserRole record);

    @PostMapping("insertByBatch")
    int insertByBatch(@Param("userRoles") List<UserRole> userRoles);

    @PostMapping("selectAll")
    List<UserRole> selectAll();

    @PostMapping("deleteRoleOfUser")
    int deleteRoleOfUser(@Param("userId") String userId);

    @PostMapping("addRolesForUser")
    int addRolesForUser(@Param("userId") String userId, @Param("roleIds") List<Integer> roleIds);
}