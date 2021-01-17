package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
@RequestMapping("UserRoleMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",configuration = FeignClientProperties.FeignClientConfiguration.class, contextId = "70")
@Qualifier("webUserRoleInvoker")
public interface UserRoleInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("userId") String userId, @RequestParam("roleId") Integer roleId);

    @PostMapping("insert")
    int insert(UserRole record);

    @PostMapping("insertByBatch")
    int insertByBatch(@RequestParam("userRoles") List<UserRole> userRoles);

    @PostMapping("selectAll")
    List<UserRole> selectAll();

    @PostMapping("deleteRoleOfUser")
    int deleteRoleOfUser(@RequestParam("userId") String userId);

    @PostMapping("addRolesForUser")
    int addRolesForUser(@RequestParam("userId") String userId, @RequestParam("roleIds") List<Integer> roleIds);
}