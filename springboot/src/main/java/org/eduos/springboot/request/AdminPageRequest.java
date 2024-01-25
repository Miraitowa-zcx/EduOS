package org.eduos.springboot.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理员页面请求类，继承BaseRequest
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AdminPageRequest extends BaseRequest {

    /**
     * 用户名
     */
    private String username;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;
}
