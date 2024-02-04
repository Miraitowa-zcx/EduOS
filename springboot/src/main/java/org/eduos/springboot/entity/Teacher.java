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
public class Teacher extends BaseEntity {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = -22769753886023337L;

    /**
     * 教师工号
     */
    private String teacherJobNumber;

    /**
     * 专业
     */
    private String major;

    /**
     * 学院
     */
    private String college;

    /**
     * 教师姓名
     */
    private String teacherName;

    /**
     * 审核状态
     */
    private String examineState;
}
