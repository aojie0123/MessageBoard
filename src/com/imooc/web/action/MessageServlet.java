package com.imooc.web.action;

import com.imooc.domain.Message;
import com.imooc.domain.User;
import com.imooc.service.MessageService;
import com.imooc.service.impl.MessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MessageServlet", urlPatterns = {"/MessageServlet"})
public class MessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");

        if (method.equals("showAllMsg")) {
            showAllMsg(request, response);
        } else if (method.equals("getMyMessage")) {
            getMyMessage(request, response);
        } else if (method.equals("edit")) {
            edit(request, response);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int mid = Integer.parseInt(request.getParameter("mid"));
        MessageService messageService = new MessageServiceImpl();

        Message message = messageService.findOne(mid);
        request.setAttribute("user", (User) request.getSession().getAttribute("loginUser"));
        request.setAttribute("message", message);
        request.getRequestDispatcher("/edit_message.jsp").forward(request, response);
    }

    private void getMyMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MessageService messageService = new MessageServiceImpl();
        List<Message> msgList = messageService.findMyMessage((User) request.getSession().getAttribute("loginUser"));

        request.setAttribute("msgList", msgList);
        request.getRequestDispatcher("/my_message_list.jsp").forward(request, response);
    }

    private void showAllMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MessageService messageService = new MessageServiceImpl();
        List<Message> list = messageService.findAllMessage();

        request.setAttribute("msgList", list);
        request.getRequestDispatcher("/message_list.jsp").forward(request, response);
    }
}
