package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.Target;
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

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
@RequestMapping("TargetMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",configuration = FeignClientProperties.FeignClientConfiguration.class, contextId = "56")
@Qualifier("webTargetInvoker")
public interface TargetInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("targetId") Integer targetId);

    @PostMapping("insert")
    int insert(@RequestBody Target record);

    @PostMapping("selectByPrimaryKey")
    Target selectByPrimaryKey(@RequestParam("targetId") Integer targetId);

    @PostMapping("selectAll")
    List<Target> selectAll();

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody Target record);

    /**
     * 获得学生评价的指标
     *
     * @return 结果集
     */
    @PostMapping("getStudentTargets")
    List<Target> getStudentTargets();

    /**
     * 获得教师评价的指标
     *
     * @return 结果集
     */
    @PostMapping("getTeacherTargets")
    List<Target> getTeacherTargets();

    /**
     * 获得指标列表的指标
     *
     * @return 结果集
     */
    @PostMapping("getTargetsByIds")
    List<Target> getTargetsByIds(@RequestBody List<Integer> ids);
}