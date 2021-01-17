package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.User;
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

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
@RequestMapping("UserMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",configuration = FeignClientProperties.FeignClientConfiguration.class, contextId = "104")
@Qualifier("menuUserInvoker")
public interface UserInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("userId") String userId);

    @PostMapping("insert")
    int insert(@RequestBody User record);

    @PostMapping("selectByPrimaryKey")
    User selectByPrimaryKey(@RequestParam("userId") String userId);

    @PostMapping("selectAll")
    List<User> selectAll();

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody User record);

    @PostMapping("selectUsersByDeptId")
    List<User> selectUsersByDeptId(@RequestParam("deptId") String deptId);

    @PostMapping("selectUserNamesByIds")
    List<String> selectUserNamesByIds(@RequestBody List<String> userIds);

    @PostMapping("selectUserNamesForMapByIds")
    Map<String, Map<String, String>> selectUserNamesForMapByIds(@RequestBody List<String> userIds);

    @PostMapping("insertUsersByBatch")
    int insertUsersByBatch(@RequestBody List<User> users);

    @PostMapping("updatePassword")
    int updatePassword(@RequestParam("userId") String userId,
                       @RequestParam("password") String password);

    @PostMapping("getUsersMatchNameInDept")
    List<User> getUsersMatchNameInDept(@RequestParam("deptId") String deptId,
                                       @RequestParam("name") String name);

    @PostMapping("selectByUsername")
    User selectByUsername(@RequestParam("username") String username);

    @PostMapping("selectUsersByRoleName")
    List<User> selectUsersByRoleName(@RequestParam("roleName") String roleName);

    @PostMapping("updateAvailableDeptIds")
    int updateAvailableDeptIds(@RequestParam("userId") String userId,
                               @RequestParam("deptIds") String deptIds);
}