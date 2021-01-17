package com.gwy.manager.service.impl;

import com.gwy.manager.domain.dto.ResultVO;
import com.gwy.manager.domain.entity.Course;
import com.gwy.manager.domain.entity.TeacherCourse;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.invokes.CourseInvoker;
import com.gwy.manager.invokes.StudentAssessInvoker;
import com.gwy.manager.invokes.TeacherCourseInvoker;
import com.gwy.manager.invokes.TermInvoker;
import com.gwy.manager.invokes.UserInvoker;
import com.gwy.manager.service.TeacherCourseService;
import com.gwy.manager.util.BeanUtil;
import com.gwy.manager.util.DateUtilCustom;
import com.gwy.manager.util.ResultVOUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/1 23:13
 */
@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {

    private static final String ASSESS_STATE = "assessed";

    @Qualifier("webTeacherCourseInvoker")
    @Autowired
    private TeacherCourseInvoker teacherCourseMapper;

    @Qualifier("webTermInvoker")
    @Autowired
    private TermInvoker termMapper;

    @Qualifier("webCourseInvoker")
    @Autowired
    private CourseInvoker courseMapper;

    @Qualifier("webUserInvoker")
    @Autowired
    private UserInvoker userMapper;

    @Qualifier("webStudentAssessInvoker")
    @Autowired
    private StudentAssessInvoker studentAssessMapper;

    @Override
    public int addTeacherCourse(TeacherCourse teacherCourse) {
        return teacherCourseMapper.insert(teacherCourse);
    }

    @Override
    public int changeTeacherCourseScore(String tcId, Integer changeScore) {
        return teacherCourseMapper.updateAppraiseScore(tcId, changeScore);
    }

    @Override
    public int addTeacherCoursesBatch(List<TeacherCourse> teacherCourses) {
        return teacherCourseMapper.insertBatch(teacherCourses);
    }

    @Override
    public ResultVO getCoursesByTeacherAndTerm(String teacherNo) {

        ResultVO resultVO;

        //获得当前学期
        String currentTermId = termMapper.getCurrentTerm(DateUtilCustom.getDate()).getTermId();
        try {
            List<TeacherCourse> teacherCourses = teacherCourseMapper
                    .selectByTeacherNoAndTermId(teacherNo, currentTermId);

            if (CollectionUtils.isEmpty(teacherCourses)) {
                resultVO = ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
            } else {
                resultVO = ResultVOUtil.success(this.teacherCourseFormat(teacherCourses));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultVO = ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
        }

        return resultVO;
    }

    @Override
    public ResultVO getCoursesByStudentAndTerm(String studentNo, String termId) {

        ResultVO resultVO;

        List<TeacherCourse> teacherCourses = teacherCourseMapper
                .selectByStudentNoAndTermId(studentNo, termId);

        if (CollectionUtils.isEmpty(teacherCourses)) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            List<String> tcIds = new ArrayList<>();
            for (TeacherCourse teacherCourse : teacherCourses) {
                tcIds.add(teacherCourse.getTcId());
            }

            List<Integer> assessStates = studentAssessMapper.selectStateByStudentAndTcIds(studentNo, tcIds);
            Collection<Map<String, Object>> maps = this.teacherCourseFormat(teacherCourses);

            int i = 0;
            for (Map<String, Object> map : maps) {
                map.put(ASSESS_STATE, assessStates.get(i++) == 1);
            }

            resultVO = ResultVOUtil.success(maps);
        }

        return resultVO;
    }

    @Override
    public ResultVO getCoursesByDeptAndTerm(String deptId, String termId) {

        ResultVO resultVO;

        List<TeacherCourse> teacherCourses = teacherCourseMapper
                .selectByTermAndDept(deptId, termId);
        if (CollectionUtils.isEmpty(teacherCourses)) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVOUtil.success(this.teacherCourseFormat(teacherCourses));
        }
        return resultVO;
    }

    private Collection<Map<String, Object>> teacherCourseFormat(List<TeacherCourse> teacherCourses) {
        Collection<Map<String, Object>> maps = BeanUtil.beansToList(teacherCourses);

        List<String> courseNos = new ArrayList<>();
        List<String> teacherNos = new ArrayList<>();

        for (Map<String, Object> map : maps) {
            //先将教师号和课程号添加至列表以便查找
            courseNos.add((String) map.get("courseNo"));
            teacherNos.add((String) map.get("teacherNo"));

            //将上课时间格式化
            String allTime = (String) map.get("time");
            String[] times = allTime.split(",");

            Map<String, Object> timeMap = new LinkedHashMap<>();
            int i = 0;
            for (String time : times) {
                Map<String, String> classes = new LinkedHashMap<>();

                String[] s = time.split("_");
                classes.put("day", s[0]);

                String[] numClass = s[1].split("-");
                classes.put("clock", "第" + numClass[0] + "节-第" + numClass[1] + "节");

                timeMap.put("第" + (++i) + "次", classes);
            }

            map.put("time", timeMap);
        }

        Map<String, Course> courses = courseMapper.getCoursesForMapByIds(courseNos);
        Map<String, Map<String, String>> usernameMap = userMapper.selectUserNamesForMapByIds(teacherNos);

        for (Map<String, Object> map : maps) {

            String courseNo = (String) map.get("courseNo");

            if (courses.get(courseNo) != null) {
                map.put("courseName", courses.get(courseNo).getCourseName());
                map.put("courseHour", courses.get(courseNo).getHour());
                map.put("credit", courses.get(courseNo).getCredit());
            }

            String teacherNo = (String) map.get("teacherNo");
            if (usernameMap.get(teacherNo) != null) {
                map.put("teacherName", usernameMap.get(teacherNo).get("username"));
            }

        }
        return maps;
    }
}
