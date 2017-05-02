<%--
  Created by IntelliJ IDEA.
  User: csl
  Date: 2017/4/25
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="wrapper">
    <div class="banner">
        <ul class="img-list" id="carouselImage">
        </ul>
        <img src="content/image/arrow_left_white.png" class="previous" id="previousGoods">
        <img src="content/image/arrow_right_white.png" class="next" id="nextGoods">
        <ul class="index-list" id="carouselIndex">
        </ul>
    </div>
</div>
<script src="script/common/Carousel.js?v=<%=new Date().getTime()%>"></script>