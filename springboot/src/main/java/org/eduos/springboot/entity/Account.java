package org.eduos.springboot.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 账户实体类
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@Data
public class Account implements Serializable {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 实体对象的ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    private String role;

    /**
     * 状态
     */
    private boolean status;
}
