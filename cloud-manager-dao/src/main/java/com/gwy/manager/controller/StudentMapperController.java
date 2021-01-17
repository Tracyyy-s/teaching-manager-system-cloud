package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.Student;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.StudentMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@RestController
@RequestMapping("StudentMapperController")
public class StudentMapperController {
    @Resource
    StudentMapper studentMapper;

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("studentNo") String studentNo) {
        return studentMapper.deleteByPrimaryKey(studentNo);
    }

    @PostMapping("insert")
    int insert(@RequestBody Student record) {
        return studentMapper.insert(record);
    }

    @PostMapping("selectByPrimaryKey")
    Student selectByPrimaryKey(@RequestParam("studentNo") String studentNo) {
        return studentMapper.selectByPrimaryKey(studentNo);
    }

    @PostMapping("selectAll")
    List<Student> selectAll() {
        return studentMapper.selectAll();
    }

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody Student record) {
        return studentMapper.updateByPrimaryKey(record);
    }

    /**
     * 批量添加学生
     *
     * @param students 学生列表
     * @return 结果集
     */
    @PostMapping("insertStudentBatch")
    int insertStudentBatch(@RequestBody List<Student> students) {
        return studentMapper.insertStudentBatch(students);
    }

    @PostMapping("updatePassword")
    int updatePassword(@RequestParam("studentNo") String studentNo, @RequestParam("password") String password) {
        return studentMapper.updatePassword(studentNo, password);
    }

    @PostMapping("selectStudentsByDept")
    List<Student> selectStudentsByDept(@RequestParam("deptId") String deptId) {
        return studentMapper.selectStudentsByDept(deptId);
    }

    @PostMapping("selectStudentsByClass")
    List<Student> selectStudentsByClass(@RequestParam("classId") String classId) {
        return studentMapper.selectStudentsByClass(classId);
    }

    @PostMapping("selectStudentsMatchName")
    List<Student> selectStudentsMatchName(@RequestParam("deptId") String deptId, @RequestParam("name") String name) {
        return studentMapper.selectStudentsMatchName(deptId, name);
    }

    @PostMapping("selectStudentNamesByIds")
    List<Map<String, Object>> selectStudentNamesByIds(@RequestBody List<String> studentNos) {
        return studentMapper.selectStudentNamesByIds(studentNos);
    }

    @PostMapping("selectStudentNamesForMapByIds")
    Map<String, Map<String, String>> selectStudentNamesForMapByIds(@RequestBody List<String> studentNos) {
        return studentMapper.selectStudentNamesForMapByIds(studentNos);
    }
}