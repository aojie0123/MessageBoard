<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>留言板</title>
        <link rel="stylesheet" href="css/index.css">
        <script type="text/javascript">
            
        </script>
    </head>

    <body>
        <header>
            <div class="container">
                <% if (null != request.getSession().getAttribute("loginUser")) {%>
                    <nav>
                        <a href="${pageContext.request.contextPath}/MessageServlet?method=getMyMessage">我的留言</a>
                    </nav>
                    <nav>
                        <a href="${pageContext.request.contextPath}/UserServlet?method=myInformation">我的信息</a>
                    </nav>
                <%} else { %>
                    <nav>
                        <a href="${pageContext.request.contextPath}/LoginServlet?method=showPage">登录</a>
                        <a href="${pageContext.request.contextPath}/RegistServlet?type=showPage">注册</a>
                    </nav>
                <% } %>
            </div>
        </header>
        <section class="banner">
            <div class="container">
                <div>
                    <h1>慕课网留言板</h1>
                    <p>慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。在这里，你可以找到最好的互联网技术牛人，也可以通过免费的在线公开视频课程学习国内领先的互联网IT技术。 </p>
                </div>
            </div>
        </section>
        <section class="main">
            <div class="container">
                <c:forEach var="message" items="${msgList}">
                    <div class="alt-item">
                        <div class="alt-head">
                            <div class="alt-info">
                                <span>作者：<a href="">${message.username}</a></span>
                                <span>时间：${message.creatTime}</span>
                            </div>
                        </div>
                        <div class="alt-content">
                            <h3>${message.title}</h3>
                            <p>${message.content}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>
        <section class="page">
            <div class="container">
           <!-- 分页内容参考视频中老师源码 -->
            </div>
        </section>
        <footer>
            copy@慕课网
        </footer>
    </body>
</html>