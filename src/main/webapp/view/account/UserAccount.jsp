<%--
  Created by IntelliJ IDEA.
  User: csl
  Date: 2017/3/27
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="backdrop hide" id="userBackground"></div>
<form id="loginForm" class="user-dialog hide">
    <div class="user-title">登入系统</div>
    <div id="loginClose" class="user-close">×</div>
    <div class="user-account">
        <div class="user-information">
            <label for="loginEmail" class="hide">email</label>
            <input type="email" id="loginEmail" placeholder="邮箱"/>
        </div>
        <div class="user-information">
            <label for="loginPassword" class="hide">password</label>
            <input type="password" id="loginPassword" placeholder="密码"/>
        </div>
        <div class="user-operation">
            <input type="button" id="login" class="user-login" value="登入"/>
        </div>
        <div class="user-operation">
            <input type="button" id="registerAccount" class="user-operation-button" value="注册账号"/>
            <input type="button" id="forgetPassword" class="user-operation-button" value="忘记密码"/>
        </div>
    </div>
</form>
<form id="registerForm" class="user-dialog hide">
    <div class="user-title">账户注册</div>
    <div id="registerClose" class="user-close">×</div>
    <div class="user-account">
        <div class="user-information">
            <label for="registerEmail" class="hide">email</label>
            <input type="email" id="registerEmail" placeholder="邮箱地址"/>
        </div>
        <div class="user-information">
            <input type="password" class="hide"/>
            <input type="password" placeholder="设置密码(至少六位)"/>
        </div>
        <div class="user-information">
            <input type="text" class="validate-input" id="registerValidate" placeholder="验证码"/>
            <a id="validateImage" class="a-button validate-a"></a>
        </div>
        <div class="user-operation">
            <input type="button" id="register" class="user-login" value="注册"/>
        </div>
    </div>
</form>
<form id="forgetForm" class="user-dialog hide">
    <div class="user-title">修改密码</div>
    <div id="forgetClose" class="user-close">×</div>
    <div class="user-account">
        <div class="user-information">
            <label for="registerEmail" class="hide">email</label>
            <input type="email" id="forgetEmail" placeholder="邮箱地址"/>
        </div>
        <div class="user-information">
            <input type="password" class="hide"/>
            <input type="password" placeholder="设置密码"/>
        </div>
        <div class="user-information">
            <input type="text" class="validate-input" id="forgetValidate" placeholder="验证码"/>
            <a id="sendValidateCode" class="a-button validate-a">发送验证码至邮箱</a>
        </div>
        <div class="user-operation">
            <label for="forgetConfirm" class="hide">button</label>
            <input type="button" id="forgetConfirm" class="user-login" value="确认"/>
        </div>
    </div>
</form>
<form id="userInformation" class="user-dialog hide">
    <div class="user-title">账户信息</div>
    <div class="user-account">
        <div class="user-information">
            <label for="userName" class="hide">name</label>
            <input type="text" id="userName" placeholder="用户名"/>
        </div>
        <div class="user-information">
            <label for="userTelephone" class="hide">telephone</label>
            <input type="text" id="userTelephone" placeholder="手机"/>
        </div>
        <div class="user-information">
            <select onchange="chinaChange(this,document.getElementById('city'));"
                    style=" width: 120px;; height:30px;line-height:30px; padding: 0;">
                <option value="请选择市区">请选择省份</option>
                <option value="北京市">北京市</option>
                <option value="天津市">天津市</option>
                <option value="上海市">上海市</option>
                <option value="重庆市">重庆市</option>
                <option value="河北省">河北省</option>
                <option value="山西省">山西省</option>
                <option value="辽宁省">辽宁省</option>
                <option value="吉林省">吉林省</option>
                <option value="黑龙江省">黑龙江省</option>
                <option value="江苏省">江苏省</option>
                <option value="浙江省">浙江省</option>
                <option value="安徽省">安徽省</option>
                <option value="福建省">福建省</option>
                <option value="江西省">江西省</option>
                <option value="山东省">山东省</option>
                <option value="河南省">河南省</option>
                <option value="湖北省">湖北省</option>
                <option value="湖南省">湖南省</option>
                <option value="广东省">广东省</option>
                <option value="海南省">海南省</option>
                <option value="四川省">四川省</option>
                <option value="贵州省">贵州省</option>
                <option value="云南省">云南省</option>
                <option value="陕西省">陕西省</option>
                <option value="甘肃省">甘肃省</option>
                <option value="青海省">青海省</option>
                <option value="台湾省">台湾省</option>
                <option value="广西">广西</option>
                <option value="内蒙">内蒙古</option>
                <option value="西藏">西藏</option>
                <option value="宁夏">宁夏</option>
                <option value="新疆">新疆</option>
                <option value="香港">香港</option>
                <option value="澳门">澳门</option>
            </select>
            <select name="city" id="city" style=" width: 125px;; height:30px;line-height:30px;padding: 0;">
                <option value="请选择市区">请选择市区</option>
            </select>
        </div>
        <div class="user-operation">
            <label for="userConfirm" class="hide">button</label>
            <input type="button" id="userConfirm" class="user-login" value="确认"/>
        </div>
    </div>
</form>
<script src="/script/account/UserAccount.js?v=<%=new Date().getTime()%>"></script>
<script src="/script/account/UserCity.js?v=<%=new Date().getTime()%>"></script>