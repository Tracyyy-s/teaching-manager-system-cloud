package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.SysPermissionApi;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/12/10 15:40
 */
@FeignClient(serviceId = "springcloud-tqms-dao",contextId = "SysPermissionApiInvoker")
@RequestMapping("SysPermissionApiMapperController")
public interface SysPermissionApiInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@Param("permissionId") Integer permissionId, @Param("api") String api);

    @PostMapping("insert")
    int insert(SysPermissionApi record);

    @PostMapping("selectAll")
    List<SysPermissionApi> selectAll();

    /**
     * 获得指定api的权限id列表
     *
     * @param api api
     * @return 结果list
     */
    @PostMapping("selectPermissionIdsByApi")
    List<Integer> selectPermissionIdsByApi(String api);
}