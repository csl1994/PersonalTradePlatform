<%--
  Created by IntelliJ IDEA.
  User: csl
  Date: 2017/5/2
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</head>
<body>
<div class="head">
    <%@include file="view/common/PageHead.jsp" %>
</div>
<div class="content">
    <div class="content-title">
        我的柜台
    </div>
    <div id="goodsRepository">
    </div>
    <div class="goods-page-list"></div>
</div>
</body>
<script src="/script/goods/GoodsRepository.js?v=<%=new Date().getTime()%>"></script>
</html>
