package com.gwy.manager.controller;

import com.gwy.manager.domain.entity.TeacherAssess;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.TeacherAssessMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("insert")
    int insert(TeacherAssess record){
        return teacherAssessMapper.insert(record);
    }
    @PostMapping("selectAll")
    List<TeacherAssess> selectAll(){
        return teacherAssessMapper.selectAll();
    }
    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(TeacherAssess record){
        return teacherAssessMapper.updateByPrimaryKey(record);
    }

    /**
     * 删除教师提交的评价
     * @param teacherNo 教师号
     * @param assessedTeacherNo 被评价的教师号
     * @param termId    学期id
     * @return  jieugoji
     */    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(String teacherNo, String assessedTeacherNo,String termId){
        return teacherAssessMapper.deleteByPrimaryKey(teacherNo,assessedTeacherNo,termId );
    }

    /**
     * 获取某学院的教师在某学期的评价列表
     * @param deptId    学院id
     * @param termId    学期id
     * @return  结果集
     */    @PostMapping("getTeacherAssessesByDeptAndTerm")
    List<TeacherAssess> getTeacherAssessesByDeptAndTerm(@Param("deptId") String deptId,
                                                        @Param("termId") String termId){
        return teacherAssessMapper.getTeacherAssessesByDeptAndTerm(deptId, termId);
    }

    /**
     * 获得某教师在某学期对某个教师的评价
     * @param teacherNo 教师号
     * @param assessedTeacherNo 被评价教师号
     * @param termId    学期id
     * @return  结果集
     */    @PostMapping("selectByPrimaryKey")
    TeacherAssess selectByPrimaryKey(@Param("teacherNo") String teacherNo,
                                     @Param("assessedTeacherNo") String assessedTeacherNo,
                                     @Param("termId") String termId){
        return teacherAssessMapper.selectByPrimaryKey(teacherNo, assessedTeacherNo, termId);
    }

    /**
     * 获得某教师的所有评价
     * @param teacherNo 教师id
     * @return  返回列表
     */    @PostMapping("selectAllByTno")
    List<TeacherAssess> selectAllByTno(String teacherNo){
        return teacherAssessMapper.selectAllByTno(teacherNo);
    }

    /**
     * 获得某教师在某学期的所有评价
     * @param teacherNo 教师id
     * @param assessedTeacherNos    被评价的id
     * @param termId    学期id
     * @return  被评价的教师id列表
     */    @PostMapping("judgeAssessed")
    List<String> judgeAssessed(@Param("teacherNo") String teacherNo,
                               @Param("assessedTeacherNos") List<String> assessedTeacherNos,
                               @Param("termId") String termId){
        return teacherAssessMapper.judgeAssessed(teacherNo, assessedTeacherNos, termId);
    }

    /**
     * 获得某学院教师某学期所有的教师评价结果
     * @param deptId    学院id
     * @param termId    学期id
     * @return  结果集
     */    @PostMapping("selectByTermAndDept")
    List<Map<String, Object>> selectByTermAndDept(@Param("deptId") String deptId,
                                                  @Param("termId") String termId){
        return teacherAssessMapper.selectByTermAndDept(deptId, termId);
    }
    @PostMapping("selectCountOfUserInTerm")
    int selectCountOfUserInTerm(@Param("userId") String userId,
                                @Param("termId") String termId){
        return teacherAssessMapper.selectCountOfUserInTerm(userId, termId);
    }
}