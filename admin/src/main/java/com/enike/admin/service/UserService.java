package com.enike.admin.service;

import com.enike.admin.AO.RegisterAO;
import com.enike.admin.config.security.JwtAuthenticatioToken;
import com.enike.admin.entity.User;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.security.authentication.AuthenticationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author enike
 * @since 2020-07-17
 */
public interface UserService extends IService<User> {

    boolean register(RegisterAO user);

    String login(String username, String password);

    JwtAuthenticatioToken getToken(HttpServletRequest request, String username, String password, AuthenticationManager authenticationManager);

    Set<String> getPermissionsByName(String username);
}
