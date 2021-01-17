package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.Dept;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@RestController
@RequestMapping("DeptMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER")
public interface DeptInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(String deptId);

    @PostMapping("insert")
    int insert(Dept record);

    @PostMapping("selectByPrimaryKey")
    Dept selectByPrimaryKey(String deptId);

    @PostMapping("selectAll")
    List<Dept> selectAll();

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(Dept record);

    @PostMapping("getDeptByName")
    Dept getDeptByName(String name);

    @PostMapping("getDeptByIds")
    Map<String, Dept> getDeptByIds(@Param("ids") List<String> ids);
}