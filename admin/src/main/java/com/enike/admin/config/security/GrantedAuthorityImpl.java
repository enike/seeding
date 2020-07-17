package com.enike.admin.config.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Author Cuicui
 * @Description  实现自定义权限封装，如果有特殊需求在此类中实现。此处我们直接调用父方法
 * GrantedAuthorityImpl实现了spring security的GrantedAuthority接口，是对权限的封装，内部包括一个字符串类型的权限标识authority
 * 对应表单的perms字段的权限字符串，比如用户管理的增删改查权限标志：sys:user:add、sys:user:delete、sys:user:edit、sys:user:add。
 * @Date 2020/3/6 14:39
 * @Param
 * @return
 **/
public class GrantedAuthorityImpl implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
