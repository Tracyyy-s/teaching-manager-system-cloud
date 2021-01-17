package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
@RequestMapping("UserMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",configuration = FeignClientProperties.FeignClientConfiguration.class, contextId = "60")
@Qualifier("webUserInvoker")
public interface UserInvoker {

    @RequestMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("userId") String userId);

    @RequestMapping("insert")
    int insert(@RequestBody User record);

    @RequestMapping("selectByPrimaryKey")
    User selectByPrimaryKey(@RequestParam("userId") String userId);

    @RequestMapping("selectAll")
    List<User> selectAll();

    @RequestMapping("updateByPrimaryKey")
    int updateByPrimaryKey(@RequestBody User record);

    @RequestMapping("selectUsersByDeptId")
    List<User> selectUsersByDeptId(@RequestParam("deptId") String deptId);

    @RequestMapping("selectUserNamesByIds")
    List<String> selectUserNamesByIds(@RequestBody List<String> userIds);

    @RequestMapping("selectUserNamesForMapByIds")
    Map<String, Map<String, String>> selectUserNamesForMapByIds(@RequestBody List<String> userIds);

    @RequestMapping("insertUsersByBatch")
    int insertUsersByBatch(@RequestBody List<User> users);

    @RequestMapping("updatePassword")
    int updatePassword(@RequestParam("userId") String userId,
                       @RequestParam("password") String password);

    @RequestMapping("getUsersMatchNameInDept")
    List<User> getUsersMatchNameInDept(@RequestParam("deptId") String deptId,
                                       @RequestParam("name") String name);

    @RequestMapping("selectByUsername")
    User selectByUsername(@RequestParam("username") String username);

    @RequestMapping("selectUsersByRoleName")
    List<User> selectUsersByRoleName(@RequestParam("roleName") String roleName);

    @RequestMapping("updateAvailableDeptIds")
    int updateAvailableDeptIds(@RequestParam("userId") String userId,
                               @RequestParam("deptIds") String deptIds);
}