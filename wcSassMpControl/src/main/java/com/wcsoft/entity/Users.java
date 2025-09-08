package com.wcsoft.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Roper
 * @since 2023-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编码
     */
    private String userId;

    /**
     * 用户名/员工号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 部门编码
     */
    private String deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 职位编码
     */
    private String postionId;

    /**
     * 职位名
     */
    private String postionName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 最后登陆时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 员工状态 0有效 1无效
     */
    private String status;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 用户uuid
     */
    private String uuid;

    /**
     * 微信的openid
     */
    @TableField("openId")
    private String openId;

    /**
     * 用户等级
     */
    private String level;

    /**
     * 身份证号
     */
    private String idNo;

    private String other;

    private String roleId;
    
    
}
