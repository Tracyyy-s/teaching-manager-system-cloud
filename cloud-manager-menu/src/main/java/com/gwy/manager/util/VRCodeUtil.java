package com.gwy.manager.util;


import com.gwy.manager.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Tracy
 * @date 2020/11/26 15:26
 */
@Component
public class VRCodeUtil {

    /**
     * 验证码的长度
     */
    private static final int VR_CODE_LENGTH = 6;
    /**
     * 验证码过期时间，默认5min
     */
    private static final int EXPIRATION_TIME = 300;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 获得用户验证码的key
     * @param userId    用户id
     * @return  结果集
     */
    public String getCode(String userId) {
        return (String) redisUtil.get(userId + ":Code");
    }
    /**
     * 删除用户
     * @param userId    用户id
     */
    public void deleteCode(String userId) {
        redisUtil.del(redisUtil.codeKey(userId));
    }
}
