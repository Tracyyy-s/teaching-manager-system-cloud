package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.Role;
import com.gwy.manager.invokes.fallbackFactory.FallbackPermissionInvoker;
import com.gwy.manager.invokes.fallbackFactory.FallbackRoleInvoker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER", contextId = "102",fallbackFactory = FallbackRoleInvoker.class)
@RequestMapping("RoleMapperController")
@Qualifier("menuRoleInvoker")
public interface RoleInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("roleId") Integer roleId);

    @PostMapping("insert")
    int insert(@RequestBody Role record);

    @PostMapping("selectByPrimaryKey")
    Role selectByPrimaryKey(@RequestParam("roleId") Integer roleId);

    @PostMapping("selectAll")
    List<Role> selectAll();

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody Role record);

    @PostMapping("selectRoleNameById")
    String selectRoleNameById(@RequestParam("roleId") Integer roleId);

    @PostMapping("selectRoleIdByName")
    Integer selectRoleIdByName(@RequestParam("roleName") String roleName);

    @PostMapping("selectRoleIdsByNames")
    List<Integer> selectRoleIdsByNames(@RequestBody List<String> roleNames);

    /*------------获取用户角色--------------*/
    @PostMapping("selectByUserId")
    List<Role> selectByUserId(@RequestParam("userId") String userId);
}