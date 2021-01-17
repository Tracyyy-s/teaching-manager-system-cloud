package com.gwy.manager.controller;


import com.gwy.manager.domain.entity.Course;
import com.gwy.manager.mapper.CourseMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("courseNo") String courseNo) {
        return courseMapper.deleteByPrimaryKey(courseNo);
    }

    @RequestMapping("insert")
    int insert(@RequestBody Course record) {
        return courseMapper.insert(record);
    }

    @RequestMapping("selectByPrimaryKey")
    Course selectByPrimaryKey(@RequestParam("courseNo") String courseNo) {
        return courseMapper.selectByPrimaryKey(courseNo);
    }

    @RequestMapping("selectAll")
    List<Course> selectAll() {
        return courseMapper.selectAll();
    }

    @RequestMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody Course record) {
        return courseMapper.updateByPrimaryKey(record);
    }

    @RequestMapping("getCoursesOfTeacher")
    List<Course> getCoursesOfTeacher(@RequestParam("teacherNo") String teacherNo) {
        return courseMapper.getCoursesOfTeacher(teacherNo);
    }

    @RequestMapping("getCoursesByIds")
    List<Course> getCoursesByIds(@RequestBody List<String> courseNos) {
        return courseMapper.getCoursesByIds(courseNos);
    }

    @RequestMapping("getCoursesForMapByIds")
    Map<String, Course> getCoursesForMapByIds(@RequestBody List<String> courseNos) {
        return courseMapper.getCoursesForMapByIds(courseNos);
    }
}