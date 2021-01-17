package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.Permission;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
@RequestMapping("PermissionMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",configuration = FeignClientProperties.FeignClientConfiguration.class, contextId = "55")
@Qualifier("webPermissionInvoker")
public interface PermissionInvoker {

    @RequestMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("permissionId") Integer permissionId);

    @RequestMapping("insert")
    int insert(@RequestBody Permission record);

    @RequestMapping("selectByPrimaryKey")
    Permission selectByPrimaryKey(@RequestParam("permissionId") Integer permissionId);

    @RequestMapping("selectAll")
    List<Permission> selectAll();

    @RequestMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody Permission record);

    @RequestMapping("selectByIds")
    List<Permission> selectByIds(@RequestBody List<Integer> permissionIds);

    @RequestMapping("selectByUserId")
    List<Permission> selectByUserId(@RequestParam("userId") String userId);

    @RequestMapping("selectByRoleIds")
    List<Permission> selectByRoleIds(@RequestBody List<Integer> roleIds);

    @RequestMapping("selectByRoleId")
    List<Permission> selectByRoleId(@RequestBody Integer roleId);

    @RequestMapping("selectIdByName")
    Integer selectIdByName(@RequestParam("permissionName") String permissionName);

    @RequestMapping("selectAllForMap")
    Map<Integer, Permission> selectAllForMap();
}