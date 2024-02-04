package org.eduos.springboot.controller.request;

import lombok.Data;

/**
 * 密码修改请求类
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@Data
public class PasswordRequest {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 新密码
     */
    private String newPass;
}
