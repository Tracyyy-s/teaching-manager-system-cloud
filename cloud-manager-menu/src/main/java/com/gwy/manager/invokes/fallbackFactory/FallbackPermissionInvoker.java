package com.gwy.manager.invokes.fallbackFactory;

import com.gwy.manager.domain.dto.ResultVO;
import com.gwy.manager.domain.entity.Permission;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.invokes.PermissionInvoker;
import com.gwy.manager.util.ResultVoUtil;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class FallbackPermissionInvoker implements FallbackFactory<PermissionInvoker> {
    @Override
    public PermissionInvoker create(Throwable throwable) {
        return new PermissionInvoker(){

            @Override
            public int deleteByPrimaryKey(Integer permissionId) {
                return 0;
            }

            @Override
            public int insert(Permission record) {
                return 0;
            }

            @Override
            public Permission selectByPrimaryKey(Integer permissionId) {
                return null;
            }

            @Override
            public List<Permission> selectAll() {
                return null;
            }

            @Override
            public int updateByPrimaryKey(Permission record) {
                return 0;
            }

            @Override
            public List<Permission> selectByIds(List<Integer> permissionIds) {
                return null;
            }

            @Override
            public List<Permission> selectByUserId(String userId) {
                return null;
            }

            /**
             *
             * @param roleIds
             * @return
             */
            @Override
            public List<Permission> selectByRoleIds(@RequestBody List<Integer> roleIds){
                List<Permission> l = new ArrayList<Permission>();
                Permission p = new Permission();
                p.setPermissionId(0);
                p.setPermissionName("服务被关闭");
                l.add(p);
                return l;
            }

            @Override
            public List<Permission> selectByRoleId(Integer roleId) {
                return null;
            }

            @Override
            public Integer selectIdByName(String permissionName) {
                return null;
            }

            @Override
            public Map<Integer, Permission> selectAllForMap() {
                return null;
            }
        };
    }
}