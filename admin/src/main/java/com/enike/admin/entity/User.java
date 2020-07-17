package com.enike.admin.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.enike.admin.AO.RegisterAO;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author enike
 * @since 2020-07-17
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐
     */
    private String salt;
    /**
     * 备注
     */
    private String remark;
    /**
     * 角色
     */
    private String role;
    /**
     * 真实姓名
     */
    private String realname;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 电话
     */
    private String phone;
    public void setFromAO(RegisterAO ao){
        this.username = ao.getUsername();
        this.password = ao.getPassword();
        this.role = ao.getRole();
        this.email = ao.getEmail();
    }
}
