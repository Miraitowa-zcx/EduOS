package org.eduos.springboot.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理员实体类，继承 BaseEntity
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Admin extends BaseEntity {

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;
}
