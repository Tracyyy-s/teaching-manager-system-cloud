package com.gwy.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gwy.manager.domain.constant.RoleName;
import com.gwy.manager.domain.dto.ResultVO;
import com.gwy.manager.domain.entity.Student;
import com.gwy.manager.domain.entity.User;
import com.gwy.manager.domain.entity.UserRole;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.domain.enums.ResponseStatus;
import com.gwy.manager.domain.enums.UserOption;
import com.gwy.manager.mapper.DeptMapper;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.StudentMapper;
import com.gwy.manager.mapper.UserMapper;
import com.gwy.manager.mapper.UserRoleMapper;
import com.gwy.manager.service.StudentService;
import com.gwy.manager.util.BeanUtil;
import com.gwy.manager.util.PageHelperUtil;
import com.gwy.manager.util.ResultVoUtil;
import com.gwy.manager.util.file.ImportExcelFileUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/3 16:12
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private VrCodeServiceImpl vrCodeServiceImpl;

    @Autowired
    private ImportExcelFileUtil importExcelFileUtil;

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public int addStudent(Student student) {
        return studentMapper.insert(student);
    }

    private Student getStudent(String studentNo) {
        return studentMapper.selectByPrimaryKey(studentNo);
    }

    @Override
    public ResultVO getStudentInfo(String studentNo) {

        ResultVO resultVO;

        Student student = this.getStudent(studentNo);
        if (student == null) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVoUtil.success(BeanUtil.beanToMap(student));
        }

        return resultVO;
    }

    @Override
    public ResultVO getStudentInfoByAdmin(String adminNo, String studentNo) {
        ResultVO resultVO;

        Student student = this.getStudent(studentNo);
        if (student == null) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            //获得管理员角色用户
            User adminUser = userMapper.selectByPrimaryKey(adminNo);
            //若学生学院不在管理员可管理学院内
            if (adminUser == null || !adminUser.getAvailableDeptIds().contains(student.getDeptId())) {
                resultVO = ResultVoUtil.error(ResponseDataMsg.PermissionDeny.getMsg());
            } else {
                resultVO = ResultVoUtil.success(BeanUtil.beanToMap(student));
            }
        }

        return resultVO;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public ResultVO updatePassword(String studentNo, String password, String vrCode) {

        return vrCodeServiceImpl.updatePasswordByCode(UserOption.STUDENT.getUserType(), studentNo, password, vrCode);
    }

    @Override
    public ResultVO updateStudent(Student student) {

        ResultVO resultVO;

        int i = studentMapper.updateByPrimaryKey(student);
        if (i == 0) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO = ResultVoUtil.success(ResponseDataMsg.Success.getMsg());
        }

        return resultVO;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public ResultVO sendCode(String studentNo) {
        return vrCodeServiceImpl.sendCode(studentNo, RoleName.STUDENT);
    }

    @Override
    public int addStudentBatch(List<Student> students) {
        return studentMapper.insertStudentBatch(students);
    }

    @Override
    public ResultVO getStudentsByDept(int pageNum, int pageSize, String deptId) {

        ResultVO resultVO;

        PageHelper.startPage(pageNum, pageSize);
        List<Student> students = studentMapper.selectStudentsByDept(deptId);
        if (CollectionUtils.isEmpty(students)) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVoUtil.success(PageHelperUtil.pageInfoToMap(new PageInfo<>(students)));
        }
        return resultVO;
    }

    @Override
    public ResultVO getStudentByClass(String classId) {
        ResultVO resultVO;

        List<Student> students = studentMapper.selectStudentsByClass(classId);
        if (students.size() == 0) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVoUtil.success(BeanUtil.beansToList(students));
        }
        return resultVO;
    }

    @Override
    public ResultVO getStudentsMatchName(String adminNo, String deptId, String name) {

        ResultVO resultVO;

        User adminUser = userMapper.selectByPrimaryKey(adminNo);
        if (adminUser == null || !adminUser.getAvailableDeptIds().contains(deptId)) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.PermissionDeny.getMsg());
            return resultVO;
        }

        List<Student> students = studentMapper.selectStudentsMatchName(deptId, name);
        if (CollectionUtils.isEmpty(students)) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVoUtil.success(BeanUtil.beansToList(students));
        }

        return resultVO;
    }

    @Override
    public ResultVO getAllStudents(int pageNum, int pageSize) {
        ResultVO resultVO;

        PageHelper.startPage(pageNum, pageSize);
        List<Student> students = studentMapper.selectAll();
        if (CollectionUtils.isEmpty(students)) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVoUtil.success(PageHelperUtil.pageInfoToMap(new PageInfo<>(students)));
        }

        return resultVO;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ResultVO importStudentsByFile(String deptId, String headerType, MultipartFile file) {

        ResultVO resultVO = importExcelFileUtil.importBeansByFile(deptId, headerType, file);

        if (resultVO.getResultCode().equals(ResponseStatus.SUCCESS.getCode())) {
            Map<String, Object> map = (Map<String, Object>) resultVO.getData();

            List<Student> students = new ArrayList<>();

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Map<String, Object> dataMap = (Map<String, Object>) entry.getValue();
                students.addAll((List<Student>) dataMap.get("dataList"));
            }

            Integer studentRoleId = roleMapper.selectRoleIdByName(RoleName.STUDENT);

            //存储用户id, 增加用户-角色
            List<UserRole> userRoles = new ArrayList<>();
            for (Student student : students) {
                UserRole userRole = new UserRole();
                userRole.setUserId(student.getStudentNo());
                userRole.setRoleId(studentRoleId);

                userRoles.add(userRole);
            }

            int i, j;
            try {
                i = studentMapper.insertStudentBatch(students);
                j = userRoleMapper.insertByBatch(userRoles);
            } catch (Exception e) {
                resultVO = ResultVoUtil.error("Exception in Executing");
                return resultVO;
            }
            if (i == 0 || j == 0) {
                resultVO = ResultVoUtil.error(ResponseDataMsg.Fail.getMsg());
            } else {
                resultVO = ResultVoUtil.success(ResponseDataMsg.Success.getMsg());
            }
        }

        return resultVO;
    }
}
