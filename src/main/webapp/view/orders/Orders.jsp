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
    <div class="order-specific hide">
        <div>
            <label class="label-medium order-user-label">卖家邮箱:</label>
            <input type="text" value="" class="js-email order-user" readOnly="true"/>
        </div>
        <div>
            <label class="label-medium order-user-label">卖家电话:</label>
            <input type="text" value="" class="js-phone order-user" readOnly="true"/>
        </div>
        <div><span class="span-medium my-grade">我的评分:</span>
            <div class="span-medium my-grade-show">123</div>
        </div>
        <label for="sellerGrade" class="label-medium">卖家评分:</label>
        <input type="number" value="" id="sellerGrade" placeholder="0~100" class="input-medium"/>
        <div>
            <input type="button" value="提交" id="buySubmit"/>
        </div>
    </div>
    <div class="order-list hide">
    </div>
</div>
<div class="order-list-container hide" id="sellRecordList">
    <div class="order-title">销售记录</div>
    <div id="sellRecordClose" class="user-close">×</div>
    <div class="order-specific hide">
        <div>
            <label class="label-medium order-user-label">买家邮箱:</label>
            <input type="text" value="" class="js-email order-user" readOnly="true"/>
        </div>
        <div>
            <label class="label-medium order-user-label">买家电话:</label>
            <input type="text" value="" class="js-phone order-user" readOnly="true">
        </div>
        <div><span class="span-medium my-grade">我的评分:</span>
            <div class="span-medium my-grade-show">123</div>
        </div>
        <label for="buyerGrade" class="label-medium">买家评分:</label>
        <input type="number" value="" id="buyerGrade" placeholder="0~100" class="input-medium"/>
        <div>
            <input type="button" value="提交" id="sellSubmit"/>
        </div>
    </div>
    <div class="order-list hide">
    </div>
</div>
<script src="/script/orders/Orders.js?v=<%= new Date().getTime()%>"></script>