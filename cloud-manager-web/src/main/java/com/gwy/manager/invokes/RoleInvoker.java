package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.Role;
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
@RequestMapping("RoleMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",configuration = FeignClientProperties.FeignClientConfiguration.class, contextId = "66")
@Qualifier("webRoleInvoker")
public interface RoleInvoker {

    @RequestMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("roleId") Integer roleId);

    @RequestMapping("insert")
    int insert(@RequestBody Role record);

    @RequestMapping("selectByPrimaryKey")
    Role selectByPrimaryKey(@RequestParam("roleId") Integer roleId);

    @RequestMapping("selectAll")
    List<Role> selectAll();

    @RequestMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody Role record);

    @RequestMapping("selectRoleNameById")
    String selectRoleNameById(@RequestParam("roleId") Integer roleId);

    @RequestMapping("selectRoleIdByName")
    Integer selectRoleIdByName(@RequestParam("roleName") String roleName);

    @RequestMapping("selectRoleIdsByNames")
    List<Integer> selectRoleIdsByNames(@RequestBody List<String> roleNames);

    @RequestMapping("selectByUserId")
    List<Role> selectByUserId(@RequestParam("userId") String userId);
}