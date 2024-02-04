package org.eduos.springboot.service.impl;

import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.eduos.springboot.controller.dto.LoginDTO;
import org.eduos.springboot.entity.Account;
import org.eduos.springboot.exception.ServiceException;
import org.eduos.springboot.mapper.LoginMapper;
import org.eduos.springboot.service.ILoginService;
import org.eduos.springboot.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务实现类，实现 ILoginService 接口
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@Slf4j
@Service
public class LoginService implements ILoginService {

    /**
     * 密码盐值
     */
    private static final String PASS_SALT = "Miraitowa";

    /**
     * 登录注册数据访问对象
     */
    private final LoginMapper loginMapper;

    /**
     * 构造方法，注入登录注册mapper
     *
     * @param loginMapper 登陆注册数据访问对象
     */
    @Autowired
    public LoginService(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    /**
     * 用户登录
     *
     * @param account 登录请求对象
     * @return 登录信息
     */
    @Override
    public LoginDTO login(Account account) {
        Account dbUser;
        try {
            dbUser = loginMapper.getByUserAndRole(account.getUsername(), account.getRole());
        } catch (Exception e) {
            log.error("用户登录异常", e);
            throw new ServiceException("用户登录异常");
        }
        if (dbUser == null) {
            throw new ServiceException("用户名或密码错误");
        }
        // 判断密码是否合法
        String securePass = securePass(account.getPassword());
        if (!securePass.equals(dbUser.getPassword())) {

            throw new ServiceException("用户名或密码错误");
        }
        if (!dbUser.isStatus()) {
            throw new ServiceException("用户已被禁用, 请联系管理员");
        }

        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(dbUser, loginDTO);

        // 生成token
        String token = TokenUtils.genToken(String.valueOf(dbUser.getId()), dbUser.getPassword());
        loginDTO.setToken(token);
        return loginDTO;
    }

    /**
     * 用户注册
     *
     * @param account 注册对象
     * @return 注册信息
     */
    @Override
    public LoginDTO register(Account account) {
        Account register = loginMapper.getByUserAndRole(account.getUsername(), account.getRole());
        if (register != null) {
            throw new ServiceException("用户名已存在");
        } else {
            account.setPassword(securePass(account.getPassword()));
            loginMapper.save(account);
            LoginDTO loginDTO = new LoginDTO();
            BeanUtils.copyProperties(account, loginDTO);
            return loginDTO;
        }
    }

    /**
     * 根据ID获取用户对象
     *
     * @param id 用户ID
     * @return 对应的用户对象
     */
    @Override
    public Account getById(Integer id) {
        return loginMapper.getById(id);
    }

    /**
     * 密码加密解密
     *
     * @param password 密码
     * @return 密码加密结果
     */
    private String securePass(String password) {
        return SecureUtil.md5(password + PASS_SALT);
    }
}
