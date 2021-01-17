package com.gwy.manager.service.impl;

import com.gwy.manager.domain.dto.ResultVO;
import com.gwy.manager.domain.entity.Course;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.invokes.CourseInvoker;
import com.gwy.manager.service.CourseService;
import com.gwy.manager.util.ResultVoUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/1 23:09
 */
@Service
public class CourserServiceImpl implements CourseService {

    @Autowired
    private CourseInvoker courseInvoker;

    @Override
    public Course getCourse(String courseNo) {
        return courseInvoker.selectByPrimaryKey(courseNo);
    }

    @Override
    public int addCourse(Course course) {
        return courseInvoker.insert(course);
    }

    @Override
    public int updateCourse(Course course) {
        return courseInvoker.updateByPrimaryKey(course);
    }

    @Override
    public ResultVO getCoursesOfTeacher(String teacherNo) {

        ResultVO resultVO;

        List<Course> coursesOfTeacher = courseInvoker.getCoursesOfTeacher(teacherNo);
        if (CollectionUtils.isEmpty(coursesOfTeacher)) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVoUtil.success(coursesOfTeacher);
        }

        return resultVO;
    }
}
