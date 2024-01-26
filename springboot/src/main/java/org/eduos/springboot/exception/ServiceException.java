package org.eduos.springboot.exception;

import lombok.Getter;

/**
 * 异常类
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@Getter
public class ServiceException extends RuntimeException {

    // 错误代码
    private String code;

    /**
     * 创建一个ServiceException实例
     *
     * @param message 异常消息
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * 创建一个ServiceException实例
     *
     * @param code    错误代码
     * @param message 异常消息
     */
    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }
}
