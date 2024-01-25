package org.eduos.springboot.common;

import lombok.Data;

/**
 * 统一返回对象
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@Data
public class Result {

    // 成员变量-成功状态码
    private static final String SUCCESS_CODE = "200";
    // 成员变量-错误状态码
    private static final String ERROR_CODE = "-1";

    // 成员变量-状态码
    private String code;
    // 成员变量-数据
    private Object data;
    // 成员变量-消息
    private String msg;

    /**
     * 创建一个成功的Result对象
     *
     * @return Result对象
     */
    public static Result success() {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        return result;
    }

    /**
     * 创建一个成功的Result对象
     *
     * @param data 数据
     * @return Result对象
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setData(data);
        return result;
    }

    /**
     * 创建一个错误的Result对象
     *
     * @param msg 错误消息
     * @return Result对象
     */
    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(ERROR_CODE);
        result.setMsg(msg);
        return result;
    }

    /**
     * 创建一个错误的Result对象
     *
     * @param code 错误状态码
     * @param msg  错误消息
     * @return Result对象
     */
    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
