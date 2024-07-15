/**
 * Author: Bintong
 * Date: 2024/7/9 13:54
 */

package com.project.springboot.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.project.springboot.common.Result;
import com.project.springboot.entity.User;
import com.project.springboot.exception.ServiceException;
import com.project.springboot.mapper.UserMapper;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token"); // gain data from HTTP header
        if (StrUtil.isBlank(token)) {
            token = request.getParameter("token"); // gain data from url
        }

        String method = request.getMethod();
        if("OPTIONS".equals(method)){
            return true;
        }

        // If the request is not mapped to a method, allow it to proceed
        if (handler instanceof HandlerMethod) {
            AuthAccess annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthAccess.class);
            if (annotation != null) {
                return true;
            }
        }
        // Perform authentication
        if (StrUtil.isBlank(token)) {
            throw new ServiceException("401", "Please log in / issue 1");
        }

        // Get the username from the token
        String userName;
        try {
            userName = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new ServiceException("401", "Please log in / issue 2");
        }

        // Query the database using the username from the token
        User user = userMapper.selectByUserName(userName);
        if (user == null) {
            throw new ServiceException("401", "Please log in / issue 3");
        }

        // Verify the token using the user's password
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUserPassword())).build();

        try {
            jwtVerifier.verify(token); // Verify the token
        } catch (JWTVerificationException e) {
            throw new ServiceException("401", "Please log in");
        }
        return true;
    }
}