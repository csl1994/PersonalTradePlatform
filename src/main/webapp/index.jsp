<%@ page import="com.csl.domain.SortBasis" %>
<%@ page import="com.csl.domain.GoodsKind" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--Created by IntelliJ IDEA.--%>
<%--User: csl--%>
<%--Date: 2017/3/14--%>
<%--Time: 10:42--%>
<%--To change this template use File | Settings | File Templates.--%>
<html>
<head>
    <title>Personal Trade Platform</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8 "/>
    <meta http-equiv= "Pragma" content= "no-cache" />
    <meta http-equiv= "Cache-Control" content= "no-cache" />
    <meta http-equiv= "Expires" content= "0" />
    <link rel="stylesheet" href="content/style/mySite.css?v=233"/>
    <script src="script/jquery-3.1.1.js"></script>
    <script src="script/jquery.cookie.js"></script>
    <script src="script/jquery.md5.js"></script>
    <script src="script/jquery.validate.min.js"></script>
    <script src="script/jquery.metadata.js"></script>
</head>
<body>
<input type="hidden" id="goodsKind" value=""/>
<input type="hidden" id="sortBasis" value=<%=SortBasis.NONE%> />
<input type="hidden" id="page" value="1"/>
<div class="">
    <div class="head">
        <%@include file="view/common/PageHead.jsp" %>
    </div>
    <div class="content">
        <%--<div>--%>
        <%--轮播--%>
        <%--</div>--%>
        <div class="goods-filter">
            <a class="filter-name a-button" href="javascript:void(0)">全部</a>
            <a class="filter-name a-button" href="javascript:void(0)" data-kind=<%=GoodsKind.clothes%>>服装</a>
            <a class="filter-name a-button" href="javascript:void(0)" data-kind=<%=GoodsKind.digitalProduct%>>数码</a>
            <a class="filter-name a-button" href="javascript:void(0)" data-kind=<%=GoodsKind.sportsProduct%>>体育</a>
            <a class="filter-name a-button" href="javascript:void(0)" data-kind=<%=GoodsKind.food%>>食物</a>
            <a class="filter-name a-button" href="javascript:void(0)" data-kind=<%=GoodsKind.dailyUse%>>日常用品</a>
            <a class="filter-name a-button" href="javascript:void(0)" data-kind=<%=GoodsKind.schoolThings%>>学习用品</a>
            <a class="filter-name a-button" href="javascript:void(0)" data-kind=<%=GoodsKind.luxury%>>奢侈品</a>
            <a class="filter-name a-button" href="javascript:void(0)" data-kind=<%=GoodsKind.others%>>其他</a>
        </div>
        <div class="goods-filter">
            <a class="filter-name a-button js-sort" href="javascript:void(0)" data-kind=<%=SortBasis.NONE%>>默认排序</a>
            <a class="filter-name a-button" href="javascript:void(0)">
                <div class="float-left">按收藏</div>
                <div class="float-left container-image">
                    <div class="sort-asc js-sort" data-kind=<%=SortBasis.ATTENTIONASC %>></div>
                    <div class="sort-desc js-sort" data-kind=<%=SortBasis.ATTENTIONDESC %>></div>
                </div>
            </a>
            <a class="filter-name a-button" href="javascript:void(0)">
                <div class="float-left">按价格</div>
                <div class="float-left container-image">
                    <div class="sort-asc js-sort" data-kind=<%=SortBasis.PRICEASC %>></div>
                    <div class="sort-desc js-sort" data-kind=<%=SortBasis.PRICEDESC %>></div>
                </div>
            </a>
            <a class="filter-name a-button" href="javascript:void(0)">
                <div class="float-left">按信用</div>
                <div class="float-left container-image">
                    <div class="sort-asc js-sort" data-kind=<%=SortBasis.CREDITASC %>></div>
                    <div class="sort-desc js-sort" data-kind=<%=SortBasis.CREDITDESC %>></div>
                </div>
            </a>
        </div>
        <div id="goodsList"></div>
        <div class="goods-page-list"></div>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
    </div>
</div>
<script src="script/common/GoodsList.js?v=972"></script>
<script src="script/common/AjaxMethod.js?v=21"></script>
<script src="script/home/HomePage.js?v=6767"></script>
<%@include file="view/account/UserAccount.jsp" %>
</body>
</html>