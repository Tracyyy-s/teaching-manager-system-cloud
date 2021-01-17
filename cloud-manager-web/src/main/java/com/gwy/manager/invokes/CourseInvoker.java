package com.gwy.manager.invokes;


import com.gwy.manager.domain.entity.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@FeignClient(serviceId = "springcloud-tqms-dao",contextId = "CourseInvoker")
@RequestMapping("courseMapperController")
public interface CourseInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(String courseNo) ;

    @PostMapping("insert")
    int insert(Course record) ;

    @PostMapping("selectByPrimaryKey")
    Course selectByPrimaryKey(String courseNo);

    @PostMapping("selectAll")
    List<Course> selectAll() ;

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(Course record);

    @PostMapping("getCoursesOfTeacher")
    List<Course> getCoursesOfTeacher(String teacherNo);

    @PostMapping("getCoursesByIds")
    List<Course> getCoursesByIds(@Param("courseNos") List<String> courseNos);

    @PostMapping("getCoursesForMapByIds")
    Map<String, Course> getCoursesForMapByIds(@Param("courseNos") List<String> courseNos) ;
}