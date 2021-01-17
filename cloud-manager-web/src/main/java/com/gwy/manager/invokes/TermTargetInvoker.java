package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.TermTarget;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tracy
 * @date 2021/1/17 9:40
 */
@FeignClient(serviceId = "springcloud-tqms-dao",contextId = "TermTargetInvoker")
@RequestMapping("TermTargetInvoker")
public interface TermTargetInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@Param("termId") String termId, @Param("targetId") Integer targetId);

    @PostMapping("insert")
    int insert(TermTarget record);

    @PostMapping("selectByPrimaryKey")
    TermTarget selectByPrimaryKey(@Param("termId") String termId, @Param("targetId") Integer targetId);

    @PostMapping("selectAll")
    List<TermTarget> selectAll();

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(TermTarget record);

    /**
     * 添加本学期的所有评价指标
     *
     * @param termTargets 学期评价指标
     * @return 结果集
     */
    @PostMapping("insertTermTargets")
    int insertTermTargets(@Param("termTargets") List<TermTarget> termTargets);

    /**
     * 获得学期所有学生评价指标
     *
     * @param termId 学期
     * @return 结果集
     */
    @PostMapping("getStudentTermTargets")
    List<Integer> getStudentTermTargets(String termId);

    /**
     * 获得学期所有教师评价指标
     *
     * @param termId 学期
     * @return 结果集
     */
    @PostMapping("getTeacherTermTargets")
    List<Integer> getTeacherTermTargets(String termId);
}
