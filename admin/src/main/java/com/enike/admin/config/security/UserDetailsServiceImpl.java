package com.enike.admin.config.security;

import com.enike.admin.dao.UserMapper;
import com.enike.admin.entity.User;
import com.enike.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author Cuicui
 * @Description 自定义的UserDetailsServiceImpl实现UserDetailsService，从我们的用户服务SysUserService中获取用户和权限信息
 * @Date 2020/3/4 10:26
 * @Param
 * @return
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByName(username);
        if(user == null){
            throw new UsernameNotFoundException("该用户不存在");
        }

        Set<String> permissions = userService.getPermissionsByName(user.getUsername());
        List<GrantedAuthority> grantedAuthorities = permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
        return new JwtUserDetails(user.getUsername(), user.getPassword(), user.getSalt(), grantedAuthorities);
    }
}
