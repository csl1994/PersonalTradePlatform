<%--
  Created by IntelliJ IDEA.
  User: csl
  Date: 2017/4/18
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.csl.domain.GoodsDO" %>
<%@ page import="com.csl.domain.UserDO" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
    <script src="/script/jquery-3.1.1.js"></script>
    <script src="/script/jquery.cookie.js"></script>
    <script src="/script/jquery.md5.js"></script>
</head>
<body>
<div class="head">
    <%@include file="/view/common/PageHead.jsp" %>
</div>
<div class="content">
    <%
        GoodsDO goods = (GoodsDO) request.getAttribute("goods");
        UserDO userDO = (UserDO) request.getAttribute("user");
    %>
    <div class="specific-container specific-attribute">
        <div class="image-container specific-border">
            <input type="hidden" id="goodsID" value=${goods.ID}>
            <img src=${goods.imageUrl} alt="image" class="goods-image"/>
        </div>
        <div class="specific-operation-container specific-border">
            <div class="specific-operation operation-goods-name">
                ${goods.name}
            </div>
            <div class="specific-operation">
                <div class="operation-kind">价格</div>
                <span class="operation-goods-price">￥${goods.price}</span>
            </div>
            <div class="specific-operation">
                <div class="operation-kind">关注</div>
                <span class="operation-goods-attention">${goods.attentionDegree}</span>
                <%
                    if (goods.isCurrentUserAttend()) {
                        out.print("<a class='a-button operation-collection js-sub' id='collectionGoods'>收藏-1</a>");
                    } else {
                        out.print("<a class='a-button operation-collection js-add' id='collectionGoods'>收藏+1</a>");
                    }
                %>
            </div>
            <div class="specific-operation">
                <div class="operation-kind">掌柜</div>
                <span class="" id="sellerName">${user.name}</span>
                <input type="hidden" value="${user.ID}" id="sellerID" />
            </div>
            <div class="specific-operation">
                <div class="operation-kind">信誉</div>
                <div class="operation-goods-credit">
                    <div class="credit-value" style="width: ${user.credit*2}px;" title="${user.credit}/100"></div>
                </div>
            </div>
            <div class="specific-operation">
                <a class="a-button operation-order" id="orderGoods">联系掌柜</a>
                <a class="a-button operation-order" id="attentionUser" style="display: none">关注掌柜</a>
                <a class="a-button operation-order" id="unAttentionUser" style="display: none">不再关注</a>
            </div>
        </div>
    </div>
    <div class="specific-select">
        <a class="a-button operation-select-a current-a" id="goodsBrief">简介</a>
        <a class="a-button operation-select-a" id="goodsParameters">参数</a>
    </div>
    <div class="specific-container specific-border specific-description">
        <div id="brief" class="">
            ${goods.description}
        </div>
        <div id="parameter" class="hide">
            <div class="specific-operation">
                <div class="parameter-kind">颜色</div>
                <%
                    if (goods.getColor() == "" || goods.getColor() == null) {
                        out.print("<span>无</span>");
                    } else {
                        out.print("<span>" + goods.getColor() + "</span>");
                    }
                %>
            </div>
            <div class="specific-operation">
                <div class="parameter-kind">尺寸(mm)</div>
                <span>${goods.length}(l)*${goods.width}(w)*${goods.height}(h)</span>
            </div>
            <div class="specific-operation">
                <div class="parameter-kind">录入日期</div>
                <%
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateTime = new Date(goods.getDatetime());
                    String date = dateFormat.format(dateTime);
                    out.print("<span class=\"operation-date\">" + date + "</span>");
                %>
            </div>
            <div class="specific-operation">
                <div class="parameter-kind">生产日期</div>
                <%
                    Date createDateTime = new Date(goods.getCreateDate());
                    String createDate = dateFormat.format(createDateTime);
                    out.print("<span class=\"operation-date\">" + createDate + "</span>");
                %>
            </div>
        </div>
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
<%@include file="/view/goods/GoodsOwner.jsp" %>
<%@include file="/view/account/UserAccount.jsp" %>
<script src="/script/goods/GoodsSpecific.js?v=<%=new Date().getTime()%>"></script>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
</html>
