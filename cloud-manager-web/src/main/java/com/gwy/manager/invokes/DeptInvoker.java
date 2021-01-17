package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.Dept;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
@RequestMapping("DeptMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",configuration = FeignClientProperties.FeignClientConfiguration.class, contextId = "22")
@Qualifier("webDeptInvoker")
public interface DeptInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("deptId") String deptId);

    @PostMapping("insert")
    int insert(@RequestBody Dept record);

    @PostMapping("selectByPrimaryKey")
    Dept selectByPrimaryKey(@RequestParam("deptId") String deptId);

    @PostMapping("selectAll")
    List<Dept> selectAll();

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody Dept record);

    @PostMapping("getDeptByName")
    Dept getDeptByName(@RequestParam("name") String name);

    @PostMapping("getDeptByIds")
    Map<String, Dept> getDeptByIds(@RequestBody List<String> ids);
}