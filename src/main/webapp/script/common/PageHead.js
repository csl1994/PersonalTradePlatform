/**
 * Created by csl on 2017/3/17.
 */
function PageHead() {
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
                        break;
                    case "buttonRegion":
                        $(element).children(".region-image").first()
                            .css("background", "url('/content/image/arrow_up_black.png') no-repeat")
                            .css("color", "#111111");
                        break;
                    case "buttonCollection":
                        if (userID && userPassword) {
                            //csl todo
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
                        $(".backdrop").show();
                        $("#loginForm").show();
                        break;
                    case "user":
                        //csl todo
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
    };
    return {
        init: function () {
            innerHelper.buildEvent();
            innerHelper.checkUser();
        },
    };
}
var head = new PageHead();
head.init();