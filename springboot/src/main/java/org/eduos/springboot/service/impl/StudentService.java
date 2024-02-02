package org.eduos.springboot.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.eduos.springboot.dto.LoginDTO;
import org.eduos.springboot.entity.Student;
import org.eduos.springboot.exception.ServiceException;
import org.eduos.springboot.mapper.StudentMapper;
import org.eduos.springboot.request.BaseRequest;
import org.eduos.springboot.request.LoginRequest;
import org.eduos.springboot.request.PasswordRequest;
import org.eduos.springboot.service.IStudentService;
import org.eduos.springboot.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 学生用户服务实现类，实现 IStudentService 接口
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@Slf4j
@Service
public class StudentService implements IStudentService {

    private final StudentMapper studentMapper;

    /**
     * 构造方法，注入学生用户mapper
     *
     * @param studentMapper 学生用户数据访问对象
     */
    @Autowired
    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    /**
     * 全部查询
     *
     * @return 学生用户列表
     */
    @Override
    public List<Student> list() {
        return studentMapper.list();
    }

    /**
     * 默认密码
     */
    private static final String DEFAULT_PASS = "123456";
    /**
     * 密码盐值
     */
    private static final String PASS_SALT = "Miraitowa";

    /**
     * 模糊分页查询
     *
     * @param baseRequest 基本请求对象
     * @return 分页信息
     */
    @Override
    public PageInfo<Student> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Student> students = studentMapper.listByCondition(baseRequest);
        return new PageInfo<>(students);
    }

    /**
     * 添加账号
     *
     * @param student 学生用户对象
     */
    @Override
    public void save(Student student) {
        // 如果密码为空，则使用默认密码
        if (StrUtil.isBlank(student.getPassword())) {
            student.setPassword(DEFAULT_PASS);
        }
        // 对密码进行MD5加密，加盐
        student.setPassword(securePass(student.getPassword()));
        try {
            studentMapper.save(student);
        } catch (DuplicateKeyException e) {
            log.error("保存用户失败", e);
            throw new ServiceException("用户已存在");
        }
    }

    /**
     * 根据ID查找用户
     *
     * @param id 用户ID
     * @return 学生用户对象
     */
    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

    /**
     * 更新用户信息
     *
     * @param student 学生用户对象
     */
    @Override
    public void update(Student student) {
        student.setUpdatetime(new Timestamp(new Date().getTime()));
        studentMapper.updateById(student);
    }

    /**
     * 根据ID删除用户
     *
     * @param id 用户ID
     */
    @Override
    public void deleteById(Integer id) {
        studentMapper.deleteById(id);
    }

    /**
     * 用户注册
     *
     * @param student 学生用户对象
     * @return 登录信息
     */
    @Override
    public LoginDTO register(Student student) {
        Student register = studentMapper.getByUsername(student.getUsername());
        if (register != null) {
            throw new ServiceException("用户名已存在");
        } else {
            student.setPassword(securePass(student.getPassword()));
            studentMapper.save(student);
            LoginDTO loginDTO = new LoginDTO();
            BeanUtils.copyProperties(student, loginDTO);
            return loginDTO;
        }
    }

    /**
     * 修改密码
     *
     * @param request 密码请求对象
     */
    @Override
    public void changePass(PasswordRequest request) {
        request.setNewPass(securePass(request.getNewPass()));
        int count = studentMapper.updatePassword(request);
        if (count <= 0) {
            throw new ServiceException("修改失败");
        }
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
