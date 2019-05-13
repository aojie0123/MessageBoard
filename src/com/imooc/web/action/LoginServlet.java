package com.imooc.web.action;

import com.imooc.domain.Message;
import com.imooc.domain.User;
import com.imooc.service.MessageService;
import com.imooc.service.UserService;
import com.imooc.service.impl.MessageServiceImpl;
import com.imooc.service.impl.UserServiceImpl;
import com.imooc.utils.CaptcahCodeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if ("showPage".equals(method)) {
            showPage(request, response);
        } else if ("login".equals(method)) {
            login(request, response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("verificationCode");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        UserService userService = new UserServiceImpl();
        MessageService messageService = new MessageServiceImpl();

        List<Message> msgList = new ArrayList<>();
        if (username == null) {
            request.setAttribute("msg", "用户名为空");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else if (password == null) {
            request.setAttribute("msg", "密码为空");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else if (!code.equals(request.getSession().getAttribute("code"))) {
            request.setAttribute("msg", "验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            User loginUser = userService.login(user);
            if (loginUser != null) {
                msgList = messageService.findAllMessage();

                request.setAttribute("msgList", msgList);
                request.getSession().setAttribute("loginUser", loginUser);
                request.getRequestDispatcher("/message_list.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "用户名密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
    }

    private void showPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
