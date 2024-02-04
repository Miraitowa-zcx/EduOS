package org.eduos.springboot.controller.dto;

import lombok.Data;

/**
 * 登录 DTO
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@Data
public class LoginDTO {

    /**
     * ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * Token
     */
    private String token;
}
