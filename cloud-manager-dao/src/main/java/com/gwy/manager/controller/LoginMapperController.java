package com.gwy.manager.controller;

import com.gwy.manager.mapper.LoginMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Tracy
 * @date 2020/12/16 15:45
 */
@RestController
@RequestMapping("LoginMapperController")
public class LoginMapperController {

    @Resource
    private LoginMapper loginMapper;

    /**
     * 自定义登录接口
     *
     * @param account 用户账号
     * @return 用户加密密码
     */
    @RequestMapping("selectUserPasswordFromAll")
    String selectUserPasswordFromAll(@RequestParam("account") String account) {
        System.out.println("login..." + account);
        return loginMapper.selectUserPasswordFromAll(account);
    }
}
