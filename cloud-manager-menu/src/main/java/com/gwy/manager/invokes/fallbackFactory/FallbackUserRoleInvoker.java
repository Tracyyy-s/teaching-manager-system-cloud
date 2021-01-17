package com.gwy.manager.invokes.fallbackFactory;

import com.gwy.manager.domain.entity.UserRole;
import com.gwy.manager.invokes.RoleInvoker;
import com.gwy.manager.invokes.UserRoleInvoker;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
public class FallbackUserRoleInvoker  implements FallbackFactory<UserRoleInvoker> {

    @Override
    public UserRoleInvoker create(Throwable throwable) {
        return  new UserRoleInvoker() {
            @Override
            public int deleteByPrimaryKey(String userId, Integer roleId) {
                return 0;
            }

            @Override
            public int insert(UserRole record) {
                return 0;
            }

            @Override
            public int insertByBatch(List<UserRole> userRoles) {
                return 0;
            }

            @Override
            public List<UserRole> selectAll() {
                return null;
            }

            @Override
            public int deleteRoleOfUser(String userId) {
                return 0;
            }

            @Override
            public int addRolesForUser(String userId, List<Integer> roleIds) {
                return 0;
            }
        };
    }
}