package org.eduos.springboot.service;

import com.github.pagehelper.PageInfo;
import org.eduos.springboot.controller.dto.LoginDTO;
import org.eduos.springboot.entity.Student;
import org.eduos.springboot.controller.request.BaseRequest;
import org.eduos.springboot.controller.request.PasswordRequest;

import java.util.List;

/**
 * 学生用户服务接口
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
public interface IStudentService {

    /**
     * 获取学生用户列表
     *
     * @return 学生用户列表
     */
    List<Student> list();

    /**
     * 获取分页后的学生用户信息
     *
     * @param baseRequest 分页请求对象
     * @return 分页后的学生用户信息
     */
    PageInfo<Student> page(BaseRequest baseRequest);

    /**
     * 保存学生用户信息
     *
     * @param student 学生用户对象
     */
    void save(Student student);

    /**
     * 根据ID获取学生用户对象
     *
     * @param id 学生用户ID
     * @return 对应的学生用户对象
     */
    Student getById(Integer id);

    /**
     * 更新学生用户信息
     *
     * @param student 学生用户对象
     */
    void update(Student student);

    /**
     * 根据ID删除学生用户
     *
     * @param id 学生用户ID
     */
    void deleteById(Integer id);

    /**
     * 修改密码
     *
     * @param request 密码修改请求学生用户对象
     */
    void changePass(PasswordRequest request);
}
