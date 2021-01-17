package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.SysPermissionApi;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.SysPermissionApiMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Tracy
 * @date 2020/12/10 15:40
 */
@RestController
@RequestMapping("SysPermissionApiMapperController")
public class SysPermissionApiMapperController {
    @Resource
    SysPermissionApiMapper sysPermissionApiMapper;

    @RequestMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("permissionId") Integer permissionId, @RequestParam("api") String api){
        return sysPermissionApiMapper.deleteByPrimaryKey(permissionId,api);
    }
    @RequestMapping("insert")
    int insert(@RequestBody SysPermissionApi record){
        return sysPermissionApiMapper.insert(record);
    }
    @RequestMapping("selectAll")
    List<SysPermissionApi> selectAll(){
        return sysPermissionApiMapper.selectAll();
    }

    /**
     * 获得指定api的权限id列表
     * @param api   api
     * @return  结果list
     */
    @RequestMapping("selectPermissionIdsByApi")
    List<Integer> selectPermissionIdsByApi(@RequestParam("api") String api){
        return sysPermissionApiMapper.selectPermissionIdsByApi(api);
    }
}