/**
 * Created by csl on 2017/3/28.
 */
function UserAccount() {
    var userEmail = "";
    var userPassword = "";
    var userName = "";
    var telephone = "";
    var region = "";
    var acceptEmail = "";
    var ajax = new AjaxMethod();
    var innerHelper = {
        bindEvent: function () {
            $(".user-dialog").unbind("click").bind("click", function (event) {
                var element = event.toElement;
                var elementID = $(element).attr("id");
                switch (elementID) {
                    case "loginClose":
                        $("#userBackground").hide();
                        $("#loginForm").hide();
                        $("body").css("overflow", "auto");
                        break;
                    case "login":
                        innerHelper.checkUser();
                        //登入后刷新goods list
                        break;
                    case "registerAccount":
                        $("#loginForm").hide();
                        $("#registerForm").show();
                        innerHelper.generateValidateCode();
                        break;
                    case "registerClose":
                        $("#userBackground").hide();
                        $("#registerForm").hide();
                        $("body").css("overflow", "auto");
                        break;
                    case "register":
                        innerHelper.validateRegisterCode();
                        break;
                    case "forgetPassword":
                        $("#loginForm").hide();
                        $("#forgetForm").show();
                        break;
                    case "forgetClose":
                        $("#userBackground").hide();
                        $("#forgetForm").hide();
                        $("body").css("overflow", "auto");
                        break;
                    case "sendValidateCode":
                        innerHelper.sendValidateCode();
                        break;
                    case "forgetConfirm":
                        innerHelper.validateForgetCode();
                        break;
                    case "userConfirm":
                        innerHelper.checkInformation();
                        break;
                    default:
                        break;
                }
            });
        },
        checkUser: function () {
            userEmail = $.trim($("#loginEmail").val());
            userPassword = $.md5($.trim($("#loginPassword").val()));
            if (userEmail === "") {
                alert("邮箱不能为空");
            } else if (userPassword === "") {
                alert("密码不能为空");
            } else {
                innerHelper.validateUser();
            }
        },
        validateUser: function () {
            var defer = ajax.validateUser(userEmail, userPassword);
            defer.done(innerHelper.getUser).fail();
        },
        getUser: function (data) {
            if (data === "true") {
                var defer = ajax.getUserByEmail(userEmail);
                defer.done(innerHelper.saveToCookie).fail();
            } else {
                alert("账号或密码错误");
            }
        },
        saveToCookie: function (data) {
            $.cookie("userName", data.name, {expires: 7, path: "/"});
            $.cookie("userID", data.id, {expires: 7, path: "/"});
            $.cookie("userPassword", data.password, {path: "/"});
            $("#buttonLogin").parent("div").hide();
            $(".backdrop").hide();
            $(".user-dialog").hide();
            $("body").css("overflow", "auto");
            $("#user").parent("div").show();
            $("#user").text("").text(data.name);
            innerHelper.updateOrder();
            home.reLoad();
        },
        updateOrder: function () {
            var defer1 = ajax.newSellOrder($.cookie("userID"));
            defer1.done(innerHelper.updateSellOrder).fail();
            var defer2 = ajax.newBuyOrder($.cookie("userID"));
            defer2.done(innerHelper.updateBuyOrder).fail();
        },
        updateSellOrder: function (data) {
            if (data === true) {
                $("#user").parent().find("div").append("<span class='new-sell-mark'>new</span>");
            }
        },
        updateBuyOrder: function (data) {
            if (data === true) {
                $("#user").parent().find("div").append("<span class='new-buy-mark'>new</span>");
            }
        },
        generateValidateCode: function () {
            var defer = ajax.getValidateCode();
            defer.done(innerHelper.showValidateCode).fail();
        },
        showValidateCode: function (data) {
            var img = new Image();
            img.src = data;
            img.alt = "validate";
            img.className = "validate-img";
            img.title = "点击刷新";
            img.onclick = innerHelper.refreshValidateCode;
            img.onload = function () {
                $("#validateImage").empty().append(img);
            }
        },
        refreshValidateCode: function () {
            innerHelper.generateValidateCode();
        },
        validateRegisterCode: function () {
            var validateCode = $.trim($("#registerValidate").val());
            var defer = ajax.checkValidateCode(validateCode.toLowerCase(), 1);
            defer.done(innerHelper.checkRegisterEmail).fail();
        },
        checkRegisterEmail: function (data) {
            userEmail = $.trim($("#registerEmail").val());
            userPassword = $.trim($("#registerEmail").parent("div").next("div").children("input").last().val());
            if (data === "true") {
                if (innerHelper.checkEmailFormat(userEmail)) {
                    if (userPassword.length < 6) {
                        alert("密码长度小于6");
                    } else if (userPassword.length > 32) {
                        alert("密码过长");
                    } else {
                        userPassword = $.md5(userPassword);
                        var defer = ajax.checkEmail(userEmail);
                        defer.done(innerHelper.fillUserInformation).fail();
                    }
                }
                else {
                    alert("邮箱格式错误");
                }
            } else {
                alert("验证码错误");
                innerHelper.generateValidateCode();
            }
        },
        fillUserInformation: function (data) {
            if (data === "true") {
                $("#registerForm").hide();
                $("#userInformation").show();
            } else {
                alert("邮箱已被占用");
            }
        },
        checkInformation: function () {
            var regex = /^([0-9]{11})?$/;
            telephone = $.trim($("#userTelephone").val());
            userName = $.trim($("#userName").val());
            region = $.trim($("#city").val());
            if (!regex.test(telephone)) {
                alert("输入正确的号码");
            } else if (!userName || !telephone || !region || region === "请选择市区") {
                alert("请输入全部信息");
            } else {
                innerHelper.checkName();
            }
        },
        checkName: function () {
            var defer = ajax.checkName(userName);
            defer.done(innerHelper.checkTelephone).fail();
        },
        checkTelephone: function (data) {
            if (data === "true") {
                var defer = ajax.checkTelephone(telephone);
                defer.done(innerHelper.registerAccount).fail();
            } else {
                alert("用户名被占用");
            }
        },
        registerAccount: function (data) {
            if (data === "true") {
                var defer = ajax.registerAccount(userEmail, userPassword, userName, telephone, region);
                defer.done(innerHelper.registerDone).fail();
            } else {
                alert("电话已被占用");
            }
        },
        registerDone: function (data) {
            if (data) {
                innerHelper.saveToCookie(data);
            } else {
                alert("注册失败");
            }
        },
        sendValidateCode: function () {
            userEmail = $.trim($("#forgetEmail").val());
            if (innerHelper.checkEmailFormat(userEmail)) {
                var defer = ajax.sendValidateCode(userEmail);
                defer.done(innerHelper.showMessage).fail();
            } else {
                alert("邮箱格式错误");
            }
        },
        showMessage: function (data) {
            if (data === "true") {
                alert("已发送");
            } else {
                alert("发送失败");
            }
        },
        validateForgetCode: function () {
            userEmail = $.trim($("#forgetEmail").val());
            if (acceptEmail !== userEmail) {
                alert("邮箱不一致，请重新发送");
            }
            else {
                var validateCode = $.trim($("#forgetValidate").val());
                var defer = ajax.checkValidateCode(validateCode.toLowerCase(), 2);
                defer.done(innerHelper.checkForgetEmail).fail();
            }
        },
        checkForgetEmail: function (data) {
            userPassword = $.trim($("#forgetEmail").parent("div").next("div").children("input").last().val());
            if (data === "true") {
                if (innerHelper.checkEmailFormat(userEmail)) {
                    if (userPassword.length < 6) {
                        alert("密码长度小于6");
                    } else if (userPassword.length > 32) {
                        alert("密码过长");
                    } else {
                        userPassword = $.md5(userPassword);
                        var defer = ajax.checkEmail(userEmail);
                        defer.done(innerHelper.updatePassword).fail();
                    }
                }
                else {
                    alert("邮箱格式错误");
                }
            } else {
                alert("验证码错误");
            }
        },
        updatePassword: function (data) {
            if (data === "true") {
                alert("账户不存在");
            } else {
                var defer = ajax.updateAccount(userEmail, userPassword);
                defer.done(innerHelper.updateDone).fail();
            }
        },
        updateDone: function (data) {
            if (data) {
                innerHelper.saveToCookie(data);
            } else {
                alert("操作失败");
            }
        },
        checkEmailFormat: function (email) {
            acceptEmail = email;
            var regex = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
            return regex.test(email);
        },
    };
    return {
        init: function () {
            innerHelper.bindEvent();
        },
    }
}
var userAccount = new UserAccount();
userAccount.init();