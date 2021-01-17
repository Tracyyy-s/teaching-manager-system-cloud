package com.gwy.manager.invokes.fallbackFactory;

import com.gwy.manager.domain.entity.RolePermission;
import com.gwy.manager.invokes.RoleInvoker;
import com.gwy.manager.invokes.RolePermissionInvoker;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
public class FallbackRolePermissionInvoker  implements FallbackFactory<RolePermissionInvoker> {


    @Override
    public RolePermissionInvoker create(Throwable throwable) {
        return new RolePermissionInvoker() {
            @Override
            public int deleteByPrimaryKey(Integer roleId, Integer permissionId) {
                return 0;
            }

            @Override
            public int insert(RolePermission record) {
                return 0;
            }

            @Override
            public List<RolePermission> selectAll() {
                return null;
            }

            @Override
            public List<Integer> selectPermissionIdsByRoleIds(List<Integer> roleIds) {
                return null;
            }

            @Override
            public List<Integer> selectPermissionIdsByRoleId(Integer roleId) {
                return null;
            }

            @Override
            public int deleteByRoleId(Integer roleId) {
                return 0;
            }

            @Override
            public int insertBatch(Integer roleId, List<Integer> permissionIds) {
                return 0;
            }
        };
    }
}