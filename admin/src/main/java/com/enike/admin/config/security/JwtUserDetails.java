package com.enike.admin.config.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Author Cuicui
 * @Description 安全用户模型:  UserDetailsService加载好用户认证信息后会封装认证信息到一个UserDetails的实现类默认实现是User类，
 * 我们这里没有特殊需要，简单继承即可，复杂需求可以在此基础上进行拓展
 * JwtUserDetails是对认证信息的封装，实现spring security，提供userDetails接口，主要包括：用户名、密码、加密盐和权限信息。
 * @Date 2020/3/4 11:30
 * @Param
 * @return
 **/
public class JwtUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String salt;
    private Collection<? extends GrantedAuthority> authorities;

    JwtUserDetails(String username, String password, String salt, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * @return
     * @Author Cuicui
     * @Description @JsonIgnore:在实体类向前台返回数据时用来忽略不想传递给前台的属性或接口
     * @Date 2020/3/4 11:29
     * @Param
     **/
    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
