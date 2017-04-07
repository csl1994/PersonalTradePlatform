/**
 * Created by csl on 2017/3/28.
 */
function UserAccount() {
    var userEmail = "";
    var userPassword = "";
    var ajax = new AjaxMethod();
    var innerHelper = {
        bindEvent: function () {
            $(".user-dialog").unbind("click").bind("click", function (event) {
                var element = event.toElement;
                var elementID = $(element).attr("id");
                switch (elementID) {
                    case "loginClose":
                        $(".backdrop").hide();
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
                        $(".backdrop").hide();
                        $("#registerForm").hide();
                        $("body").css("overflow", "auto");
                        break;
                    case "register":
                        break;
                    case "forgetPassword":
                        $("#loginForm").hide();
                        $("#forgetForm").show();
                        break;
                    case "forgetClose":
                        $(".backdrop").hide();
                        $("#forgetForm").hide();
                        $("body").css("overflow", "auto");
                        break;
                    case "getValidateCode":
                        break;
                    case "confirm":
                        break;
                    default:
                        break;
                }
            });
        },
        checkUser: function () {
            userEmail = $("#loginEmail").val();
            userPassword = $("#loginPassword").val();
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
            $("#user").parent("div").show();
            $("#user").text("").text(data.name);
            $(".backdrop").hide();
            $("#loginForm").hide();
            $("body").css("overflow", "auto");
            home.reLoad();
        },
        generateValidateCode: function () {
            var defer = ajax.getValidateCode();
            defer.done(innerHelper.showValidateCode).fail();
        },
        showValidateCode: function (data) {
            var src = "/validateCode/" + data + ".png";
            var img = new Image();
            img.src = src;
            img.alt = "validate";
            img.onload = function () {
                $("#validateImage").empty().append(img);
            }
        }
    };
    return {
        init: function () {
            innerHelper.bindEvent();
        },
    }
}
var userAccount = new UserAccount();
userAccount.init();
