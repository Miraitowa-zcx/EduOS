package org.eduos.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Springboot 启动程序
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@RestController
@SpringBootApplication
public class SpringbootApplication {

        /**
     * 启动程序
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }


    /**
     * 健康检查接口
     *
     * @return 返回字符串
     */
    @GetMapping("/health")
    public String health() {
        return "SUCCESS";
    }
}
