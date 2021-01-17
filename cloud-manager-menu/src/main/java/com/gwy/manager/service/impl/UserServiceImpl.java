package com.gwy.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gwy.manager.domain.constant.PageHelperConst;
import com.gwy.manager.domain.constant.RoleName;
import com.gwy.manager.domain.dto.ResultVO;
import com.gwy.manager.domain.entity.*;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.domain.enums.ResponseStatus;
import com.gwy.manager.domain.enums.UserOption;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.UserMapper;
import com.gwy.manager.mapper.UserRoleMapper;
import com.gwy.manager.service.UserService;
import com.gwy.manager.util.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Tracy
 * @date 2020/11/24 23:50
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VRCodeUtil vrCodeUtil;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;



    @Override
    public ResultVO getUserById(String adminNo, String userId) {

        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            User adminUser = userMapper.selectByPrimaryKey(adminNo);
            if (adminUser == null || !adminUser.getAvailableDeptIds().contains(user.getDeptId())) {
                return ResultVoUtil.error(ResponseDataMsg.PermissionDeny.getMsg());
            } else {
                return ResultVoUtil.success(BeanUtil.beanToMap(user));
            }
        }
    }

    @Override
    public ResultVO getUserById(String userId) {

        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        }

        return ResultVoUtil.success(BeanUtil.beanToMap(user));
    }

    @Override
    public ResultVO getUserMatchNameInDept(String adminNo, String deptId, String name) {

        ResultVO resultVO;

        //判断管理员用户是否存在
        User adminUser = userMapper.selectByPrimaryKey(adminNo);
        if (adminUser == null || !adminUser.getAvailableDeptIds().contains(deptId)) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.PermissionDeny.getMsg());
            return resultVO;
        }

        List<User> users = userMapper.getUsersMatchNameInDept(deptId, name);
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

        int i = userMapper.updateByPrimaryKey(user);
        if (i == 0) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO = ResultVoUtil.success(ResponseDataMsg.Success.getMsg());
        }

        return resultVO;
    }
    @Override
    public ResultVO getAllUsers(int pageNum, int pageSize) {

        ResultVO resultVO;

        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.selectAll();
        if (CollectionUtils.isEmpty(users)) {
            resultVO =ResultVoUtil.error(com.gwy.manager.enums.ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVoUtil.success(PageHelperUtil.pageInfoToMap(new PageInfo<>(users)));
        }

        return resultVO;
    }
}
