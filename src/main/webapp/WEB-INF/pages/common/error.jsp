<%--
  Created by IntelliJ IDEA.
  User: zf
  Date: 2020/8/21
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>失败页面</title>
</head>
<body>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
    String url = basePath+"index.jsp";   //设置跳转的地址
%><html>
<head>

    <meta http-equiv=refresh content=5;url=<%=url %>>
</head>
<body >
<div><h2>你没有权限访问</h2></div>
<b style=color:blue><span id=jump>5</span> 秒钟后页面将自动返回登录页面...</b>

</body>
</html>

<script>
    function countDown(secs){
        jump.innerText=secs;
        if(--secs>0)
            setTimeout("countDown("+secs+" )",1000);
    }
    countDown(5);

</script>

</body>
</html>
