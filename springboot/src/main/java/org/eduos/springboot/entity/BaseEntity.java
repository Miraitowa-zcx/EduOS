package org.eduos.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 基础实体类，用于存储实体对象的基本信息
 *
 * @author <a href="mailto:2038322151@qq.com">Miraitowa_zcx</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseEntity extends Account implements Serializable {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 实体对象的创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createtime;

    /**
     * 实体对象的更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updatetime;
}
