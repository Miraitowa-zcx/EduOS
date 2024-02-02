package org.eduos.springboot.controller;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.eduos.springboot.common.Result;
import org.eduos.springboot.dto.LoginDTO;
import org.eduos.springboot.request.LoginRequest;
import org.eduos.springboot.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录注册控制器
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@Slf4j
@RestController
public class LoginController {

    private final ILoginService loginService;

    /**
     * 构造函数，注入登录注册服务
     *
     * @param loginService 登录注册服务
     */
    @Autowired
    public LoginController(ILoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 登录
     * @param request 登录请求
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest request) {
        if (ObjectUtil.isEmpty(request.getUsername()) || ObjectUtil.isEmpty(request.getPassword())
                || ObjectUtil.isEmpty(request.getRole())) {
            return Result.error("参数缺失！");
        }
        LoginDTO login = loginService.login(request);
        return Result.success(login);
    }
}
