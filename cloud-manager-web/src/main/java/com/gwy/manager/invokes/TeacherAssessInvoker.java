package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.TeacherAssess;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
@RequestMapping("TeacherAssessMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",configuration = FeignClientProperties.FeignClientConfiguration.class, contextId = "67")
@Qualifier("webTeacherAssessInvoker")
public interface TeacherAssessInvoker {

    @PostMapping("insert")
    int insert(@RequestBody TeacherAssess record);

    @PostMapping("selectAll")
    List<TeacherAssess> selectAll();

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody TeacherAssess record);

    /**
     * 删除教师提交的评价
     *
     * @param teacherNo         教师号
     * @param assessedTeacherNo 被评价的教师号
     * @param termId            学期id
     * @return jieugoji
     */
    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("teacherNo") String teacherNo,
                           @RequestParam("assessedTeacherNo") String assessedTeacherNo,
                           @RequestParam("termId") String termId);

    /**
     * 获取某学院的教师在某学期的评价列表
     *
     * @param deptId 学院id
     * @param termId 学期id
     * @return 结果集
     */
    @PostMapping("getTeacherAssessesByDeptAndTerm")
    List<TeacherAssess> getTeacherAssessesByDeptAndTerm(@RequestParam("deptId") String deptId,
                                                        @RequestParam("termId") String termId);

    /**
     * 获得某教师在某学期对某个教师的评价
     *
     * @param teacherNo         教师号
     * @param assessedTeacherNo 被评价教师号
     * @param termId            学期id
     * @return 结果集
     */
    @PostMapping("selectByPrimaryKey")
    TeacherAssess selectByPrimaryKey(@RequestParam("teacherNo") String teacherNo,
                                     @RequestParam("assessedTeacherNo") String assessedTeacherNo,
                                     @RequestParam("termId") String termId);

    /**
     * 获得某教师的所有评价
     *
     * @param teacherNo 教师id
     * @return 返回列表
     */
    @PostMapping("selectAllByTno")
    List<TeacherAssess> selectAllByTno(@RequestParam("teacherNo") String teacherNo);

    /**
     * 获得某教师在某学期的所有评价
     *
     * @param teacherNo          教师id
     * @param assessedTeacherNos 被评价的id
     * @param termId             学期id
     * @return 被评价的教师id列表
     */
    @PostMapping("judgeAssessed")
    List<String> judgeAssessed(@RequestParam("teacherNo") String teacherNo,
                               @RequestParam("assessedTeacherNos") List<String> assessedTeacherNos,
                               @RequestParam("termId") String termId);

    /**
     * 获得某学院教师某学期所有的教师评价结果
     *
     * @param deptId 学院id
     * @param termId 学期id
     * @return 结果集
     */
    @PostMapping("selectByTermAndDept")
    List<Map<String, Object>> selectByTermAndDept(@RequestParam("deptId") String deptId,
                                                  @RequestParam("termId") String termId);

    @PostMapping("selectCountOfUserInTerm")
    int selectCountOfUserInTerm(@RequestParam("userId") String userId,
                                @RequestParam("termId") String termId);

}