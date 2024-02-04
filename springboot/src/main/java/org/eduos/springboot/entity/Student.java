package org.eduos.springboot.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 学生用户实体类，继承 BaseEntity
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Student extends BaseEntity {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 专业
     */
    private String major;

    /**
     * 学院
     */
    private String college;

    /**
     * 学号
     */
    private String studentNumber;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 审核状态
     */
    private String examineState;
}
