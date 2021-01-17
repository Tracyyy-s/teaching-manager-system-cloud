package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("StudentMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",configuration = FeignClientProperties.FeignClientConfiguration.class, contextId = "45")
@Qualifier("webStudentInvoker")
public interface StudentInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("studentNo") String studentNo);

    @PostMapping("insert")
    int insert(@RequestBody Student record);

    @PostMapping("selectByPrimaryKey")
    Student selectByPrimaryKey(@RequestParam("studentNo") String studentNo);

    @PostMapping("selectAll")
    List<Student> selectAll();

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody Student record);

    /**
     * 批量添加学生
     *
     * @param students 学生列表
     * @return 结果集
     */
    @PostMapping("insertStudentBatch")
    int insertStudentBatch(@RequestBody List<Student> students);

    @PostMapping("updatePassword")
    int updatePassword(@RequestParam("studentNo") String studentNo, @RequestParam("password") String password);

    @PostMapping("selectStudentsByDept")
    List<Student> selectStudentsByDept(@RequestParam("deptId") String deptId);

    @PostMapping("selectStudentsByClass")
    List<Student> selectStudentsByClass(@RequestParam("classId") String classId);

    @PostMapping("selectStudentsMatchName")
    List<Student> selectStudentsMatchName(@RequestParam("deptId") String deptId,
                                          @RequestParam("name") String name);

    @PostMapping("selectStudentNamesByIds")
    List<Map<String, Object>> selectStudentNamesByIds(@RequestBody List<String> studentNos);

    @PostMapping("selectStudentNamesForMapByIds")
    Map<String, Map<String, String>> selectStudentNamesForMapByIds(@RequestBody List<String> studentNos);
}