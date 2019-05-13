<%@ page import="com.imooc.utils.CaptcahCodeUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**  1、清空浏览器缓存，目的是为了清空浏览器的缓存，因为浏览器会对网站的资源文件和图片进行记忆存储，
     *      如果被浏览器加载过得图片就记忆起来，记忆以后，文件就不会和服务器再交互，如果不清空，刷新后验证码没有效果
     */
    response.setHeader("pragma","no-cache");
    response.setHeader("cache-control","no-cache");
    response.setHeader("expries","0");

    //  2：调用编写的生成验证码的工具
    String code = CaptcahCodeUtils.drawImageVerificate(response);
    session.setAttribute("code",code);

    out.clear();
    out = pageContext.pushBody();
%>
