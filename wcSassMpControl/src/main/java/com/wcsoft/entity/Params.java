package com.wcsoft.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 全局参数表
 * </p>
 *
 * @author Roper
 * @since 2023-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("params")
public class Params implements Serializable {

    private static final long serialVersionUID = 1L;

    private String paramId;

    private String paramName;

    private String paramValue;

    private String createTime;

    private String updateTime;


}
