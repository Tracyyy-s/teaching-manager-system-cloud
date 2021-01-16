package com.gwy.manager.controller;

import com.gwy.manager.domain.dto.ResultVO;
import com.gwy.manager.util.ResultVoUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tracy
 * @date 2021/1/16 15:55
 */
@RestController
public class LoginController {

    @GetMapping("/login")
    public ResultVO login() {
        return ResultVoUtil.error("Sorry, Not Found Resources");
    }

}
