package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.RolePermission;
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
@RequestMapping("RolePermissionMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",configuration = FeignClientProperties.FeignClientConfiguration.class, contextId = "103")
@Qualifier("menuRolePermissionInvoker")
public interface RolePermissionInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("roleId") Integer roleId, @RequestParam("permissionId")Integer permissionId);

    @PostMapping("insert")
    int insert(@RequestBody RolePermission record);

    @PostMapping("selectAll")
    List<RolePermission> selectAll();

    @PostMapping("selectPermissionIdsByRoleIds")
    List<Integer> selectPermissionIdsByRoleIds(@RequestBody List<Integer> roleIds);

    @PostMapping("selectPermissionIdsByRoleId")
    List<Integer> selectPermissionIdsByRoleId(@RequestParam("roleId") Integer roleId);

    @PostMapping("deleteByRoleId")
    int deleteByRoleId(@RequestParam("roleId") Integer roleId);

    @PostMapping("insertBatch")
    int insertBatch(@RequestParam("roleId") Integer roleId, @RequestBody List<Integer> permissionIds);
}