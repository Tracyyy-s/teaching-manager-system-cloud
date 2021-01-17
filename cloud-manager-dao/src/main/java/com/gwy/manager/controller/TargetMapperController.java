package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.Target;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.TargetMapper;
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
@RequestMapping("TargetMapperController")
public class TargetMapperController {
    @Resource
    TargetMapper targetMapper;

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("targetId") Integer targetId) {
        return targetMapper.deleteByPrimaryKey(targetId);
    }

    @PostMapping("insert")
    int insert(@RequestBody Target record) {
        return targetMapper.insert(record);
    }

    @PostMapping("selectByPrimaryKey")
    Target selectByPrimaryKey(@RequestParam("targetId") Integer targetId) {
        return targetMapper.selectByPrimaryKey(targetId);
    }

    @PostMapping("selectAll")
    List<Target> selectAll() {
        return targetMapper.selectAll();
    }

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody Target record) {
        return targetMapper.updateByPrimaryKey(record);
    }

    /**
     * 获得学生评价的指标
     *
     * @return 结果集
     */
    @PostMapping("getStudentTargets")
    List<Target> getStudentTargets() {
        return targetMapper.getStudentTargets();
    }

    /**
     * 获得教师评价的指标
     *
     * @return 结果集
     */
    @PostMapping("getTeacherTargets")
    List<Target> getTeacherTargets() {
        return targetMapper.getTeacherTargets();
    }

    /**
     * 获得指标列表的指标
     *
     * @return 结果集
     */
    @PostMapping("getTargetsByIds")
    List<Target> getTargetsByIds(@RequestBody List<Integer> ids) {
        return targetMapper.getTargetsByIds(ids);
    }
}