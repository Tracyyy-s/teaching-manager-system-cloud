package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@FeignClient(serviceId = "springcloud-tqms-dao",contextId = "PermissionInvoker")
@RequestMapping("PermissionMapperController")
public interface PermissionInvoker {

    @RequestMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(Integer permissionId);

    @RequestMapping("insert")
    int insert(Permission record);

    @RequestMapping("selectByPrimaryKey")
    Permission selectByPrimaryKey(Integer permissionId);

    @RequestMapping("selectAll")
    List<Permission> selectAll();

    @RequestMapping("updateByPrimaryKey")
    int updateByPrimaryKey(Permission record);

    @RequestMapping("selectByIds")
    List<Permission> selectByIds(@Param("permissionIds") List<Integer> permissionIds);

    @RequestMapping("selectByUserId")
    List<Permission> selectByUserId(String userId);

    @RequestMapping("selectByRoleIds")
    List<Permission> selectByRoleIds(@Param("roleIds") List<Integer> roleIds);

    @RequestMapping("selectByRoleId")
    List<Permission> selectByRoleId(@Param("roleId") Integer roleId);

    @RequestMapping("selectIdByName")
    Integer selectIdByName(String permissionName);

    @RequestMapping("selectAllForMap")
    Map<Integer, Permission> selectAllForMap();
}