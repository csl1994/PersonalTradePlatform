<%--
  Created by IntelliJ IDEA.
  User: csl
  Date: 2017/3/20
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="content" style="text-align: center; border-top: solid 1px #c35ac1; padding-top: 5px;">
    <span id="platformDescription" style="cursor: pointer;">平台介绍 |</span>
    <span id="platformRule" style="cursor: pointer;">| 平台须知</span>
</div>
<div class="backdrop hide" id="footBackground"></div>
<div class="user-dialog hide" id="first">
    <div class="user-title">平台介绍</div>
    <div id="firstClose" class="user-close">×</div>
    <div class="user-account">
        本平台为个人物品交易平台
    </div>
</div>
<div class="user-dialog hide" id="second">
    <div class="user-title">平台须知</div>
    <div id="secondClose" class="user-close">×</div>
    <div class="user-account">
        一旦注册即可进行买卖
    </div>
</div>
<script src="/script/common/PageFoot.js?v=<%=new Date().getTime()%>"></script>