package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.Student;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.StudentMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("studentNo") String studentNo) {
        return studentMapper.deleteByPrimaryKey(studentNo);
    }

    @RequestMapping("insert")
    int insert(@RequestBody Student record) {
        return studentMapper.insert(record);
    }

    @RequestMapping("selectByPrimaryKey")
    Student selectByPrimaryKey(@RequestParam("studentNo") String studentNo) {
        return studentMapper.selectByPrimaryKey(studentNo);
    }

    @RequestMapping("selectAll")
    List<Student> selectAll() {
        return studentMapper.selectAll();
    }

    @RequestMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody Student record) {
        return studentMapper.updateByPrimaryKey(record);
    }

    /**
     * 批量添加学生
     *
     * @param students 学生列表
     * @return 结果集
     */
    @RequestMapping("insertStudentBatch")
    int insertStudentBatch(@RequestBody List<Student> students) {
        return studentMapper.insertStudentBatch(students);
    }

    @RequestMapping("updatePassword")
    int updatePassword(@RequestParam("studentNo") String studentNo, @RequestParam("password") String password) {
        return studentMapper.updatePassword(studentNo, password);
    }

    @RequestMapping("selectStudentsByDept")
    List<Student> selectStudentsByDept(@RequestParam("deptId") String deptId) {
        return studentMapper.selectStudentsByDept(deptId);
    }

    @RequestMapping("selectStudentsByClass")
    List<Student> selectStudentsByClass(@RequestParam("classId") String classId) {
        return studentMapper.selectStudentsByClass(classId);
    }

    @RequestMapping("selectStudentsMatchName")
    List<Student> selectStudentsMatchName(@RequestParam("deptId") String deptId, @RequestParam("name") String name) {
        return studentMapper.selectStudentsMatchName(deptId, name);
    }

    @RequestMapping("selectStudentNamesByIds")
    List<Map<String, Object>> selectStudentNamesByIds(@RequestBody List<String> studentNos) {
        return studentMapper.selectStudentNamesByIds(studentNos);
    }

    @RequestMapping("selectStudentNamesForMapByIds")
    Map<String, Map<String, String>> selectStudentNamesForMapByIds(@RequestBody List<String> studentNos) {
        return studentMapper.selectStudentNamesForMapByIds(studentNos);
    }
}