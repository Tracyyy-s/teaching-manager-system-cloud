package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.Target;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.TargetMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("targetId") Integer targetId) {
        return targetMapper.deleteByPrimaryKey(targetId);
    }

    @RequestMapping("insert")
    int insert(@RequestBody Target record) {
        return targetMapper.insert(record);
    }

    @RequestMapping("selectByPrimaryKey")
    Target selectByPrimaryKey(@RequestParam("targetId") Integer targetId) {
        return targetMapper.selectByPrimaryKey(targetId);
    }

    @RequestMapping("selectAll")
    List<Target> selectAll() {
        return targetMapper.selectAll();
    }

    @RequestMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody Target record) {
        return targetMapper.updateByPrimaryKey(record);
    }

    /**
     * 获得学生评价的指标
     *
     * @return 结果集
     */
    @RequestMapping("getStudentTargets")
    List<Target> getStudentTargets() {
        return targetMapper.getStudentTargets();
    }

    /**
     * 获得教师评价的指标
     *
     * @return 结果集
     */
    @RequestMapping("getTeacherTargets")
    List<Target> getTeacherTargets() {
        return targetMapper.getTeacherTargets();
    }

    /**
     * 获得指标列表的指标
     *
     * @return 结果集
     */
    @RequestMapping("getTargetsByIds")
    List<Target> getTargetsByIds(@RequestBody List<Integer> ids) {
        return targetMapper.getTargetsByIds(ids);
    }
}