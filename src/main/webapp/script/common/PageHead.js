/**
 * Created by csl on 2017/3/17.
 */
function PageHead() {
    var href = new HrefMethod();
    var ajax = new AjaxMethod();
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
                            href.viewRepository();
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
                        innerHelper.getSellRecordList();
                        break;
                    case "buyRecord":
                        $("body").css("overflow", "hidden");
                        $("#orderBackground").show();
                        $("#buyRecordList").show();
                        innerHelper.getBuyRecordList();
                        break;
                    case "sellFRecord":
                        $("body").css("overflow", "hidden");
                        $("#orderBackground").show();
                        $("#sellRecordList").show();
                        break;
                    case "addGoods":
                        href.createGoods();
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
                var defer = ajax.newOrder(userID);
                defer.done(innerHelper.updateOrder).fail();
            }
        },
        updateOrder: function (data) {
            if (data === true) {
                $("#user").parent().find("div").append("<span class='new-mark'>new</span>");
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
        getBuyRecordList: function () {
            var defer = ajax.getAllBuyOrder($.cookie("userID"));
            defer.done(innerHelper.showBuyList).fail();
        },
        showBuyList: function (data) {
            if (data) {
                var options = {
                    listElement: $("#buyRecordList").find(".order-list"),
                    data: data,
                    kind: 1,
                }
                var orders = new OrdersList(options);
                orders.init();
                innerHelper.buildOrderEvent();
            }
        },
        getSellRecordList: function () {
            var defer = ajax.getAllSellOrder($.cookie("userID"));
            defer.done(innerHelper.showSellList).fail();
        },
        showSellList: function (data) {
            if (data) {
                var options = {
                    listElement: $("#sellRecordList").find(".order-list"),
                    data: data,
                    kind: 0,
                }
                var orders = new OrdersList(options);
                orders.init();
                innerHelper.buildOrderEvent();
            }
        },
        buildOrderEvent: function () {
            $(".order-item").unbind("mouseover mouseout click").bind("mouseover mouseout click", function (event) {
                var eventType = event.type;
                switch (eventType) {
                    case "mouseover":
                        $(this).find(".order-operation").show();
                        break;
                    case "mouseout":
                        $(this).find(".order-operation").hide();
                        break;
                    case "click":
                        break;
                }
            })
        }
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