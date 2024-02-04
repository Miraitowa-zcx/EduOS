package org.eduos.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.eduos.springboot.common.Result;
import org.eduos.springboot.controller.request.AdminPageRequest;
import org.eduos.springboot.controller.request.PasswordRequest;
import org.eduos.springboot.entity.Student;
import org.eduos.springboot.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生用户控制器
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {

    private final IStudentService studentService;

    /**
     * 构造函数，注入学生用户服务
     *
     * @param studentService 学生用户服务
     */
    @Autowired
    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 修改密码
     *
     * @param request 密码请求对象
     * @return 修改结果
     */
    @PutMapping("/password")
    public Result password(@RequestBody PasswordRequest request) {
        studentService.changePass(request);
        return Result.success();
    }

    /**
     * 添加账号
     *
     * @param student 用户对象
     * @return 添加结果
     */
    @PostMapping("/save")
    public Result save(@RequestBody Student student) {
        studentService.save(student);
        return Result.success();
    }

    /**
     * 更新用户信息
     *
     * @param student 用户对象
     * @return 更新结果
     */
    @PutMapping("/update")
    public Result update(@RequestBody Student student) {
        studentService.update(student);
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
        studentService.deleteById(id);
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
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    /**
     * 全部查询
     *
     * @return 查询结果
     */
    @GetMapping("/list")
    public Result list() {
        List<Student> list = studentService.list();
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
        return Result.success(studentService.page(adminPageRequest));
    }
}
