package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.StudentClass;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.StudentClassMapper;
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
@RequestMapping("StudentClassMapperController")
public class StudentClassMapperController {
    @Resource
    StudentClassMapper studentClassMapper;

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("classId") String classId) {
        return studentClassMapper.deleteByPrimaryKey(classId);
    }

    @PostMapping("insert")
    int insert(@RequestBody StudentClass record) {
        return studentClassMapper.insert(record);
    }

    @PostMapping("selectByPrimaryKey")
    StudentClass selectByPrimaryKey(@RequestParam("classId") String classId) {
        return studentClassMapper.selectByPrimaryKey(classId);
    }

    @PostMapping("selectAll")
    List<StudentClass> selectAll() {
        return studentClassMapper.selectAll();
    }

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody StudentClass record) {
        return studentClassMapper.updateByPrimaryKey(record);
    }

    @PostMapping("selectByDept")
    List<StudentClass> selectByDept(@RequestParam("deptId") String deptId) {
        return studentClassMapper.selectByDept(deptId);
    }
}