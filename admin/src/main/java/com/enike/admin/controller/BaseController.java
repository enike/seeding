package com.enike.admin.controller;

import com.enike.admin.config.exception.SeedException;
import com.enike.admin.config.security.JwtUserDetails;
import com.enike.admin.constants.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    @Autowired
    protected HttpServletRequest request;


    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof JwtUserDetails)) {
            throw new SeedException(ErrorMessage.TOKEN_INVALID);
        }

        String username = ((JwtUserDetails) principal).getUsername();
        if (username == null) {
            throw new SeedException(ErrorMessage.TOKEN_INVALID);
        }

        return username;
    }
}
