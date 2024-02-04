package org.eduos.springboot.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.eduos.springboot.entity.Account;
import org.eduos.springboot.entity.Admin;
import org.eduos.springboot.entity.BaseEntity;
import org.eduos.springboot.service.IAdminService;
import org.eduos.springboot.service.ILoginService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

/**
 * Token 工具类
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@Component
@Slf4j
public class TokenUtils {

    private static ILoginService staticLoginService;

    @Resource
    private ILoginService loginService;

    @PostConstruct
    public void setUserService() {
        staticLoginService = loginService;
    }

    public static String genToken(String userId, String sign) {
         // 将 user id 保存到 token 里面,作为载荷
        return JWT.create().withAudience(userId)
                // 2小时后token过期
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2))
                // 以 password 作为 token 的密钥
                .sign(Algorithm.HMAC256(sign));
    }

    public static String genToken(String userId, String sign, int days) {
        // 将 user id 保存到 token 里面,作为载荷
        return JWT.create().withAudience(userId)
                // 2小时后token过期
                .withExpiresAt(DateUtil.offsetDay(new Date(), days))
                // 以 password 作为 token 的密钥
                .sign(Algorithm.HMAC256(sign));
    }

    public static Account getCurrentUser() {
        String token = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            token = request.getHeader("token");
            if (StrUtil.isBlank(token)) {
                token = request.getParameter("token");
            }
            if (StrUtil.isBlank(token)) {
                log.error("获取当前登录的token失败， token: {}", token);
                return null;
            }
            String adminId = JWT.decode(token).getAudience().get(0);
            return staticLoginService.getById(Integer.valueOf(adminId));
        } catch (Exception e) {
            log.error("获取当前登录的管理员信息失败, token={}", token,  e);
            return null;
        }
    }
}
