package org.eduos.springboot.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.eduos.springboot.entity.Account;
import org.eduos.springboot.entity.BaseEntity;
import org.eduos.springboot.exception.ServiceException;
import org.eduos.springboot.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT拦截器
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    /**
     * 错误码401
     */
    private static final String ERROR_CODE_401 = "401";

    /**
     * 管理服务接口
     */
    private final ILoginService loginService;

    /**
     * 带有 loginService 参数的构造函数
     *
     * @param loginService ILoginService实例
     */
    @Autowired
    public JwtInterceptor(ILoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 用于对请求进行身份验证的预处理方法
     *
     * @param request  HttpServletRequest 对象
     * @param response HttpServletResponse 对象
     * @param handler  处理程序对象
     * @return 如果请求已通过身份验证，则为 true，否则为 false
     */
    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            token = request.getParameter("token");
        }

        // 执行认证
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(ERROR_CODE_401, "无token，请重新登录");
        }

        // 获取 token 中的adminId
        String userId;
        Account dbUser;
        try {
            userId = JWT.decode(token).getAudience().get(0);
            // 根据token中的userid查询数据库
            dbUser = loginService.getById(Integer.parseInt(userId));
        } catch (Exception e) {
            String errMsg = "token验证失败，请重新登录";
            log.error(errMsg + ", token=" + token, e);
            throw new ServiceException(ERROR_CODE_401, errMsg);
        }
        if (dbUser == null) {
            throw new ServiceException(ERROR_CODE_401, "用户不存在，请重新登录");
        }

        try {
            // 用户密码加签验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(dbUser.getPassword())).build();
            // 验证token
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new ServiceException(ERROR_CODE_401, "token验证失败，请重新登录");
        }
        return true;
    }
}
