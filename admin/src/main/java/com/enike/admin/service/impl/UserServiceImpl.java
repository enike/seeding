package com.enike.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.enike.admin.AO.RegisterAO;
import com.enike.admin.config.exception.SeedException;
import com.enike.admin.config.security.JwtAuthenticatioToken;
import com.enike.admin.config.security.JwtTokenUtils;
import com.enike.admin.constants.ErrorMessage;
import com.enike.admin.entity.User;
import com.enike.admin.dao.UserMapper;
import com.enike.admin.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.enike.admin.util.PasswordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author enike
 * @since 2020-07-17
 */
@Service

public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean register(RegisterAO user) {
        if(user.getUsername() != null && user.getPassword()!=null && user.getPhone()!=null){
            EntityWrapper<User> ew = new EntityWrapper<>();
            ew.eq("username",user.getUsername())
                    .eq("phone",user.getPhone());
            if(userMapper.selectList(ew).size()>0){
                throw new SeedException("用户信息已被注册"+ErrorMessage.INPUT_VALUE_INVALID);
            }
            String salt = PasswordManager.getSalt();
            user.setSalt(salt);
            String password = new PasswordManager(salt).encode(user.getPassword());
            user.setPassword(password);
            userMapper.insert(user);
        }else{
            throw new SeedException(ErrorMessage.INPUT_VALUE_INVALID);
        }
        return true;
    }

    @Override
    public String login(String username, String password) {
        if(username != null && password != null){
            User user = userMapper.selectByName(username);
            if(user.getPassword().equals(new PasswordManager(user.getSalt()).encode(password))){
                return "SUCCESS";
            }else{
                return ErrorMessage.PASSWORD_ERROR;
            }
        }
        return "";
    }

    @Override
    public JwtAuthenticatioToken getToken(HttpServletRequest request, String username, String password, AuthenticationManager authenticationManager) {
        JwtAuthenticatioToken token = new JwtAuthenticatioToken(username, password);

        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        token.setToken(JwtTokenUtils.generateToken(authentication));
        return token;
    }

    @Override
    public Set<String> getPermissionsByName(String username) {

        return userMapper.getPermissionsByName(username);
    }
}
