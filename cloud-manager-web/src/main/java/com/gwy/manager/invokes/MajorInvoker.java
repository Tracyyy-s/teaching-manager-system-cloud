package com.gwy.manager.invokes;


import com.gwy.manager.domain.entity.Major;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
@RequestMapping("MajorMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",configuration = FeignClientProperties.FeignClientConfiguration.class, contextId = "44")
@Qualifier("webMajorInvoker")
public interface MajorInvoker {

    @RequestMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("majorId") String majorId);

    @RequestMapping("insert")
    int insert(@RequestBody Major record);

    @RequestMapping("selectByPrimaryKey")
    Major selectByPrimaryKey(@RequestParam("majorId") String majorId);

    @RequestMapping("selectAll")
    List<Major> selectAll();

    @RequestMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody Major record);

    /**
     * 获得某学院的所有专业
     *
     * @param deptId 学院id
     * @return 返回结果
     */
    @RequestMapping("selectByDept")
    List<Major> selectByDept(@RequestParam("deptId") String deptId);
}