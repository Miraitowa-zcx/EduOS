package org.eduos.springboot.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * springboot 整合 webmvc
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * JWT拦截器
     */
    private final JwtInterceptor jwtInterceptor;

    /**
     * 构造方法注入JWT拦截器
     *
     * @param jwtInterceptor JWT拦截器
     */
    @Autowired
    public WebConfig(JwtInterceptor jwtInterceptor) {
        this.jwtInterceptor = jwtInterceptor;
    }

    /**
     * 配置路径匹配规则
     *
     * @param configurer 路径匹配配置
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 指定controller统一的接口前缀
        configurer.addPathPrefix("/api", clazz -> clazz.isAnnotationPresent(RestController.class));
    }

    /**
     * 加自定义拦截器JwtInterceptor，设置拦截规则
     *
     * @param registry 拦截器注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 配置白名单
        List<String> patterns = new ArrayList<>();
        patterns.add("/api/admin/login");
        patterns.add("/api/admin/register");
        patterns.add("/api/login");
        patterns.add("/api/register");

        registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/**").excludePathPatterns(patterns);
    }
}
