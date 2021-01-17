package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.StudentAssess;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@RestController
@RequestMapping("StudentAssessMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER")
public interface StudentAssessInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(String studentNo, String tcId);

    @PostMapping("insert")
    int insert(StudentAssess record);

    @PostMapping("selectByPrimaryKey")
    StudentAssess selectByPrimaryKey(String studentNo, String tcId);

    @PostMapping("selectAll")
    List<StudentAssess> selectAll();

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(StudentAssess record);

    /**
     * 获取学生在本学期的评价列表
     *
     * @param studentNo 学号
     * @param termId    学期号
     * @return 结果集
     */
    @PostMapping("selectByStudentNoAndTerm")
    List<StudentAssess> selectByStudentNoAndTerm(String studentNo, String termId);

    /**
     * 获得某门课的评价
     *
     * @param tcId 课程号
     * @return 结果集
     */
    @PostMapping("selectByTcId")
    List<StudentAssess> selectByTcId(String tcId);

    /**
     * 获得学生对课程评价状态
     *
     * @param studentNo 学号
     * @param tcIds     教师课程号
     * @return 结果集
     */
    @PostMapping("selectStateByStudentAndTcIds")
    List<Integer> selectStateByStudentAndTcIds(String studentNo, List<String> tcIds);

    /**
     * 获得某学期教师所有的课程评价
     *
     * @param teacherNos 教师id列表
     * @param termId     学期id
     * @return 结果集
     */
    @PostMapping("selectByTeacherNosAndTerm")
    List<Map<String, Object>> selectByTeacherNosAndTerm(List<String> teacherNos, String termId);
}