<%--
  Created by IntelliJ IDEA.
  User: csl
  Date: 2017/4/25
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page import="java.util.Date" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Personal Trade Platform</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8 "/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <link rel="stylesheet" href="/content/style/mySite.css?v=<%=new Date().getTime()%>"/>
    <script src="/script/common/AjaxMethod.js?v=<%=new Date().getTime()%>"></script>
    <script src="/script/common/HrefMethod.js?v=<%=new Date().getTime()%>"></script>
    <script src="/script/common/GoodsList.js?v=<%=new Date().getTime()%>"></script>
    <script src="/script/jquery-3.1.1.js"></script>
    <script src="/script/jquery.cookie.js"></script>
    <script src="/script/jquery.md5.js"></script>
</head>
<body>
<div class="head">
    <%@include file="/view/common/PageHead.jsp" %>
</div>
<div class="content">
    <div class="content-title">
        我的收藏
    </div>
    <div id="goodsCollection">
    </div>
</div>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<%@include file="/view/common/PageFoot.jsp"%>
<br/>
<br/>
</body>
<script src="/script/goods/UserCollection.js?v<%=new Date().getTime()%>"></script>
</html>