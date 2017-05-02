/**
 * Created by csl on 2017/3/17.
 */
function PageHead() {
    var href = new HrefMethod();
    var innerHelper = {
        buildEvent: function () {
            $(".page-head").bind("click", function (event) {
                var element = event.toElement;
                var e = event.type;
                var elementID = $(element).attr("id");
                var userID = $.cookie("userID");
                var userPassword = $.cookie("userPassword");
                switch (elementID) {
                    case "buttonHome":
                        href.goHome();
                        break;
                    case "buttonRegion":
                        $(element).children(".region-image").first()
                            .css("background", "url('/content/image/arrow_up_black.png') no-repeat")
                            .css("color", "#111111");
                        break;
                    case "buttonCollection":
                        if (userID && userPassword) {
                            href.viewCollection();
                        } else {
                            alert("请先登入");
                        }
                        break;
                    case "buttonStore":
                        if (userID && userPassword) {
                            //csl todo
                        } else {
                            alert("请先登入");
                        }
                        break;
                    case "buttonExplain":
                        //csl todo
                        break;
                    case "buttonLogin":
                        $("body").css("overflow", "hidden");
                        $("#userBackground").show();
                        $("#loginForm").show();
                        break;
                    case "record":
                        break;
                    case "exit":
                        $.cookie("userPassword", "");
                        href.goHome();
                        href.goHome();
                        break;
                    default:
                        break;
                }
            });
        },
        checkUser: function () {
            var userName = $.cookie("userName");
            var userID = $.cookie("userID");
            var userPassword = $.cookie("userPassword");
            if (userID && userName && userPassword) {
                $("#buttonLogin").parent("div").hide();
                $("#user").parent("div").show();
                $("#user").text("").text(userName);
            }
        },
        buildUserEvent: function () {
            $(".pop-menu").unbind("mouseover mouseout").bind("mouseover mouseout", function (event) {
                switch (event.type) {
                    case "mouseover":
                        $(".sub-menu").first().show();
                        break;
                    case "mouseout":
                        $(".sub-menu").first().hide();
                        break;
                }
                return false;
            });
        },
    };
    return {
        init: function () {
            innerHelper.buildEvent();
            innerHelper.buildUserEvent();
            innerHelper.checkUser();
        },
    };
}
var head = new PageHead();
head.init();