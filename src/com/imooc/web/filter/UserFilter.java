package com.imooc.web.filter;

import com.imooc.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "UserFilter", urlPatterns = {"/MessageServlet", "/UserServlet", "/AddEditMessageServlet"})
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            request.setAttribute("msg", "您尚未登录，请先登录");
            request.getRequestDispatcher("/login.jsp").forward(request, (HttpServletResponse) resp);
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
