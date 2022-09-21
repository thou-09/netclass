package com.itany.netclass.filter;

import com.itany.netclass.entity.User;
import com.itany.netclass.exception.StatusErrorException;
import com.itany.netclass.exception.UserNotFindException;
import com.itany.netclass.factory.ObjectFactory;
import com.itany.netclass.service.UserService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录 Filter
 *
 * @author Thou
 * @date 2022/9/9
 */
@WebFilter(urlPatterns = {"/*"}, filterName = "loginFilter")
public class LoginFilter implements Filter {

    private final List<String> whiteList = new ArrayList<>();
    private final List<String> staticResources = new ArrayList<>();
    private final UserService userService = ObjectFactory.getObject("userService");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String contextPath = filterConfig.getServletContext().getContextPath();
        whiteList.add(contextPath + "/user/login.do");
        whiteList.add(contextPath + "/user/register.do");
        whiteList.add(contextPath + "/user/admin_login.do");
        whiteList.add(contextPath + "/user/code_image.do");
        whiteList.add(contextPath + "/user/forget.do");
        whiteList.add(contextPath + "/course_type/all.do");
        whiteList.add(contextPath + "/course/top4.do");
        whiteList.add(contextPath + "/course/top10.do");
        whiteList.add(contextPath + "/show_front_index.do");
        whiteList.add(contextPath + "/show_back_login.do");
        whiteList.add(contextPath + "/index.jsp");
        whiteList.add(contextPath + "/");

        staticResources.add("/css/");
        staticResources.add("/fonts/");
        staticResources.add("/iconfont/");
        staticResources.add("/images/");
        staticResources.add("/js/");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String uri = request.getRequestURI();

        // 静态资源放行
        for (String resource : staticResources) {
            if (uri.contains(resource)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        // 白名单放行
        if (whiteList.contains(uri)) {
            filterChain.doFilter(request, response);
            return;
        }

        boolean isLogin = false;
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        if (null != loginUser) {
            isLogin = true;
        }

        User loginAdmin = (User) request.getSession().getAttribute("loginAdmin");
        if (null != loginAdmin) {
            isLogin = true;
        }

        // auto login
        if (!isLogin) {
            Cookie[] cookies = request.getCookies();
            String loginName = "";
            String password = "";
            for (Cookie cookie : cookies) {
                if ("USERNAME".equals(cookie.getName())) {
                    loginName = cookie.getValue();
                }
                if ("PASSWORD".equals(cookie.getName())) {
                    password = cookie.getValue();
                }
            }
            if (!"".equals(loginName) && !"".equals(password)) {
                User user = new User();
                user.setLoginName(loginName);
                user.setPassword(password);
                try {
                    User u = userService.login(user);
                    request.getSession().setAttribute("loginUser", u);
                    isLogin = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (isLogin) {
            filterChain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
