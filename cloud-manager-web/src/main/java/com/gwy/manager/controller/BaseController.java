package com.gwy.manager.controller;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.domain.dto.ResultVO;
import com.gwy.manager.domain.entity.Role;
import com.gwy.manager.domain.enums.ResponseStatus;
import com.gwy.manager.service.impl.DeptServiceImpl;
import com.gwy.manager.service.impl.PermissionServiceImpl;
import com.gwy.manager.service.impl.StudentServiceImpl;
import com.gwy.manager.service.impl.TermServiceImpl;
import com.gwy.manager.service.impl.UserRoleServiceImpl;
import com.gwy.manager.service.impl.UserServiceImpl;
import com.gwy.manager.service.impl.VrCodeServiceImpl;
import com.gwy.manager.util.DateUtilCustom;
import com.gwy.manager.util.ResultVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/5 13:57
 */
@CrossOrigin
@RestController
public class BaseController {

    @Autowired
    private TermServiceImpl termService;

    @Autowired
    private UserRoleServiceImpl userRoleService ;

    @Autowired
    private PermissionServiceImpl permissionService;

    @Autowired
    private DeptServiceImpl deptService;

    @Autowired
    private VrCodeServiceImpl vrCodeServiceImpl;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/")
    public ResultVO root() {
        return ResultVoUtil.success("Welcome to Teaching Manager System.");
    }

    @GetMapping("/login")
    public ResultVO login() {
        return ResultVoUtil.error("Sorry, Not Found Resources");
    }

    /**
     * 忘记密码时，向绑定邮箱发送验证码
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/sendCode")
    public ResultVO sendCode(@RequestBody Map<String, String> map) {

        String userId = map.get("userId");
        return vrCodeServiceImpl.sendCode(userId, null);
    }

    /**
     * 忘记密码时修改密码操作
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/updatePassword")
    public ResultVO updatePassword(@RequestBody Map<String, String> map) {

        String userId = map.get("userId");
        String code = map.get("code");
        String password = map.get("password");

        ResultVO resultVO = studentService.getStudentInfo(userId);
        if (resultVO.getResultCode().equals(ResponseStatus.FAIL.getCode())) {
            
            return userService.updatePassword(userId, password, code);
        }

        return studentService.updatePassword(userId, password, code);
    }

    /**
     * 获得所有的学期信息
     * @return 返回结果
     */
    @PostMapping("/getTerms")
    public String getTerms() {
        return JSONObject.toJSONStringWithDateFormat(termService.getTerms(), DateUtilCustom.DATE_PATTERN);
    }

    /**
     * 获得当前学期信息
     * @return  结果集
     */
    @PostMapping("/getCurrentTerm")
    public String getCurrentTerm() {
        return JSONObject.toJSONStringWithDateFormat(termService.getCurrentTerm(), DateUtilCustom.DATE_PATTERN);
    }

    /**
     * 获得用户所有权限
     * @param map   请求体
     * @return  结果集
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/getUserPermissions")
    public ResultVO getUserPermissions(@RequestBody Map<String, String> map) {

        String account = map.get("account");
        ResultVO rolesOfUser = userRoleService.getUserRoles(account);

        if (rolesOfUser.getResultCode().equals(ResponseStatus.SUCCESS.getCode())) {

            List<Role> roles = (List<Role>) rolesOfUser.getData();

            List<Integer> roleIds = new ArrayList<>();
            for (Role role : roles) {
                roleIds.add(role.getRoleId());
            }

            return permissionService.getPermissionsByRoleIds(roleIds);
        }

        return rolesOfUser;
    }

    @PostMapping("/getUserPermissionsVX")
    public ResultVO getUserPermissionsVX() {
        return null;
    }

    /**
     * 根据学院id获得学院
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("getDeptById")
    public ResultVO getDeptById(@RequestBody Map<String, String> map) {

        String deptId = map.get("deptId");
        return deptService.getDeptById(deptId);
    }
}
