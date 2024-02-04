package org.eduos.springboot.service;

import org.eduos.springboot.controller.dto.LoginDTO;
import org.eduos.springboot.entity.Account;

/**
 * 登录注册服务接口
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
public interface ILoginService {

    /**
     * 用户登录
     *
     * @param account 登录请求对象
     * @return 登录信息对象
     */
    LoginDTO login(Account account);

    /**
     * 用户注册
     *
     * @param account 注册的用户对象
     * @return 注册后的登录信息对象
     */
    LoginDTO register(Account account);

    /**
     * 通过id获取用户信息
     *
     * @param id 用户id
     * @return 用户信息对象
     */
    Account getById(Integer id);
}
