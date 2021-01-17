package com.gwy.manager.invokes;


import com.gwy.manager.domain.entity.Course;
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
@RequestMapping("courseMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",configuration = FeignClientProperties.FeignClientConfiguration.class, contextId = "11")
@Qualifier("webCourseInvoker")
public interface CourseInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("courseNo") String courseNo) ;

    @PostMapping("insert")
    int insert(@RequestBody Course record) ;

    @PostMapping("selectByPrimaryKey")
    Course selectByPrimaryKey(@RequestParam("courseNo") String courseNo);

    @PostMapping("selectAll")
    List<Course> selectAll() ;

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody Course record);

    @PostMapping("getCoursesOfTeacher")
    List<Course> getCoursesOfTeacher(@RequestParam("teacherNo") String teacherNo);

    @PostMapping("getCoursesByIds")
    List<Course> getCoursesByIds(@RequestBody List<String> courseNos);

    @PostMapping("getCoursesForMapByIds")
    Map<String, Course> getCoursesForMapByIds(@RequestBody List<String> courseNos) ;
}