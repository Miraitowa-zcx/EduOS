package org.eduos.springboot.service;

import org.eduos.springboot.dto.LoginDTO;
import org.eduos.springboot.request.LoginRequest;

/**
 * 登录注册服务接口
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
public interface ILoginService {
    LoginDTO login(LoginRequest request);
}
