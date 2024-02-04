package org.eduos.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.eduos.springboot.entity.Admin;
import org.eduos.springboot.controller.request.BaseRequest;
import org.eduos.springboot.controller.request.PasswordRequest;

import java.util.List;

/**
 * 管理员数据映射器接口
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@Mapper
public interface AdminMapper {

    /**
     * 获取所有管理员列表
     *
     * @return 管理员列表
     */
    List<Admin> list();

    /**
     * 根据条件获取管理员列表
     *
     * @param baseRequest 条件
     * @return 管理员列表
     */
    List<Admin> listByCondition(BaseRequest baseRequest);

    /**
     * 添加管理员
     *
     * @param admin 管理员对象
     */
    void save(Admin admin);

    /**
     * 根据ID获取管理员
     *
     * @param id ID
     * @return 管理员对象
     */
    Admin getById(Integer id);

    /**
     * 根据ID更新管理员
     *
     * @param admin 管理员对象
     */
    void updateById(Admin admin);

    /**
     * 根据ID删除管理员
     *
     * @param id ID
     */
    void deleteById(Integer id);

    /**
     * 根据用户名和密码获取管理员
     *
     * @param username 用户名
     * @param password 密码
     * @return 管理员对象
     */
    Admin getByUsernameAndPassword(String username, String password);

    /**
     * 根据用户名获取管理员
     *
     * @param username 用户名
     * @return 管理员对象
     */
    Admin getByUsername(String username);

    /**
     * 更新密码
     *
     * @param request 密码请求对象
     * @return 更新后的密码
     */
    int updatePassword(PasswordRequest request);
}
