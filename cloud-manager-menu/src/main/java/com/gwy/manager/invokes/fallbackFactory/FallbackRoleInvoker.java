package com.gwy.manager.invokes.fallbackFactory;

import com.gwy.manager.domain.entity.Role;
import com.gwy.manager.invokes.RoleInvoker;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Component
public class FallbackRoleInvoker implements FallbackFactory<RoleInvoker> {

    @Override
    public RoleInvoker create(Throwable throwable) {
        return new RoleInvoker() {
            @Override
            public int deleteByPrimaryKey(Integer roleId) {
                return 0;
            }

            @Override
            public int insert(Role record) {
                return 0;
            }

            @Override
            public Role selectByPrimaryKey(Integer roleId) {
                return null;
            }

            @Override
            public List<Role> selectAll() {
                return null;
            }

            @Override
            public int updateByPrimaryKey(Role record) {
                return 0;
            }

            @Override
            public String selectRoleNameById(Integer roleId) {
                return null;
            }

            @Override
            public Integer selectRoleIdByName(String roleName) {
                return null;
            }

            @Override
            public List<Integer> selectRoleIdsByNames(List<String> roleNames) {
                return null;
            }

            /*------------获取用户角色--------------*/
            @Override
            public List<Role> selectByUserId(String userId) {
                List<Role> l = new ArrayList<Role>();
                Role r = new Role();
                r.setRoleId(-1);
                r.setRoleName("服务被关闭了");
                l.add(r);
                return l;
            }
        };
    }
}