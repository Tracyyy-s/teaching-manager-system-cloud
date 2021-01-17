package com.gwy.manager.controller;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.domain.dto.ResultVO;
import com.gwy.manager.domain.entity.Role;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.domain.enums.ResponseStatus;
import com.gwy.manager.service.RoleService;
import com.gwy.manager.service.impl.PermissionServiceImpl;
import com.gwy.manager.service.impl.UserRoleServiceImpl;
import com.gwy.manager.service.impl.UserServiceImpl;
import com.gwy.manager.domain.enums.*;

import com.gwy.manager.util.DateUtilCustom;
import com.gwy.manager.util.ResultVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("menuBaseController")
public class MenuBaseController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleServiceImpl userRoleService;
    @Autowired
    private PermissionServiceImpl permissionService;

    /*-------------------操作-用户-表---------------------*/

    /**
     * root用户获得所有用户
     * @return 结果集
     */
    @PostMapping("/getAllUsers")
    public String getAllUsers() {
//
        return JSONObject.toJSONStringWithDateFormat(userService.getAllUsers(), DateUtilCustom.DATE_PATTERN);
    }

    /*-------------------操作-用户-角色-表---------------------*/
    /**
     * 获得某用户的所有角色
     * @param map 请求体
     * @return 结果集
     */
    @PostMapping("/getUserRoles")
    public ResultVO getUserRole(@RequestBody Map<String, String> map) {

        String userId = map.get("userId");
        return userRoleService.getUserRoles(userId);
    }
    /**
     * 修改用户的角色
     * @param map 请求体
     * @return 结果集
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/updateUserRole")
    public ResultVO updateUserRole(@RequestBody Map<String, Object> map) {

        List<Integer> roleIds;
        String userId = (String) map.get("userId");
        try {
            roleIds = (List<Integer>) map.get("data");

        } catch (Exception e) {
            return ResultVoUtil.error(ResponseDataMsg.BadRequest.getMsg());
        }
        return userRoleService.updateUserRole(userId, roleIds);
    }

    /*-------------------操作角色表---------------------*/

    /**
     * 获得所有角色信息列表
     * @return 结果集
     */
    @PostMapping("/getAllRoles")
    public ResultVO getAllRoles() {

        return roleService.getAllRoles();
    }
    /**
     * 添加新角色
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/addRole")
    public ResultVO addRole(@RequestBody Map<String, String> map) {

        String roleName = map.get("roleName");
        Role role = new Role();
        role.setRoleName(roleName);
        return roleService.addRole(role);
    }
    /**
     * 删除指定角色
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/deleteRole")
    public ResultVO deleteRole(@RequestBody Map<String, Integer> map) {

        Integer roleId = map.get("roleId");
        return roleService.deleteRole(roleId);
    }

    /*------------------操作-角色-权限-表---------------------------------*/
    /**
     * 通过角色id获得权限
     *
     * @param map 请求体
     * @return 结果集
     */
    @PostMapping("/getPermissionsByRoleId")
    public ResultVO getPermissionsByRoleId(@RequestBody Map<String, String> map) {

        int roleId;
        try {
            roleId = Integer.parseInt(map.get("roleId"));
        } catch (Exception e) {
            return ResultVoUtil.error(ResponseDataMsg.Fail.getMsg());
        }
        return permissionService.getPermissionsByRoleId(roleId);
    }
    /**
     * root用户修改角色权限
     *
     * @param map 请求体
     * @return 结果集
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/updateRolePermission")
    public ResultVO updateRolePermission(@RequestBody Map<String, Object> map) {

        Integer roleId = Integer.parseInt((String) map.get("roleId"));
        List<Integer> permissionIds;
        try {
            permissionIds = (List<Integer>) map.get("data");
        } catch (NumberFormatException e) {
            return ResultVoUtil.error(ResponseDataMsg.BadRequest.getMsg());
        }
        return permissionService.updateRolePermission(roleId, permissionIds);
    }


    /*-------------------操作权限表---------------------*/
    /**
     * 获得所有权限信息列表
     * @return 结果集
     */
    @PostMapping("/getAllPermissions")
    public ResultVO getAllPermissions() {
        return permissionService.getAllPermissions();
    }
    /*------------------联级操作-通过用户查询其所有权限------------------*/
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
        System.out.println((List<Role>)rolesOfUser.getData());
        if (((List<Role>)rolesOfUser.getData()).get(0).getRoleId() == -1){
            System.out.println("您请求的服务被临时关闭了");
            rolesOfUser.setMessage("您请求的服务被临时关闭了,请稍后访问");
            return rolesOfUser ;
        }
        if (rolesOfUser.getResultCode().equals(ResponseStatus.SUCCESS.getCode())) {

            List<Role> roles = (List<Role>) rolesOfUser.getData();

            List<Integer> roleIds = new ArrayList<>();
            for (Role role : roles) {
                roleIds.add(role.getRoleId());
            }
            return permissionService.getPermissionsByRoleIds(roleIds);
        }else{
            return rolesOfUser;
        }
    }
}
