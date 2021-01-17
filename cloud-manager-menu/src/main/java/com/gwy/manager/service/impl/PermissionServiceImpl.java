package com.gwy.manager.service.impl;

import com.gwy.manager.domain.dto.ResultVO;
import com.gwy.manager.domain.entity.Permission;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.invokes.PermissionInvoker;
import com.gwy.manager.invokes.RolePermissionInvoker;
import com.gwy.manager.service.PermissionService;
import com.gwy.manager.util.ResultVoUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Set;

/**
 * @author Tracy
 * @date 2020/11/30 8:59
 */
@CacheConfig(cacheNames = "permissions")
@Service
public class PermissionServiceImpl implements PermissionService {

    private static final String TOKEN_PREFIX = "eyJ*";

    @Qualifier("menuPermissionInvoker")
    @Autowired
    private PermissionInvoker permissionInvoker;

    @Qualifier("menuRolePermissionInvoker")
    @Autowired
    private RolePermissionInvoker rolePermissionInvoker;

    @Autowired
    private StringRedisTemplate redisTemplate;


    /*---根据roleId列表去筛选权限---*/
    @Cacheable(keyGenerator = "byRoleIds")
    @Override
    public ResultVO getPermissionsByRoleIds(List<Integer> roleIds) {

        ResultVO resultVO;

        List<Permission> permissions = permissionInvoker.selectByRoleIds(roleIds);
        System.out.println(permissions);
        if (CollectionUtils.isEmpty(permissions)) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            if (permissions.get(0).getPermissionId() == 0){
                resultVO = ResultVoUtil.error("您请求的服务被临时关闭了,请稍后访问");
            }else{
                resultVO = ResultVoUtil.success(permissions);
            }
        }
        return resultVO;
    }

    @Cacheable(key = "'all'")
    @Override
    public ResultVO getAllPermissions() {

        ResultVO resultVO;

        List<Permission> permissions = permissionInvoker.selectAll();
        if (CollectionUtils.isEmpty(permissions)) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVoUtil.success(permissions);
        }
        return resultVO;
    }

    @Cacheable(keyGenerator = "byRoleId")
    @Override
    public ResultVO getPermissionsByRoleId(Integer roleId) {
        ResultVO resultVO;

        List<Permission> permissions = permissionInvoker.selectByRoleId(roleId);
        if (CollectionUtils.isEmpty(permissions)) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVoUtil.success(permissions);
        }

        return resultVO;
    }

    @Cacheable(keyGenerator = "byIds")
    @Override
    public ResultVO getPermissionsByIds(List<Integer> permissionIds) {

        ResultVO resultVO;

        List<Permission> permissions = permissionInvoker.selectByIds(permissionIds);
        if (CollectionUtils.isEmpty(permissions)) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVoUtil.success(permissions);
        }

        return resultVO;
    }

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public ResultVO updateRolePermission(Integer roleId, List<Integer> permissionIds) {

        try {
            int i = rolePermissionInvoker.deleteByRoleId(roleId);
            if (i == 0) {
                return ResultVoUtil.error(ResponseDataMsg.Fail.getMsg());
            }

            int j = rolePermissionInvoker.insertBatch(roleId, permissionIds);
            if (j == 0) {
                return ResultVoUtil.error(ResponseDataMsg.Fail.getMsg());
            } else {
                return ResultVoUtil.success(ResponseDataMsg.Success.getMsg());
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        //删除所有的token信息
        Set<String> keys = redisTemplate.keys(TOKEN_PREFIX);
        if (keys != null) {
            redisTemplate.delete(keys);
        }

        return ResultVoUtil.error(ResponseDataMsg.Fail.getMsg());
    }
}
