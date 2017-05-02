<%--
  Created by IntelliJ IDEA.
  User: csl
  Date: 2017/4/21
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="backdrop hide" id="ownerBackground"></div>
<div id="goodsOwner" class="user-dialog-owner hide">
    <div class="user-information">
        <div class="message-name">电话</div>
        <div id="userTelephone" class="message-content"></div>
    </div>
    <div class="user-information">
        <div class="message-name">邮箱</div>
        <div id="userEmail" class="message-content"></div>
    </div>
    <div class="user-operation">
        <input type="button" class="user-operation-button" id="buyButton" value="立刻购买"/>
        <input type="button" class="user-operation-button" id="bayButton" value="以后再说"/>
    </div>
</div>
<script src="/script/goods/GoodsOnwer.js?v=<%=new Date().getTime()%>"></script>