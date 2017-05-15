<%--
  Created by IntelliJ IDEA.
  User: csl
  Date: 2017/5/14
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="backdrop hide" id="orderBackground"></div>
<div class="order-list-container hide" id="buyRecordList">
    <div class="order-title">购买记录</div>
    <div id="buyRecordClose" class="user-close">×</div>
    <div class="order-list">
        <%--<div class="order-item">--%>
            <%--<div class="order-goods">goods</div>--%>
            <%--<div class="order-seller">user</div>--%>
            <%--<div class="order-time">2014-03-09</div>--%>
            <%--<div class="order-operation" title="删除">x</div>--%>
        <%--</div>--%>
        <%--<div class="order-item">--%>
            <%--<div class="order-goods">goods</div>--%>
            <%--<div class="order-seller">user</div>--%>
            <%--<div class="order-time">2014-03-09</div>--%>
            <%--<div class="order-operation" title="删除">x</div>--%>
        <%--</div>--%>
        <%--<div class="order-item">--%>
            <%--<div class="order-goods">goods</div>--%>
            <%--<div class="order-seller">user</div>--%>
            <%--<div class="order-time">2014-03-09</div>--%>
            <%--<div class="order-operation" title="删除">x</div>--%>
        <%--</div>--%>
        <%--<div class="order-item">--%>
            <%--<div class="order-goods">goods</div>--%>
            <%--<div class="order-seller">user</div>--%>
            <%--<div class="order-time">2014-03-09</div>--%>
            <%--<div class="order-operation" title="删除">x</div>--%>
        <%--</div>--%>
        <%--<div class="order-item">--%>
            <%--<div class="order-goods">goods</div>--%>
            <%--<div class="order-seller">user</div>--%>
            <%--<div class="order-time">2014-03-09</div>--%>
            <%--<div class="order-operation" title="删除">x</div>--%>
        <%--</div>--%>
    </div>
</div>
<div class="order-list-container hide" id="sellRecordList">
    <div class="order-title">销售记录</div>
    <div id="sellRecordClose" class="user-close">×</div>
</div>
<script src="/script/orders/Orders.js?v=<%= new Date().getTime()%>"></script>