package com.gwy.manager.controller;



import com.gwy.manager.domain.entity.Course;
import com.gwy.manager.mapper.CourseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@RestController
@RequestMapping("courseMapperController")
public class CourseMapperController {
    @Resource
    private CourseMapper courseMapper;
    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(String courseNo){
        return courseMapper.deleteByPrimaryKey(courseNo);
    }
    @PostMapping("insert")
    int insert(Course record){
        return courseMapper.insert(record);
    }

    @PostMapping("selectByPrimaryKey")
    Course selectByPrimaryKey(String courseNo){
        return courseMapper.selectByPrimaryKey(courseNo);
    }

    @PostMapping("selectAll")
    List<Course> selectAll(){
        return courseMapper.selectAll();
    }
    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(Course record){
        return courseMapper.updateByPrimaryKey(record);
    }
    @PostMapping("getCoursesOfTeacher")
    List<Course> getCoursesOfTeacher(String teacherNo){
        return courseMapper.getCoursesOfTeacher(teacherNo);
    }
    @PostMapping("getCoursesByIds")
    List<Course> getCoursesByIds(@Param("courseNos") List<String> courseNos){
        return courseMapper.getCoursesByIds( courseNos);
    }

    @PostMapping("getCoursesForMapByIds")
    Map<String, Course> getCoursesForMapByIds(@Param("courseNos") List<String> courseNos){
        return courseMapper.getCoursesForMapByIds(courseNos);
    }
}