package com.gwy.manager.invokes;

import com.gwy.manager.domain.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@RestController
@RequestMapping("UserMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER")
public interface UserInvoker {

    @PostMapping("deleteByPrimaryKey")
    int deleteByPrimaryKey(String userId);

    @PostMapping("insert")
    int insert(User record);

    @PostMapping("selectByPrimaryKey")
    User selectByPrimaryKey(String userId);

    @PostMapping("selectAll")
    List<User> selectAll();

    @PostMapping("updateByPrimaryKey")
    int updateByPrimaryKey(User record);

    @PostMapping("selectUsersByDeptId")
    List<User> selectUsersByDeptId(String deptId);

    @PostMapping("selectUserNamesByIds")
    List<String> selectUserNamesByIds(@Param("userIds") List<String> userIds);

    @PostMapping("selectUserNamesForMapByIds")
    Map<String, Map<String, String>> selectUserNamesForMapByIds(@Param("userIds") List<String> userIds);

    @PostMapping("insertUsersByBatch")
    int insertUsersByBatch(@Param("users") List<User> users);

    @PostMapping("updatePassword")
    int updatePassword(@Param("userId") String userId,
                       @Param("password") String password);

    @PostMapping("getUsersMatchNameInDept")
    List<User> getUsersMatchNameInDept(@Param("deptId") String deptId,
                                       @Param("name") String name);

    @PostMapping("selectByUsername")
    User selectByUsername(String username);

    @PostMapping("selectUsersByRoleName")
    List<User> selectUsersByRoleName(String roleName);

    int updateAvailableDeptIds(@Param("userId") String userId,
                               @Param("deptIds") String deptIds);
}