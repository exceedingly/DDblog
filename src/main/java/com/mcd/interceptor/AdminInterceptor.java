package com.mcd.interceptor;
import com.mcd.mapper.UserMapper;
import com.mcd.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by codedrinker on 2019/5/16.
 */
@Component
public class AdminInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("##################################我是拦截器");


//        Cookie[] cookies = request.getCookies();
//        if (null != cookies) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("token")) {
//                    System.out.println(cookie.getValue());
//                    User user = userMapper.findByToken(cookie.getValue());
//                    System.out.println("my is 过滤器 user  " + user);
//                    request.getSession().setAttribute("user", user);
//                    System.out.println(user);
//                    break;
//                }
//            }
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
