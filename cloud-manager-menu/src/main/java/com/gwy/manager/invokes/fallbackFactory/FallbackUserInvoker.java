package com.gwy.manager.invokes.fallbackFactory;

import com.gwy.manager.domain.entity.User;
import com.gwy.manager.invokes.RoleInvoker;
import com.gwy.manager.invokes.UserInvoker;
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
import java.util.Map;


@Component
public class FallbackUserInvoker  implements FallbackFactory<UserInvoker> {


    @Override
    public UserInvoker create(Throwable throwable) {
        return new UserInvoker() {
            @Override
            public int deleteByPrimaryKey(String userId) {
                return 0;
            }

            @Override
            public int insert(User record) {
                return 0;
            }

            @Override
            public User selectByPrimaryKey(String userId) {
                return null;
            }

            @Override
            public List<User> selectAll() {
                return null;
            }

            @Override
            public int updateByPrimaryKey(User record) {
                return 0;
            }

            @Override
            public List<User> selectUsersByDeptId(String deptId) {
                return null;
            }

            @Override
            public List<String> selectUserNamesByIds(List<String> userIds) {
                return null;
            }

            @Override
            public Map<String, Map<String, String>> selectUserNamesForMapByIds(List<String> userIds) {
                return null;
            }

            @Override
            public int insertUsersByBatch(List<User> users) {
                return 0;
            }

            @Override
            public int updatePassword(String userId, String password) {
                return 0;
            }

            @Override
            public List<User> getUsersMatchNameInDept(String deptId, String name) {
                return null;
            }

            @Override
            public User selectByUsername(String username) {
                return null;
            }

            @Override
            public List<User> selectUsersByRoleName(String roleName) {
                return null;
            }

            @Override
            public int updateAvailableDeptIds(String userId, String deptIds) {
                return 0;
            }
        };
    }
}