package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.Target;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@FeignClient(serviceId = "springcloud-tqms-dao",contextId = "TargetInvoker")
@RequestMapping("TargetMapperController")
public interface TargetInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(Integer targetId);

    @PostMapping("insert")
    int insert(Target record);

    @PostMapping("selectByPrimaryKey")
    Target selectByPrimaryKey(Integer targetId);

    @PostMapping("selectAll")
    List<Target> selectAll();

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(Target record);

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
    List<Target> getTargetsByIds(List<Integer> ids);
}