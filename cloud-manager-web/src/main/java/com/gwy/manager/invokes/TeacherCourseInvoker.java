package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.TeacherCourse;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@FeignClient(serviceId = "springcloud-tqms-dao",contextId = "TeacherCourseInvoker")
@RequestMapping("TeacherCourseMapperController")
public interface TeacherCourseInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(String tcId);

    @PostMapping("insert")
    int insert(TeacherCourse record);

    @PostMapping("selectByPrimaryKey")
    TeacherCourse selectByPrimaryKey(String tcId);

    @PostMapping("selectAll")
    List<TeacherCourse> selectAll();

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(TeacherCourse record);

    /**
     * 修改学生评价分数
     *
     * @param tcId        教师课程id
     * @param changeScore 修改的分数
     * @return 返回结果
     */
    @PostMapping("updateAppraiseScore")
    int updateAppraiseScore(@Param("tcId") String tcId,
                            @Param("changeScore") Integer changeScore);

    /**
     * 获得教师在某学期教授的全部课程
     *
     * @param teacherNo 教师id
     * @param termId    学期id
     * @return 返回结果
     */
    @PostMapping("selectByTeacherNoAndTermId")
    List<TeacherCourse> selectByTeacherNoAndTermId(@Param("teacherNo") String teacherNo,
                                                   @Param("termId") String termId);

    /**
     * 获得学生在某学期学习的全部课程
     *
     * @param studentNo 学号
     * @param termId    学期
     * @return 返回结果
     */
    @PostMapping("selectByStudentNoAndTermId")
    List<TeacherCourse> selectByStudentNoAndTermId(@Param("studentNo") String studentNo,
                                                   @Param("termId") String termId);

    /**
     * 获得某学院某学期开设的所有课程
     *
     * @param deptId 学院id
     * @param termId 学期id
     * @return 结果集
     */
    @PostMapping("selectByTermAndDept")
    List<TeacherCourse> selectByTermAndDept(@Param("deptId") String deptId,
                                            @Param("termId") String termId);

    /**
     * 批量添加教师-课程信息
     *
     * @param teacherCourses 预添加
     * @return 结果集
     */
    @PostMapping("insertBatch")
    int insertBatch(@Param("teacherCourses") List<TeacherCourse> teacherCourses);
}