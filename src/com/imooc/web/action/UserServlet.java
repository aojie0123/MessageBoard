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

@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");

        switch (method) {
            case "myInformation":
                myInformation(request, response);
                break;

            case "editInformation":
                editInformation(request, response);
                break;

            default:
        }
    }

    private void editInformation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int uid = Integer.parseInt(request.getParameter("uid"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserService userService = new UserServiceImpl();
        User user = new User();

        user.setUid(uid);
        user.setUsername(username);
        user.setPassword(password);

        userService.editInformation(user);
        User loginUser = userService.findUser(user);
        request.getSession().setAttribute("loginUser", user);

        response.sendRedirect(request.getContextPath() + "/MessageServlet?method=showAllMsg");
    }

    private void myInformation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("user", request.getSession().getAttribute("loginUser"));
        request.getRequestDispatcher("/user.jsp").forward(request, response);
    }
}
