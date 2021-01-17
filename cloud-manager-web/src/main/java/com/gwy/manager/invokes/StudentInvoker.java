package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.Student;
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
@RequestMapping("StudentMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER")
public interface StudentInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(String studentNo);

    @PostMapping("insert")
    int insert(Student record);

    @PostMapping("selectByPrimaryKey")
    Student selectByPrimaryKey(String studentNo);

    @PostMapping("selectAll")
    List<Student> selectAll();

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(Student record);

    /**
     * 批量添加学生
     *
     * @param students 学生列表
     * @return 结果集
     */
    @PostMapping("insertStudentBatch")
    int insertStudentBatch(List<Student> students);

    @PostMapping("updatePassword")
    int updatePassword(String studentNo, String password);

    @PostMapping("selectStudentsByDept")
    List<Student> selectStudentsByDept(String deptId);

    @PostMapping("selectStudentsByClass")
    List<Student> selectStudentsByClass(String classId);

    @PostMapping("selectStudentsMatchName")
    List<Student> selectStudentsMatchName(String deptId, String name);

    @PostMapping("selectStudentNamesByIds")
    List<Map<String, Object>> selectStudentNamesByIds(List<String> studentNos);

    @PostMapping("selectStudentNamesForMapByIds")
    Map<String, Map<String, String>> selectStudentNamesForMapByIds(List<String> studentNos);
}