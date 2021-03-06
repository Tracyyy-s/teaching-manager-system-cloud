package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.SysPermissionApi;
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
 * @date 2021/1/17 16:57
 */
@Component
@RequestMapping("SysPermissionApiMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",configuration = FeignClientProperties.FeignClientConfiguration.class, contextId = "ttyu")
@Qualifier("routeSysPermissionApiInvoker")
public interface SysPermissionApiInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("permissionId") Integer permissionId, @RequestParam("api") String api);

    @PostMapping("insert")
    int insert(@RequestBody SysPermissionApi record);

    @PostMapping("selectAll")
    List<SysPermissionApi> selectAll();

    /**
     * 获得指定api的权限id列表
     *
     * @param api api
     * @return 结果list
     */
    @PostMapping("selectPermissionIdsByApi")
    List<Integer> selectPermissionIdsByApi(@RequestParam("api") String api);
}
