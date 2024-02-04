package org.eduos.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.eduos.springboot.common.Result;
import org.eduos.springboot.controller.request.AdminPageRequest;
import org.eduos.springboot.controller.request.PasswordRequest;
import org.eduos.springboot.entity.Admin;
import org.eduos.springboot.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员控制器
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final IAdminService adminService;

    /**
     * 构造函数，注入管理员服务
     *
     * @param adminService 管理员服务
     */
    @Autowired
    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 修改密码
     *
     * @param request 密码请求对象
     * @return 修改结果
     */
    @PutMapping("/password")
    public Result password(@RequestBody PasswordRequest request) {
        adminService.changePass(request);
        return Result.success();
    }

    /**
     * 添加账号
     *
     * @param admin 用户对象
     * @return 添加结果
     */
    @PostMapping("/save")
    public Result save(@RequestBody Admin admin) {
        adminService.save(admin);
        return Result.success();
    }

    /**
     * 更新用户信息
     *
     * @param admin 用户对象
     * @return 更新结果
     */
    @PutMapping("/update")
    public Result update(@RequestBody Admin admin) {
        adminService.update(admin);
        return Result.success();
    }

    /**
     * 根据ID删除用户
     *
     * @param id 用户ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        adminService.deleteById(id);
        return Result.success();
    }

    /**
     * 根据ID查询用户
     *
     * @param id 用户ID
     * @return 查询结果
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Admin admin = adminService.getById(id);
        return Result.success(admin);
    }

    /**
     * 全部查询
     *
     * @return 查询结果
     */
    @GetMapping("/list")
    public Result list() {
        List<Admin> list = adminService.list();
        return Result.success(list);
    }

    /**
     * 模糊分页查询
     *
     * @param adminPageRequest 分页请求对象
     * @return 查询结果
     */
    @GetMapping("/page")
    public Result page(AdminPageRequest adminPageRequest) {
        return Result.success(adminService.page(adminPageRequest));
    }
}
