package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.TermTarget;
import com.gwy.manager.mapper.TermTargetMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@RestController
@RequestMapping("TermTargetMapperController")
public class TermTargetMapperController {

    @Resource
    TermTargetMapper termTargetMapper;

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("termId") String termId, @RequestParam("targetId") Integer targetId) {
        return termTargetMapper.deleteByPrimaryKey(termId, targetId);
    }

    @PostMapping("insert")
    int insert(@RequestBody TermTarget record) {
        return termTargetMapper.insert(record);
    }

    @PostMapping("selectByPrimaryKey")
    TermTarget selectByPrimaryKey(@RequestParam("termId") String termId, @RequestParam("targetId") Integer targetId) {
        return termTargetMapper.selectByPrimaryKey(termId, targetId);
    }

    @PostMapping("selectAll")
    List<TermTarget> selectAll() {
        return termTargetMapper.selectAll();
    }

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody TermTarget record) {
        return termTargetMapper.updateByPrimaryKey(record);
    }

    /**
     * 添加本学期的所有评价指标
     *
     * @param termTargets 学期评价指标
     * @return 结果集
     */
    @PostMapping("insertTermTargets")
    int insertTermTargets(@RequestBody List<TermTarget> termTargets) {
        return termTargetMapper.insertTermTargets(termTargets);
    }

    /**
     * 获得学期所有学生评价指标
     *
     * @param termId 学期
     * @return 结果集
     */
    @PostMapping("getStudentTermTargets")
    List<Integer> getStudentTermTargets(@RequestParam("termId") String termId) {
        return termTargetMapper.getStudentTermTargets(termId);
    }

    /**
     * 获得学期所有教师评价指标
     *
     * @param termId 学期
     * @return 结果集
     */
    @PostMapping("getTeacherTermTargets")
    List<Integer> getTeacherTermTargets(@RequestParam("termId") String termId) {
        return termTargetMapper.getTeacherTermTargets(termId);
    }
}