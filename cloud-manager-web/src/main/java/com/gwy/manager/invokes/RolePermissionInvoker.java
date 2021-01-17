package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.RolePermission;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Component;
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
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",configuration = FeignClientProperties.FeignClientConfiguration.class, contextId = "77")
@Qualifier("webRolePermissionInvoker")
public interface RolePermissionInvoker {

    @RequestMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("roleId") Integer roleId, @RequestParam("permissionId")Integer permissionId);

    @RequestMapping("insert")
    int insert(@RequestBody RolePermission record);

    @RequestMapping("selectAll")
    List<RolePermission> selectAll();

    @RequestMapping("selectPermissionIdsByRoleIds")
    List<Integer> selectPermissionIdsByRoleIds(@RequestBody List<Integer> roleIds);

    @RequestMapping("selectPermissionIdsByRoleId")
    List<Integer> selectPermissionIdsByRoleId(@RequestParam("roleId") Integer roleId);

    @RequestMapping("deleteByRoleId")
    int deleteByRoleId(@RequestParam("roleId") Integer roleId);

    @RequestMapping("insertBatch")
    int insertBatch(@RequestParam("roleId") Integer roleId, @RequestBody List<Integer> permissionIds);
}