package com.gwy.manager.service.impl;

import com.gwy.manager.domain.constant.RoleName;
import com.gwy.manager.domain.dto.ResultVO;
import com.gwy.manager.domain.entity.Role;
import com.gwy.manager.domain.entity.RolePermission;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.invokes.PermissionInvoker;
import com.gwy.manager.invokes.RoleInvoker;
import com.gwy.manager.invokes.RolePermissionInvoker;
import com.gwy.manager.service.RoleService;
import com.gwy.manager.util.ResultVoUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/30 12:54
 */
@CacheConfig(cacheNames = "roles")
@Service
public class RoleServiceImpl implements RoleService {

    private static final String DEFAULT_PERMISSION = "teacherCard";

    @Autowired
    private RoleInvoker roleMapper;

    @Autowired
    private RolePermissionInvoker rolePermissionMapper;

    @Autowired
    private PermissionInvoker permissionMapper;

    @Cacheable(key = "'all'")
    @Override
    public ResultVO getAllRoles() {

        ResultVO resultVO;

        List<Role> roles = roleMapper.selectAll();
        if (CollectionUtils.isEmpty(roles)) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            roles.removeIf(role -> role.getRoleName().equals(RoleName.STUDENT) || role.getRoleName().equals(RoleName.ROOT));
            resultVO = ResultVoUtil.success(roles);
        }

        return resultVO;
    }

    @Transactional(rollbackFor = {Exception.class})
    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Override
    public ResultVO addRole(Role role) {

        Integer id = roleMapper.selectRoleIdByName(role.getRoleName());
        if (id != null) {
            return ResultVoUtil.error("Already Exists");
        }

        try {
            int i = roleMapper.insert(role);
            if (i == 0) {
                return ResultVoUtil.error(ResponseDataMsg.Fail.getMsg());
            }

            RolePermission rolePermission = new RolePermission();
            Integer roleId = roleMapper.selectRoleIdByName(role.getRoleName());
            Integer permissionId = permissionMapper.selectIdByName(DEFAULT_PERMISSION);
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);

            //添加角色默认权限为资料卡片
            int j = rolePermissionMapper.insert(rolePermission);
            if (j == 0) {
                return ResultVoUtil.error(ResponseDataMsg.Fail.getMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return ResultVoUtil.success(ResponseDataMsg.Success.getMsg());
    }

    @Transactional(rollbackFor = {Exception.class})
    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Override
    public ResultVO deleteRole(Integer roleId) {

        try {
            int i = roleMapper.deleteByPrimaryKey(roleId);
            int j = rolePermissionMapper.deleteByRoleId(roleId);

            if (i == 0 || j == 0) {
                return ResultVoUtil.error(ResponseDataMsg.Fail.getMsg());
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultVoUtil.error(ResponseDataMsg.Fail.getMsg());
        }

        return ResultVoUtil.success(ResponseDataMsg.Success.getMsg());
    }
}
