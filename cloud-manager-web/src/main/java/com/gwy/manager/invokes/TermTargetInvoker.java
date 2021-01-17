package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.TermTarget;
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

/**
 * @author Tracy
 * @date 2021/1/17 9:40
 */
@Component
@RequestMapping("TermTargetInvoker")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",configuration = FeignClientProperties.FeignClientConfiguration.class, contextId = "17")
@Qualifier("webTermTargetInvoker")
public interface TermTargetInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("termId") String termId, @RequestParam("targetId") Integer targetId);

    @PostMapping("insert")
    int insert(@RequestBody TermTarget record);

    @PostMapping("selectByPrimaryKey")
    TermTarget selectByPrimaryKey(@RequestParam("termId") String termId, @RequestParam("targetId") Integer targetId);

    @PostMapping("selectAll")
    List<TermTarget> selectAll();

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody TermTarget record);

    /**
     * 添加本学期的所有评价指标
     *
     * @param termTargets 学期评价指标
     * @return 结果集
     */
    @PostMapping("insertTermTargets")
    int insertTermTargets(@RequestBody List<TermTarget> termTargets);

    /**
     * 获得学期所有学生评价指标
     *
     * @param termId 学期
     * @return 结果集
     */
    @PostMapping("getStudentTermTargets")
    List<Integer> getStudentTermTargets(@RequestParam("termId") String termId);

    /**
     * 获得学期所有教师评价指标
     *
     * @param termId 学期
     * @return 结果集
     */
    @PostMapping("getTeacherTermTargets")
    List<Integer> getTeacherTermTargets(@RequestParam("termId") String termId);
}
