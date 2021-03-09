package com.yunwanjia.guli.service.edu.controller;

import com.yunwanjia.guli.common.base.result.ResultDTO;
import org.springframework.web.bind.annotation.*;

/**
 * @author lijw
 * 临时模拟登录接口
 */
@CrossOrigin
@RestControllerAdvice
@RequestMapping("/user")
public class LoginController {
    /**
     * 模拟登录
     *
     * @return
     */
    @PostMapping("/login")
    public ResultDTO login() {
        return ResultDTO.ok().data("token", "admin");
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/info")
    public ResultDTO info() {
        return ResultDTO.ok()
                .data("name", "admin")
                .data("roles", "[admin,admin]")
                .data("avatar", "http://www.yunwanjia.site/jay.png");
    }

    /**
     * 登出
     * @return
     */
    @PostMapping("/logout")
    public ResultDTO logout() {
        return ResultDTO.ok();
    }
}
