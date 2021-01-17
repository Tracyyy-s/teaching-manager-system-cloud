package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.RolePermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@FeignClient(serviceId = "springcloud-tqms-dao",contextId = "RolePermissionInvoker")
@RequestMapping("RolePermissionMapperController")
public interface RolePermissionInvoker {

    @RequestMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(Integer roleId, Integer permissionId);

    @RequestMapping("insert")
    int insert(RolePermission record);

    @RequestMapping("selectAll")
    List<RolePermission> selectAll();

    @RequestMapping("selectPermissionIdsByRoleIds")
    List<Integer> selectPermissionIdsByRoleIds(@Param("roleIds") List<Integer> roleIds);

    @RequestMapping("selectPermissionIdsByRoleId")
    List<Integer> selectPermissionIdsByRoleId(Integer roleId);

    @RequestMapping("deleteByRoleId")
    int deleteByRoleId(Integer roleId);

    @RequestMapping("insertBatch")
    int insertBatch(Integer roleId, List<Integer> permissionIds);
}