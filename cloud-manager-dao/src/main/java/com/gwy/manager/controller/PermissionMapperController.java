package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.Permission;
import com.gwy.manager.mapper.PermissionMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@RestController
@RequestMapping("PermissionMapperController")
public class PermissionMapperController {

    @Resource
    private PermissionMapper permissionMapper;

    @RequestMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("permissionId") Integer permissionId) {
        return permissionMapper.deleteByPrimaryKey(permissionId);
    }


    @RequestMapping("insert")
    int insert(@RequestBody Permission record) {
        return permissionMapper.insert(record);
    }


    @RequestMapping("selectByPrimaryKey")
    Permission selectByPrimaryKey(@RequestParam("permissionId") Integer permissionId) {
        return permissionMapper.selectByPrimaryKey(permissionId);
    }


    @RequestMapping("selectAll")
    List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }


    @RequestMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody Permission record) {
        return permissionMapper.updateByPrimaryKey(record);
    }


    @RequestMapping("selectByIds")
    List<Permission> selectByIds(@RequestBody List<Integer> permissionIds) {
        return permissionMapper.selectByIds(permissionIds);
    }


    @RequestMapping("selectByUserId")
    List<Permission> selectByUserId(@RequestParam("userId") String userId) {
        return permissionMapper.selectByUserId(userId);
    }

    /*---根据roleId列表去筛选权限---*/
    /**
     *
     * @return java.util.List<com.gwy.manager.domain.entity.Permission>
     *     查询熔断
     * @date 2021/1/17 21:40
     */
    @RequestMapping("selectByRoleIds")
    @HystrixCommand(fallbackMethod = "selectByRoleIdHystrix") //熔断机制，调用失败时调用hystrixGet
    List<Permission> selectByRoleIds(@RequestBody List<Integer> roleIds) {
//        System.out.println(roleIds);
//        System.out.println(permissionMapper.selectByRoleIds(roleIds));
//        throw new RuntimeException("获取权限操作失败，请稍后再试");
        return permissionMapper.selectByRoleIds(roleIds);
    }
    public List<Permission> selectByRoleIdHystrix(List<Integer> roleIds){
        List<Permission> l = new ArrayList<Permission>();
        Permission per = new Permission();
        per.setPermissionId(1);
        per.setPermissionName("获取权限操作失败，请稍后再试");
        l.add(per);
        return l;
    }


    @RequestMapping("selectByRoleId")
    List<Permission> selectByRoleId(@RequestParam("roleId") Integer roleId) {
//        throw new NullPointerException();
        return permissionMapper.selectByRoleId(roleId);
    }



    @RequestMapping("selectIdByName")
    Integer selectIdByName(@RequestParam("permissionName") String permissionName) {
        return permissionMapper.selectIdByName(permissionName);
    }

    @RequestMapping("selectAllForMap")
    Map<Integer, Permission> selectAllForMap() {
        return permissionMapper.selectAllForMap();
    }

}