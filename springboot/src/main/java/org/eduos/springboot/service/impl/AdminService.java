package org.eduos.springboot.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.eduos.springboot.controller.request.BaseRequest;
import org.eduos.springboot.controller.request.PasswordRequest;
import org.eduos.springboot.entity.Admin;
import org.eduos.springboot.exception.ServiceException;
import org.eduos.springboot.mapper.AdminMapper;
import org.eduos.springboot.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 管理员服务实现类，实现 IAdminService 接口
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@Slf4j
@Service
public class AdminService implements IAdminService {

    private final AdminMapper adminMapper;

    /**
     * 构造方法，注入管理员mapper
     *
     * @param adminMapper 管理员数据访问对象
     */
    @Autowired
    public AdminService(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    /**
     * 全部查询
     *
     * @return 管理员列表
     */
    @Override
    public List<Admin> list() {
        return adminMapper.list();
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
    public PageInfo<Admin> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Admin> admin = adminMapper.listByCondition(baseRequest);
        return new PageInfo<>(admin);
    }

    /**
     * 添加账号
     *
     * @param admin 管理员对象
     */
    @Override
    public void save(Admin admin) {
        // 如果密码为空，则使用默认密码
        if (StrUtil.isBlank(admin.getPassword())) {
            admin.setPassword(DEFAULT_PASS);
        }
        // 对密码进行MD5加密，加盐
        admin.setPassword(securePass(admin.getPassword()));
        try {
            adminMapper.save(admin);
        } catch (DuplicateKeyException e) {
            log.error("保存用户失败", e);
            throw new ServiceException("用户已存在");
        }
    }

    /**
     * 根据ID查找用户
     *
     * @param id 用户ID
     * @return 管理员对象
     */
    @Override
    public Admin getById(Integer id) {
        return adminMapper.getById(id);
    }

    /**
     * 更新用户信息
     *
     * @param admin 管理员对象
     */
    @Override
    public void update(Admin admin) {
        admin.setUpdatetime(new Timestamp(new Date().getTime()));
        adminMapper.updateById(admin);
    }

    /**
     * 根据ID删除用户
     *
     * @param id 用户ID
     */
    @Override
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    /**
     * 修改密码
     *
     * @param request 密码请求对象
     */
    @Override
    public void changePass(PasswordRequest request) {
        request.setNewPass(securePass(request.getNewPass()));
        int count = adminMapper.updatePassword(request);
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
