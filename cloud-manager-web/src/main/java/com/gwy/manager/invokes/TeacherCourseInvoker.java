package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.TeacherCourse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
@RequestMapping("TeacherCourseMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",configuration = FeignClientProperties.FeignClientConfiguration.class, contextId = "89")
@Qualifier("webTeacherCourseInvoker")
public interface TeacherCourseInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("tcId") String tcId);

    @PostMapping("insert")
    int insert(@RequestBody TeacherCourse record);

    @PostMapping("selectByPrimaryKey")
    TeacherCourse selectByPrimaryKey(@RequestParam("tcId") String tcId);

    @PostMapping("selectAll")
    List<TeacherCourse> selectAll();

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody TeacherCourse record);

    /**
     * 修改学生评价分数
     *
     * @param tcId        教师课程id
     * @param changeScore 修改的分数
     * @return 返回结果
     */
    @PostMapping("updateAppraiseScore")
    int updateAppraiseScore(@RequestParam("tcId") String tcId,
                            @RequestParam("changeScore") Integer changeScore);

    /**
     * 获得教师在某学期教授的全部课程
     *
     * @param teacherNo 教师id
     * @param termId    学期id
     * @return 返回结果
     */
    @PostMapping("selectByTeacherNoAndTermId")
    List<TeacherCourse> selectByTeacherNoAndTermId(@RequestParam("teacherNo") String teacherNo,
                                                   @RequestParam("termId") String termId);

    /**
     * 获得学生在某学期学习的全部课程
     *
     * @param studentNo 学号
     * @param termId    学期
     * @return 返回结果
     */
    @PostMapping("selectByStudentNoAndTermId")
    List<TeacherCourse> selectByStudentNoAndTermId(@RequestParam("studentNo") String studentNo,
                                                   @RequestParam("termId") String termId);

    /**
     * 获得某学院某学期开设的所有课程
     *
     * @param deptId 学院id
     * @param termId 学期id
     * @return 结果集
     */
    @PostMapping("selectByTermAndDept")
    List<TeacherCourse> selectByTermAndDept(@RequestParam("deptId") String deptId,
                                            @RequestParam("termId") String termId);

    /**
     * 批量添加教师-课程信息
     *
     * @param teacherCourses 预添加
     * @return 结果集
     */
    @PostMapping("insertBatch")
    int insertBatch(@RequestParam("teacherCourses") List<TeacherCourse> teacherCourses);
}