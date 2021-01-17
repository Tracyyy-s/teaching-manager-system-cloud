package com.gwy.manager.invokes;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tracy
 * @date 2020/12/16 15:45
 */
@Component
@RequestMapping("LoginMapperController")
@FeignClient(value = "CLOUD-MANAGER-DAO-SERVER",configuration = FeignClientProperties.FeignClientConfiguration.class, contextId = "33")
@Qualifier("webLoginInvoker")
public interface LoginInvoker {

    /**
     * 自定义登录接口
     * @param account   用户账号
     * @return  用户加密密码
     */
    @RequestMapping("selectUserPasswordFromAll")
    String selectUserPasswordFromAll(@RequestParam("account") String account);
}
