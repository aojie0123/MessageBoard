<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>注册</title>
        <link rel="stylesheet" href="css/reg.css">

        <script type="text/javascript">
            function changeImg() {
                var img = document.getElementById("img");
                img.src = "code.jsp?date=" + new Date().getTime();
            }

            
        </script>
    </head>
    <body>
    <div class="reg">
        <div class="header">
            <h1>
                <a href="">登录</a>
                <a href="">注册</a>
            </h1>
            <font color="red">${msg}</font>
            <button></button>
        </div>
        <form action="${pageContext.request.contextPath}/RegistServlet?type=regist" method="post">
            <div class="username">
                <input type="text" id="username" name="username" placeholder="请输入用户名">
                <p></p>
            </div>
            <div class="pwd">
                <input type="password" id="pwd1" name="password" placeholder="6-16位密码，区分大小写，不能用空格">
                <p></p>
            </div>
            <div class="confirm-pwd">
                <input type="password" id="pwd2" name="confirmPass" placeholder="确认密码">
                <p></p>
            </div>
            <div class="idcode">
                <input type="text" id="verificationCode" name="verificationCode" placeholder="请输入验证码">
                <a href='#' onclick="javascript:changeImg()">&nbsp;&nbsp;&nbsp;&nbsp;换一张</a>
                <span><img id="img" src="code.jsp"/></span>
                <div class="clear"></div>
            </div>
            <div class="btn-red">
                <input  type="submit" value="注册" id="reg-btn">
            </div>
        </form>
    </div>
    </body>
</html>
