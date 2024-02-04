package org.eduos.springboot.controller;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.eduos.springboot.common.Result;
import org.eduos.springboot.controller.dto.LoginDTO;
import org.eduos.springboot.entity.Account;
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
     *
     * @param account 登录请求
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        if (ObjectUtil.isEmpty(account.getUsername()) || ObjectUtil.isEmpty(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            return Result.error("参数缺失！");
        }
        LoginDTO login = loginService.login(account);
        return Result.success(login);
    }

    /**
     * 注册
     *
     * @param account 注册请求
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result register(@RequestBody Account account) {
        if (ObjectUtil.isEmpty(account.getUsername()) || ObjectUtil.isEmpty(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            return Result.error("参数缺失！");
        }
        LoginDTO login = loginService.register(account);
        return Result.success(login);
    }
}
