package com.imooc.web.action;

import com.imooc.domain.Message;
import com.imooc.service.MessageService;
import com.imooc.service.impl.MessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddEditMessageServlet", urlPatterns = {"/AddEditMessageServlet"})
public class AddEditMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");

        if (method.equals("update")) {
            update(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        Message message = new Message();
        message.setMid(id);
        message.setTitle(title);
        message.setContent(content);

        MessageService messageService = new MessageServiceImpl();
        messageService.update(message);
        response.sendRedirect(request.getContextPath() + "/MessageServlet?method=getMyMessage");
    }
}
