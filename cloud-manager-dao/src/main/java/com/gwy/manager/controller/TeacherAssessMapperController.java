package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.TeacherAssess;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.TeacherAssessMapper;
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
@RequestMapping("TeacherAssessMapperController")
public class TeacherAssessMapperController {
    @Resource
    TeacherAssessMapper teacherAssessMapper;

    @RequestMapping("insert")
    int insert(@RequestBody TeacherAssess record) {
        return teacherAssessMapper.insert(record);
    }

    @RequestMapping("selectAll")
    List<TeacherAssess> selectAll() {
        return teacherAssessMapper.selectAll();
    }

    @RequestMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody TeacherAssess record) {
        return teacherAssessMapper.updateByPrimaryKey(record);
    }

    /**
     * 删除教师提交的评价
     *
     * @param teacherNo         教师号
     * @param assessedTeacherNo 被评价的教师号
     * @param termId            学期id
     * @return jieugoji
     */
    @RequestMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("teacherNo") String teacherNo, @RequestParam("assessedTeacherNo") String assessedTeacherNo, @RequestParam("termId") String termId) {
        return teacherAssessMapper.deleteByPrimaryKey(teacherNo, assessedTeacherNo, termId);
    }

    /**
     * 获取某学院的教师在某学期的评价列表
     *
     * @param deptId 学院id
     * @param termId 学期id
     * @return 结果集
     */
    @RequestMapping("getTeacherAssessesByDeptAndTerm")
    List<TeacherAssess> getTeacherAssessesByDeptAndTerm(@RequestParam("deptId") String deptId,
                                                        @RequestParam("termId") String termId) {
        return teacherAssessMapper.getTeacherAssessesByDeptAndTerm(deptId, termId);
    }

    /**
     * 获得某教师在某学期对某个教师的评价
     *
     * @param teacherNo         教师号
     * @param assessedTeacherNo 被评价教师号
     * @param termId            学期id
     * @return 结果集
     */
    @RequestMapping("selectByPrimaryKey")
    TeacherAssess selectByPrimaryKey(@RequestParam("teacherNo") String teacherNo,
                                     @RequestParam("assessedTeacherNo") String assessedTeacherNo,
                                     @RequestParam("termId") String termId) {
        return teacherAssessMapper.selectByPrimaryKey(teacherNo, assessedTeacherNo, termId);
    }

    /**
     * 获得某教师的所有评价
     *
     * @param teacherNo 教师id
     * @return 返回列表
     */
    @RequestMapping("selectAllByTno")
    List<TeacherAssess> selectAllByTno(@RequestParam("teacherNo") String teacherNo) {
        return teacherAssessMapper.selectAllByTno(teacherNo);
    }

    /**
     * 获得某教师在某学期的所有评价
     *
     * @param teacherNo          教师id
     * @param assessedTeacherNos 被评价的id
     * @param termId             学期id
     * @return 被评价的教师id列表
     */
    @RequestMapping("judgeAssessed")
    List<String> judgeAssessed(@RequestParam("teacherNo") String teacherNo,
                               @RequestParam("assessedTeacherNos") List<String> assessedTeacherNos,
                               @RequestParam("termId") String termId) {
        return teacherAssessMapper.judgeAssessed(teacherNo, assessedTeacherNos, termId);
    }

    /**
     * 获得某学院教师某学期所有的教师评价结果
     *
     * @param deptId 学院id
     * @param termId 学期id
     * @return 结果集
     */
    @RequestMapping("selectByTermAndDept")
    List<Map<String, Object>> selectByTermAndDept(@RequestParam("deptId") String deptId,
                                                  @RequestParam("termId") String termId) {
        return teacherAssessMapper.selectByTermAndDept(deptId, termId);
    }

    @RequestMapping("selectCountOfUserInTerm")
    int selectCountOfUserInTerm(@RequestParam("userId") String userId,
                                @RequestParam("termId") String termId) {
        return teacherAssessMapper.selectCountOfUserInTerm(userId, termId);
    }
}