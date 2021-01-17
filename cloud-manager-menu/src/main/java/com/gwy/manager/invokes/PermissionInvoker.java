package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.Permission;
import com.gwy.manager.invokes.fallbackFactory.FallbackPermissionInvoker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Component
@RequestMapping("PermissionMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",contextId = "101",fallbackFactory = FallbackPermissionInvoker.class)
@Qualifier("menuPermissionInvoker")
public interface PermissionInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("permissionId") Integer permissionId);

    @PostMapping("insert")
    int insert(@RequestBody Permission record);

    @PostMapping("selectByPrimaryKey")
    Permission selectByPrimaryKey(@RequestParam("permissionId") Integer permissionId);

    @PostMapping("selectAll")
    List<Permission> selectAll();

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody Permission record);

    @PostMapping("selectByIds")
    List<Permission> selectByIds(@RequestBody List<Integer> permissionIds);

    @PostMapping("selectByUserId")
    List<Permission> selectByUserId(@RequestParam("userId") String userId);

    /*---根据roleId列表去筛选权限---*/
    @PostMapping("selectByRoleIds")
    List<Permission> selectByRoleIds(@RequestBody List<Integer> roleIds);

    @PostMapping("selectByRoleId")
    List<Permission> selectByRoleId(@RequestBody Integer roleId);

    @PostMapping("selectIdByName")
    Integer selectIdByName(@RequestParam("permissionName") String permissionName);

    @PostMapping("selectAllForMap")
    Map<Integer, Permission> selectAllForMap();
}