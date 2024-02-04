package org.eduos.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.eduos.springboot.entity.Student;
import org.eduos.springboot.controller.request.BaseRequest;
import org.eduos.springboot.controller.request.PasswordRequest;

import java.util.List;

/**
 * 学生用户数据映射器接口
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@Mapper
public interface StudentMapper {

    /**
     * 获取所有学生用户列表
     *
     * @return 学生用户列表
     */
    List<Student> list();

    /**
     * 根据条件获取学生用户列表
     *
     * @param baseRequest 条件
     * @return 学生用户列表
     */
    List<Student> listByCondition(BaseRequest baseRequest);

    /**
     * 添加学生用户
     *
     * @param student 学生用户对象
     */
    void save(Student student);

    /**
     * 根据ID获取学生用户
     *
     * @param id ID
     * @return 学生用户对象
     */
    Student getById(Integer id);

    /**
     * 根据ID更新学生用户
     *
     * @param student 学生用户对象
     */
    void updateById(Student student);

    /**
     * 根据ID删除学生用户
     *
     * @param id ID
     */
    void deleteById(Integer id);

    /**
     * 根据用户名和密码获取学生用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 学生用户对象
     */
    Student getByUsernameAndPassword(String username, String password);

    /**
     * 根据用户名获取学生用户
     *
     * @param username 用户名
     * @return 学生用户对象
     */
    Student getByUsername(String username);

    /**
     * 更新密码
     *
     * @param request 密码请求对象
     * @return 更新后的密码
     */
    int updatePassword(PasswordRequest request);
}
