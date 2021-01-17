package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.StudentClass;
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

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
@RequestMapping("StudentClassMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",configuration = FeignClientProperties.FeignClientConfiguration.class, contextId = "99")
@Qualifier("webStudentClassInvoker")
public interface StudentClassInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("classId") String classId);

    @PostMapping("insert")
    int insert(@RequestBody StudentClass record);

    @PostMapping("selectByPrimaryKey")
    StudentClass selectByPrimaryKey(@RequestParam("classId") String classId);

    @PostMapping("selectAll")
    List<StudentClass> selectAll();

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody StudentClass record);

    @PostMapping("selectByDept")
    List<StudentClass> selectByDept(@RequestParam("deptId") String deptId);
}