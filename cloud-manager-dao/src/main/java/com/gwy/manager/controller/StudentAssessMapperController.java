package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.Student;
import com.gwy.manager.domain.entity.StudentAssess;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.StudentAssessMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("StudentAssessMapperController")
public class StudentAssessMapperController {
    @Resource
    StudentAssessMapper studentAssessMapper;

    @GetMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("studentNo") String studentNo, @RequestParam("tcId") String tcId) {
        return studentAssessMapper.deleteByPrimaryKey(studentNo, tcId);
    }

    @GetMapping("insert")
    int insert(@RequestBody StudentAssess record) {
        return studentAssessMapper.insert(record);
    }

    @GetMapping("selectByPrimaryKey")
    StudentAssess selectByPrimaryKey(@RequestParam("studentNo") String studentNo, @RequestParam("tcId") String tcId) {
        return studentAssessMapper.selectByPrimaryKey(studentNo, tcId);
    }

    @GetMapping("selectAll")
    List<StudentAssess> selectAll() {
        return studentAssessMapper.selectAll();
    }

    @GetMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody StudentAssess record) {
        return studentAssessMapper.updateByPrimaryKey(record);
    }

    /**
     * 获取学生在本学期的评价列表
     *
     * @param studentNo 学号
     * @param termId    学期号
     * @return 结果集
     */
    @GetMapping("selectByStudentNoAndTerm")
    List<StudentAssess> selectByStudentNoAndTerm(@RequestParam("studentNo") String studentNo, @RequestParam("termId") String termId) {
        return studentAssessMapper.selectByStudentNoAndTerm(studentNo, termId);
    }

    /**
     * 获得某门课的评价
     *
     * @param tcId 课程号
     * @return 结果集
     */
    @GetMapping("selectByTcId")
    List<StudentAssess> selectByTcId(@RequestParam("tcId") String tcId) {
        return studentAssessMapper.selectByTcId(tcId);
    }

    /**
     * 获得学生对课程评价状态
     *
     * @param studentNo 学号
     * @param tcIds     教师课程号
     * @return 结果集
     */
    @GetMapping("selectStateByStudentAndTcIds")
    List<Integer> selectStateByStudentAndTcIds(@RequestParam("studentNo") String studentNo, @RequestBody List<String> tcIds) {
        return studentAssessMapper.selectStateByStudentAndTcIds(studentNo, tcIds);
    }

    /**
     * 获得某学期教师所有的课程评价
     *
     * @param teacherNos 教师id列表
     * @param termId     学期id
     * @return 结果集
     */
    @GetMapping("selectByTeacherNosAndTerm")
    List<Map<String, Object>> selectByTeacherNosAndTerm(@RequestBody List<String> teacherNos, @RequestParam("termId") String termId) {
        return studentAssessMapper.selectByTeacherNosAndTerm(teacherNos, termId);
    }
}