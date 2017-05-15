<%--
  Created by IntelliJ IDEA.
  User: csl
  Date: 2017/5/3
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.csl.domain.GoodsDO" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.csl.domain.GoodsKind" %>
<%@ page import="com.csl.domain.GoodsStatus" %>
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
        String pageType = (String) request.getAttribute("pageType");
    %>
    <input type="hidden" value="${pageType}" id="pageType"/>
    <div class="specific-container specific-attribute">
        <div class="image-container specific-border">
            <form id="imageForm" method="POST" enctype="multipart/form-data" action="/goods/upImage.html"
                  target="url">
                <input type="hidden" id="goodsID" value=${goods.ID}>
                <input type="file" class="filePath" name="file"/>
                <%
                    if (goods.getImageUrl() == "" || goods.getImageUrl() == null) {
                        out.print("<span class=\"goods-close hide\">x</span><img id='camera' src=\"/content/image/camera.png\" class=\"image-container-img\"><img id=\"imageUrl\" src='" + goods.getImageUrl() + "' alt=\"image\" class=\"image-container-img hide\"/>");
                    } else {
                        out.print("<span class=\"goods-close\">x</span><img id='camera' src=\"/content/image/camera.png\" class=\"image-container-img hide\"><img id=\"imageUrl\" src='" + goods.getImageUrl() + "' alt=\"image\" class=\"image-container-img\"/>");
                    }
                %>
            </form>
            <iframe id="url" name="url" class="hide"></iframe>
        </div>
        <div class="specific-operation-container specific-border">
            <div class="specific-operation">
                <div class="operation-kind">名字</div>
                <span class="must-mark">*</span><input type="text" class="input-large" id="goodsName"
                                                       value="${goods.name}"/>
            </div>
            <div class="specific-operation">
                <div class="operation-kind">价格</div>
                <span class="must-mark">*</span><input type="text" class="goods-attribute" id="goodsPrice"
                                                       value="${goods.price}"/>
            </div>
            <%
                String str0 = "<option value='" + GoodsKind.clothes + "'>服装</option>";
                String str1 = "<option value='" + GoodsKind.digitalProduct + "'>数码</option>";
                String str2 = "<option value='" + GoodsKind.sportsProduct + "'>体育</option>";
                String str3 = "<option value='" + GoodsKind.food + "'>食物</option>";
                String str4 = "<option value='" + GoodsKind.dailyUse + "'>日常用品</option>";
                String str5 = "<option value='" + GoodsKind.schoolThings + "'>学习用品</option>";
                String str6 = "<option value='" + GoodsKind.luxury + "'>奢侈品</option>";
                String str7 = "<option value='" + GoodsKind.others + "'>其他</option>";
                if (goods.getKind() != null) {
                    switch (goods.getKind()) {
                        case "clothes":
                            str0 = "<option selected='selected' value='" + GoodsKind.clothes + "'>服装</option>";
                            break;
                        case "digitalProduct":
                            str1 = "<option selected='selected' value='" + GoodsKind.digitalProduct + "'>数码</option>";
                            break;
                        case "sportsProduct":
                            str2 = "<option selected='selected' value='" + GoodsKind.sportsProduct + "'>体育</option>";
                            break;
                        case "food":
                            str3 = "<option selected='selected' value='" + GoodsKind.food + "'>食物</option>";
                            break;
                        case "dailyUse":
                            str4 = "<option selected='selected' value='" + GoodsKind.dailyUse + "'>日常用品</option>";
                            ;
                            break;
                        case "schoolThings":
                            str5 = "<option selected='selected' value='" + GoodsKind.schoolThings + "'>学习用品</option>";
                            break;
                        case "luxury":
                            str6 = "<option selected='selected' value='" + GoodsKind.luxury + "'>奢侈品</option>";
                            break;
                        case "others":
                            str7 = "<option selected='selected' value='" + GoodsKind.others + "'>其他</option>";
                            break;
                        default:
                            break;
                    }
                }
            %>
            <div class="specific-operation">
                <div class="operation-kind">种类</div>
                <span class="must-mark">*</span>
                <%
                    out.print("<select id=\"goodsKind\">" + str0 + str1 + str2 + str3 + str4 + str5 + str6 + str7 + "</select>");
                %>
            </div>
            <div class="specific-operation">
                <div class="operation-kind">关注</div>
                <span id="goodsAttention" class="goods-attribute">${goods.attentionDegree}</span>
            </div>
            <%
                String status = "";
                if (goods.getStatus() != null) {
                    switch (GoodsStatus.valueOf(goods.getStatus())) {
                        case sold:
                            status = "已售出";
                            break;
                        case unsold:
                            status = "未售出";
                            break;
                    }
                }
            %>
            <div class="specific-operation">
                <div class="operation-kind">状态</div>
                <span class="goods-attribute" data-status="${goods.status}" id="goodsStatus">${status}</span>
            </div>
        </div>
    </div>
    <div class="specific-select">
        <a class="a-button operation-select-a current-a" id="goodsBrief">简介</a>
        <a class="a-button operation-select-a" id="goodsParameters">参数</a>
    </div>
    <div class="specific-container specific-border specific-description">
        <div id="brief">
            <span class="must-mark">*</span>
            <div id="goodsDescription" class="goods-brief" contenteditable="true">
                ${goods.description}
            </div>
        </div>
        <div id="parameter" class="hide">
            <div class="specific-operation">
                <div class="parameter-kind">颜色</div>
                <input type="text" class="input-small" id="goodsColor" value="${goods.color}"/>
            </div>
            <div class="specific-operation">
                <div class="parameter-kind">尺寸(mm)</div>
                <label for="goodsLength" class="label-small">length</label>
                <input type="text" class="input-small" id="goodsLength" value="${goods.length}"/>
                <label for="goodsWidth" class="label-small">width</label>
                <input type="text" class="input-small" id="goodsWidth" value="${goods.width}"/>
                <label for="goodsHeight" class="label-small">height</label>
                <input type="text" class="input-small" id="goodsHeight" value="${goods.height}"/>
            </div>
            <div class="specific-operation">
                <div class="parameter-kind">录入日期</div>
                <%
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    if (goods.getDatetime() == null) {
                        out.print("<span class=\"must-mark\">*</span><input class=\"operation-date\" type=\"date\" id=\"logDate\" />");
                    } else {
                        Date dateTime = new Date(goods.getDatetime() == null ? 0 : goods.getDatetime());
                        String date = dateFormat.format(dateTime);
                        out.print("<span class=\"must-mark\">*</span><input class=\"operation-date\" type=\"date\" id=\"logDate\" value=\"" + date + "\" />");
                    }
                %>
            </div>
            <div class="specific-operation">
                <div class="parameter-kind">生产日期</div>
                <%
                    if (goods.getCreateDate() == null) {
                        out.print("<span class=\"must-mark\">*</span><input class=\"operation-date\" type=\"date\" id=\"createDate\" />");
                    } else {
                        Date createDateTime = new Date(goods.getCreateDate() == null ? 0 : goods.getDatetime());
                        String createDate = dateFormat.format(createDateTime);
                        out.print("<span class=\"must-mark\">*</span><input class=\"operation-date\" type=\"date\" id=\"createDate\" value=\"" + createDate + "\" />");
                    }
                %>
            </div>
        </div>
    </div>
    <div>
        <%
            switch (pageType) {
                case "edit":
                    out.print("<input type=\"button\" class=\"commit\" value=\"保存\" id=\"commit\"/>" +
                            "<input type=\"button\" class=\"delete\" value=\"删除\" id=\"delete\"/>");
                    break;
                case "create":
                    out.print("<input type=\"button\" class=\"add\" value=\"添加\" id=\"add\"/>");
            }
        %>
    </div>
</div>
</body>
<script src="/script/goods/GoodsEdit.js?v=<%=new Date().getTime()%>"></script>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
</html>