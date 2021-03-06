package com.imooc.web.action;

import com.imooc.domain.User;
import com.imooc.service.UserService;
import com.imooc.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistServlet", urlPatterns = {"/RegistServlet"})
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");

        switch (type) {
            case "showPage":
                showPage(request, response);
                break;

            case "regist":
                regist(request, response);
                break;

            default:
        }
    }

    private void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPass = request.getParameter("confirmPass");
        String code = request.getParameter("verificationCode");

        if (!confirmPass.equals(password)) {
            request.setAttribute("msg", "两次密码不同，请重新输入密码");
            request.getRequestDispatcher("/reg.jsp").forward(request, response);
        } else if (!code.equals(request.getSession().getAttribute("code"))) {
            request.setAttribute("msg", "验证码错误");
            request.getRequestDispatcher("/reg.jsp").forward(request, response);
        } else {
            UserService userService = new UserServiceImpl();
            String msg = null;
            User user = new User();
            user.setUsername(name);
            user.setPassword(password);
            msg = userService.regist(user);
            if ("success".equals(msg)) {
                request.getRequestDispatcher("/LoginServlet?method=showPage").forward(request, response);
            }
        }
    }

    private void showPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/reg.jsp").forward(request, response);
    }
}
