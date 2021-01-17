package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.StudentAssess;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
@RequestMapping("StudentAssessMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",configuration = FeignClientProperties.FeignClientConfiguration.class, contextId = "88")
@Qualifier("webStudentAssessInvoker")
public interface StudentAssessInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("studentNo") String studentNo,
                           @RequestParam("tcId") String tcId);

    @PostMapping("insert")
    int insert(@RequestBody StudentAssess record);

    @PostMapping("selectByPrimaryKey")
    StudentAssess selectByPrimaryKey(@RequestParam("studentNo")String studentNo,
                                     @RequestParam("tcId") String tcId);

    @PostMapping("selectAll")
    List<StudentAssess> selectAll();

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody StudentAssess record);

    /**
     * 获取学生在本学期的评价列表
     *
     * @param studentNo 学号
     * @param termId    学期号
     * @return 结果集
     */
    @PostMapping("selectByStudentNoAndTerm")
    List<StudentAssess> selectByStudentNoAndTerm(@RequestParam("studentNo")String studentNo,
                                                 @RequestParam("termId") String termId);

    /**
     * 获得某门课的评价
     *
     * @param tcId 课程号
     * @return 结果集
     */
    @PostMapping("selectByTcId")
    List<StudentAssess> selectByTcId(@RequestParam("tcId") String tcId);

    /**
     * 获得学生对课程评价状态
     *
     * @param studentNo 学号
     * @param tcIds     教师课程号
     * @return 结果集
     */
    @PostMapping("selectStateByStudentAndTcIds")
    List<Integer> selectStateByStudentAndTcIds(@RequestParam("studentNo") String studentNo,
                                               @RequestBody List<String> tcIds);

    /**
     * 获得某学期教师所有的课程评价
     *
     * @param teacherNos 教师id列表
     * @param termId     学期id
     * @return 结果集
     */
    @PostMapping("selectByTeacherNosAndTerm")
    List<Map<String, Object>> selectByTeacherNosAndTerm(@RequestBody List<String> teacherNos, @RequestParam("termId") String termId);
}