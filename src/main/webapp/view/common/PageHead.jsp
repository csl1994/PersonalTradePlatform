<%--
  Created by IntelliJ IDEA.
  User: csl
  Date: 2017/3/15
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<input type="hidden" id="userID" value=""/>
<input type="hidden" id="region" value=""/>
<div class="page-head">
    <div class="home-button-div">
        <a class="a-button home-button" id="buttonHome" href="javascript:void(0)">
            <img src="/content/image/home.png" alt="主页"/>
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
    <div class="head-button-div">
        <a class="a-button head-button" id="buttonExplain" href="javascript:void(0)">说明</a>
    </div>
    <div class="head-button-div">
        <a class="a-button head-button" id="buttonLogin" href="javascript:void(0)">登入</a>
    </div>
    <div class="hide">
        <a class="a-button head-button" id="user" href="javascript:void(0)">user</a>
    </div>
</div>
<script src="script/common/PageHead.js?v=23"></script>