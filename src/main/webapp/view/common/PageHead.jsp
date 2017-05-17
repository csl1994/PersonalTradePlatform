<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<input type="hidden" id="userID" value=""/>
<input type="hidden" id="region" value=""/>
<div class="page-head">
    <div class="home-button-div">
        <a class="a-button home-button" href="javascript:void(0)">
            <img src="/content/image/home.png" id="buttonHome" alt="主页"/>
        </a>
    </div>
    <div class="head-button-div">
        <a class="a-button head-button" id="buttonRegion" href="javascript:void(0)">
            地区
            <div class="region-image"></div>
        </a>
    </div>
    <div class="head-button-div">
        <a class="a-button head-button" id="buttonCollection" href="javascript:void(0)">收藏</a>
    </div>
    <div class="head-button-div">
        <a class="a-button head-button" id="buttonStore" href="javascript:void(0)">柜台</a>
    </div>
    <%--<div class="head-button-div">--%>
        <%--<a class="a-button head-button" id="buttonExplain" href="javascript:void(0)">说明</a>--%>
    <%--</div>--%>
    <div class="head-button-div">
        <a class="a-button head-button" id="buttonLogin" href="javascript:void(0)">登入</a>
    </div>
    <div class="head-button-div">
        <div class="pop-menu hide">
            <a class="a-button head-button" id="user" href="javascript:void(0)">user</a>
            <div class="sub-menu hide">
                <a class="a-button menu-a" id="buyRecord">购买记录</a>
                <a class="a-button menu-a" id="sellFRecord">销售记录</a>
                <a class="a-button menu-a" id="addGoods">新增物品</a>
                <a class="a-button menu-a" id="exit">退出系统</a>
            </div>
        </div>
    </div>
</div>
<%@include file="/view/orders/Orders.jsp" %>
<script src="/script/common/PageHead.js?v=<%=new Date().getTime()%>"></script>
<script type="text/javascript" src="/script/common/OrdersList.js?version=<%=new Date().getTime()%>"></script>
<script type="text/javascript" src="/script/common/Timer.js?v=<%=new Date().getTime()%>"></script>