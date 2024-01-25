package org.eduos.springboot.request;

import lombok.Data;

/**
 * 基本请求类
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@Data
public class BaseRequest {

    /**
     * 页码，默认为1
     */
    private Integer pageNum = 1;

    /**
     * 每页大小，默认为10
     */
    private Integer pageSize = 10;
}
