package com.gwy.manager.invokes;


import com.gwy.manager.domain.entity.Major;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@FeignClient(serviceId = "springcloud-tqms-dao",contextId = "MajorInvoker")
@RequestMapping("MajorMapperController")
public interface MajorInvoker {

    @RequestMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(String majorId);

    @RequestMapping("insert")
    int insert(Major record);

    @RequestMapping("selectByPrimaryKey")
    Major selectByPrimaryKey(String majorId);

    @RequestMapping("selectAll")
    List<Major> selectAll();

    @RequestMapping("updateByPrimaryKey")
    int updateByPrimaryKey(Major record);

    /**
     * 获得某学院的所有专业
     *
     * @param deptId 学院id
     * @return 返回结果
     */
    @RequestMapping("selectByDept")
    List<Major> selectByDept(String deptId);
}