/**
 * Author: Bintong
 * Date: 2024/7/9 14:37
 */

package com.project.springboot.Utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.project.springboot.entity.User;
import com.project.springboot.mapper.UserMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenUtils {

    private static UserMapper staticUserMapper;

    @Resource
    UserMapper userMapper;

    @PostConstruct
    public void setUserService() {
        staticUserMapper = userMapper;
    }

    /**
     * Generate token
     *
     * @return token string
     */
    public static String generateToken(String userName, String sign) {
        return JWT.create().withAudience(userName) // Save user id into the token as payload
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // Token expires in 2 hours
                .sign(Algorithm.HMAC256(sign)); // Use password as the token's secret key
    }

    /**
     * Get the current logged-in user's information
     *
     * @return user object
     */
    public static User getCurrentUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)) {
//                String userName = JWT.decode(token).getAudience().get(1);

                DecodedJWT decodedJWT = JWT.decode(token);
                String userName = decodedJWT.getClaim("userName").asString();

                return staticUserMapper.selectByUserName(userName);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
