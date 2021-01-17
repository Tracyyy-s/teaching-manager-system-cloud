package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@FeignClient(serviceId = "springcloud-tqms-dao",contextId = "RoleInvoker")
@RequestMapping("RoleMapperController")
public interface RoleInvoker {

    @RequestMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(Integer roleId);

    @RequestMapping("insert")
    int insert(Role record);

    @RequestMapping("selectByPrimaryKey")
    Role selectByPrimaryKey(Integer roleId);

    @RequestMapping("selectAll")
    List<Role> selectAll();

    @RequestMapping("updateByPrimaryKey")
    int updateByPrimaryKey(Role record);

    @RequestMapping("selectRoleNameById")
    String selectRoleNameById(Integer roleId);

    @RequestMapping("selectRoleIdByName")
    Integer selectRoleIdByName(String roleName);

    @RequestMapping("selectRoleIdsByNames")
    List<Integer> selectRoleIdsByNames(@Param("roleNames") List<String> roleNames);

    @RequestMapping("selectByUserId")
    List<Role> selectByUserId(String userId);
}