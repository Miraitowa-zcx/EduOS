package org.eduos.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.eduos.springboot.entity.Account;

/**
 * 登陆注册数据映射器接口
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@Mapper
public interface LoginMapper {

    /**
     * 保存登陆数据
     *
     * @param username 用户名
     * @param role     角色类型
     * @return 登陆数据
     */
    Account getByUserAndRole(@Param("username") String username, @Param("role") String role);

    /**
     * 保存注册数据
     *
     * @param account 注册数据
     */
    void save(Account account);

    /**
     * 根据ID获取用户信息
     *
     * @param id ID
     * @return 用户信息对象
     */
    Account getById(Integer id);
}
