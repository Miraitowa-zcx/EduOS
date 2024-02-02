package org.eduos.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.eduos.springboot.entity.BaseEntity;

/**
 * 登陆注册数据映射器接口
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@Mapper
public interface LoginMapper {
    BaseEntity getByUserAndPass(@Param("username") String username, @Param("role") String role);
}
