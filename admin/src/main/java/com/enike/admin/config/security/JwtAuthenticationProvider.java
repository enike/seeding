package com.enike.admin.config.security;

import com.enike.admin.util.PasswordManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class JwtAuthenticationProvider extends DaoAuthenticationProvider {
    /**
     * @return
     * @Author Cuicui
     * @Description 自定义构造方法  jwt权限身份验证提供者
     * @Date 2020/3/3 15:14
     * @Param
     **/
    public JwtAuthenticationProvider(UserDetailsService userDetailsService) {
        setUserDetailsService(userDetailsService);
        setPasswordEncoder(new BCryptPasswordEncoder());
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        /*
        可以在此处覆写整个登录认证逻辑,由于我们已经在SysLoginController中规定了登录逻辑，
        所以不用再重写编写这个登录逻辑，此方法本可以不用编写，为了方便学习，作为展示。
         */
        return super.authenticate(authentication);
    }

    /**
     * 额外的身份验证
     *
     * @param userDetails
     * @param authentication
     * @throws AuthenticationException
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }

        String presentedPassword = authentication.getCredentials().toString();
        String salt = ((JwtUserDetails) userDetails).getSalt();
        // 覆写密码验证逻辑
        if (!new PasswordManager(salt).matches(userDetails.getPassword(), presentedPassword)) {
            logger.debug("Authentication failed: password does not match stored value");
            throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
    }
}
