package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.StudentClass;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@RestController
@RequestMapping("StudentClassMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER")
public interface StudentClassInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(String classId);

    @PostMapping("insert")
    int insert(StudentClass record);

    @PostMapping("selectByPrimaryKey")
    StudentClass selectByPrimaryKey(String classId);

    @PostMapping("selectAll")
    List<StudentClass> selectAll();

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(StudentClass record);

    @PostMapping("selectByDept")
    List<StudentClass> selectByDept(String deptId);
}