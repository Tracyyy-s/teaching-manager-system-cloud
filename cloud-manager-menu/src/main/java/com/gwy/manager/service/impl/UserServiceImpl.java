package com.gwy.manager.service.impl;

import com.gwy.manager.domain.dto.ResultVO;
import com.gwy.manager.domain.entity.*;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.invokes.RoleInvoker;
import com.gwy.manager.invokes.UserInvoker;
import com.gwy.manager.invokes.UserRoleInvoker;
import com.gwy.manager.service.UserService;
import com.gwy.manager.util.*;
import com.gwy.manager.util.ResultVoUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Tracy
 * @date 2020/11/24 23:50
 */
@Service
public class UserServiceImpl implements UserService {

    @Qualifier("menuUserInvoker")
    @Autowired
    private UserInvoker userInvoker;

    @Autowired
    private VRCodeUtil vrCodeUtil;

    @Qualifier("menuUserRoleInvoker")
    @Autowired
    private UserRoleInvoker userRoleInvoker;

    @Qualifier("menuRoleInvoker")
    @Autowired
    private RoleInvoker roleInvoker;



    @Override
    public ResultVO getUserById(String adminNo, String userId) {

        User user = userInvoker.selectByPrimaryKey(userId);
       if (user == null) {
            return ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            User adminUser = userInvoker.selectByPrimaryKey(adminNo);
            if (adminUser == null || !adminUser.getAvailableDeptIds().contains(user.getDeptId())) {
                return ResultVoUtil.error(ResponseDataMsg.PermissionDeny.getMsg());
            } else {
                return ResultVoUtil.success(BeanUtil.beanToMap(user));
            }
        }
    }

    @Override
    public ResultVO getUserById(String userId) {

        User user = userInvoker.selectByPrimaryKey(userId);
        if (user == null) {
            return ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        }

        return ResultVoUtil.success(BeanUtil.beanToMap(user));
    }

    @Override
    public ResultVO getUserMatchNameInDept(String adminNo, String deptId, String name) {

        ResultVO resultVO;

        //判断管理员用户是否存在
        User adminUser = userInvoker.selectByPrimaryKey(adminNo);
        if (adminUser == null || !adminUser.getAvailableDeptIds().contains(deptId)) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.PermissionDeny.getMsg());
            return resultVO;
        }

        List<User> users = userInvoker.getUsersMatchNameInDept(deptId, name);
        if (CollectionUtils.isEmpty(users)) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVoUtil.success(BeanUtil.beansToList(users));
        }

        return resultVO;
    }

    @Override
    public ResultVO updateUser(User user) {

        ResultVO resultVO;

        int i = userInvoker.updateByPrimaryKey(user);
        if (i == 0) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO = ResultVoUtil.success(ResponseDataMsg.Success.getMsg());
        }

        return resultVO;
    }

    public ResultVO getAllUsers() {
        ResultVO resultVO;

        List<User> users = userInvoker.selectAll();
        if (CollectionUtils.isEmpty(users)) {
            resultVO =ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVoUtil.success(BeanUtil.beansToList(users));
        }

        return resultVO;
    }
}
