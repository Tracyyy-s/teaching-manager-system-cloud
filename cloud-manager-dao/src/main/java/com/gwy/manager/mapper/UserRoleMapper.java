package com.gwy.manager.mapper;

import com.gwy.manager.domain.entity.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
public interface UserRoleMapper {
    int deleteByPrimaryKey(@Param("userId") String userId, @Param("roleId") Integer roleId);

    int insert(UserRole record);

    int insertByBatch(@Param("userRoles") List<UserRole> userRoles);

    List<UserRole> selectAll();

    int deleteRoleOfUser(@Param("userId") String userId);

    int addRolesForUser(@Param("userId") String userId, @Param("roleIds") List<Integer> roleIds);
}