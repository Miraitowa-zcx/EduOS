package org.eduos.springboot.service;

import com.github.pagehelper.PageInfo;
import org.eduos.springboot.dto.LoginDTO;
import org.eduos.springboot.entity.Admin;
import org.eduos.springboot.request.BaseRequest;
import org.eduos.springboot.request.LoginRequest;
import org.eduos.springboot.request.PasswordRequest;

import java.util.List;

/**
 * 管理员服务接口
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
public interface IAdminService {

    /**
     * 获取管理员列表
     *
     * @return 管理员列表
     */
    List<Admin> list();

    /**
     * 获取分页后的管理员信息
     *
     * @param baseRequest 分页请求对象
     * @return 分页后的管理员信息
     */
    PageInfo<Admin> page(BaseRequest baseRequest);

    /**
     * 保存管理员信息
     *
     * @param admin 管理员对象
     */
    void save(Admin admin);

    /**
     * 根据ID获取管理员对象
     *
     * @param id 管理员ID
     * @return 对应的管理员对象
     */
    Admin getById(Integer id);

    /**
     * 更新管理员信息
     *
     * @param admin 管理员对象
     */
    void update(Admin admin);

    /**
     * 根据ID删除管理员
     *
     * @param id 管理员ID
     */
    void deleteById(Integer id);

    /**
     * 用户注册
     *
     * @param admin 注册的管理员对象
     * @return 注册后的登录信息对象
     */
    LoginDTO register(Admin admin);

    /**
     * 修改密码
     *
     * @param request 密码修改请求对象
     */
    void changePass(PasswordRequest request);
}
