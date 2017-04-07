<%--
  Created by IntelliJ IDEA.
  User: csl
  Date: 2017/3/27
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="backdrop hide"></div>
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
            <input type="button" id="registerAccount" class="user-register" value="注册账号"/>
            <input type="button" id="forgetPassword" class="user-forget" value="忘记密码"/>
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
            <input type="password" placeholder="设置密码"/>
        </div>
        <div class="user-information">
            <input type="text" class="validateInput" id="registerValidate" placeholder="验证码"/>
            <a id="validateImage" class="a-button validateImage"><img src="/validateCode/1491486765268.PNG" alt="validate"/></a>
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
            <input type="text" class="validateInput" id="forgetValidate" placeholder="验证码"/>
            <a id="getValidateCode" class="a-button validateImage">获取验证码</a>
        </div>
        <div class="user-operation">
            <input type="button" id="confirm" class="user-login" value="确认"/>
        </div>
    </div>
</form>
<script src="script/account/UserAccount.js?v=98"></script>