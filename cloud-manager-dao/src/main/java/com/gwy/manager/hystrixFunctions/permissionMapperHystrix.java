package com.gwy.manager.hystrixFunctions;

import com.gwy.manager.domain.entity.Permission;

import java.util.ArrayList;
import java.util.List;

public class permissionMapperHystrix {
    public List<Permission> SelectByRoleIdHystrix(){
        List<Permission> l = new ArrayList<Permission>();
        Permission per = new Permission();
        per.setPermissionId(1);
        per.setPermissionName("服务调用失败，请稍后再试");
        l.add(per);
        return l;
    }
}
