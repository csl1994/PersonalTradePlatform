/**
 * Created by csl on 2017/3/17.
 */
function PageHead() {
    var href = new HrefMethod();
    var ajax = new AjaxMethod();
    var region = undefined;
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
                        innerHelper.getSellRecordList();
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
                var defer1 = ajax.newSellOrder(userID);
                defer1.done(innerHelper.updateSellOrder).fail();
                var defer2 = ajax.newBuyOrder(userID);
                defer2.done(innerHelper.updateBuyOrder).fail();
            }
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
            $("#buttonRegion").parent().unbind("mouseover mouseout").bind("mouseover mouseout", function (event) {
                switch (event.type) {
                    case "mouseover":
                        $(".region-menu").first().show();
                        break;
                    case "mouseout":
                        $(".region-menu").first().hide();
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
                $("#buyRecordList").find(".order-list").show();
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
                $("#sellRecordList").find(".order-list").show();
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
                        var sellerID = $(this).find(".js-seller-id").val();
                        var buyerID = $(this).find(".js-buyer-id").val();
                        var buyerGrade = $(this).find(".js-buyer-grade").val();
                        var buyerStatus = $(this).find(".js-buyer-status").val();
                        var sellerGrade = $(this).find(".js-seller-grade").val();
                        var sellerStatus = $(this).find(".js-seller-status").val();
                        var orderID = $(this).find(".js-id").val();
                        if (sellerID) {
                            innerHelper.deployOrderSpecific(sellerGrade, sellerStatus, buyerGrade, buyerStatus, 1);
                            $("#buyRecordList").find(".order-list").hide();
                            $("#buyRecordList").find(".order-specific").show();
                            var defer = ajax.getUserByID(sellerID);
                            defer.done(innerHelper.deployUser).fail();
                            if (sellerStatus === "unread") {
                                var defer1 = ajax.updateBuyerStatus(orderID, "end");
                                defer1.done().fail();
                            }
                        }
                        if (buyerID) {
                            innerHelper.deployOrderSpecific(sellerGrade, sellerStatus, buyerGrade, buyerStatus, 0);
                            $("#sellRecordList").find(".order-list").hide();
                            $("#sellRecordList").find(".order-specific").show();
                            var defer = ajax.getUserByID(buyerID);
                            defer.done(innerHelper.deployUser).fail();
                            if (sellerStatus === "unread") {
                                var defer1 = ajax.updateSellerStatus(orderID, "start");
                                defer1.done().fail();
                            }
                        }
                        break;
                }
                return false;
            });
            $(".order-operation").unbind("click").bind("click", function () {
                var sellerID = $(this).parent().find(".js-seller-id").val();
                var buyerID = $(this).parent().find(".js-buyer-id").val();
                var orderID = $(this).parent().find(".js-id").val();
                if (sellerID) {
                    var defer = ajax.updateBuyerStatus(orderID, "delete");
                    defer.done().fail();
                }
                if (buyerID) {
                    var defer = ajax.updateSellerStatus(orderID, "delete");
                    defer.done().fail();
                }
                $(this).parent().remove();
                return false;
            });
            $("#buyRecordClose").unbind("click").bind("click", function () {
                $("#buyRecordList").hide().find(".order-list").empty();
                $("#buyRecordList").find(".order-list").hide();
                $("#buyRecordList").find(".order-specific").hide();
                $("#orderBackground").hide();
                $("body").css("overflow", "auto");
                $("#user").find(".new-buy-mark").remove();
                var defer2 = ajax.newBuyOrder($.cookie("userID"));
                defer2.done(innerHelper.updateBuyOrder).fail();
                return false;
            });
            $("#sellRecordClose").unbind("click").bind("click", function () {
                $("#sellRecordList").hide().find(".order-list").empty();
                $("#sellRecordList").find(".order-list").hide();
                $("#sellRecordList").find(".order-specific").hide();
                $("#orderBackground").hide();
                $("body").css("overflow", "auto");
                $("#user").find(".new-sell-mark").remove();
                var defer1 = ajax.newSellOrder($.cookie("userID"));
                defer1.done(innerHelper.updateSellOrder).fail();
                return false;
            });
            $("#buySubmit").unbind("click").bind("click", function () {
                var grade = $("#sellerGrade").val();
                var orderID = $(".js-id").val();
                var sellerID = $(".js-seller-id").val();
                if (grade > 0 && grade < 100) {
                    var defer = ajax.updateSellerGrade(orderID, sellerID, grade);
                    defer.done(innerHelper.closeBuy).fail();
                } else {
                    alert("请填写有效值");
                }
            });
            $("#sellSubmit").unbind("click").bind("click", function () {
                var grade = $("#buyerGrade").val();
                var orderID = $(".js-id").val();
                var buyerID = $(".js-buyer-id").val();
                if (grade > 0 && grade < 100) {
                    var defer = ajax.updateBuyerGrade(orderID, buyerID, grade);
                    defer.done(innerHelper.closeSell).fail();
                } else {
                    alert("请填写有效值");
                }
            });
        },
        deployOrderSpecific: function (sGrade, sStatus, bGrade, bStatus, kind) {
            if (kind) {
                //购买
                if (sStatus === "end" || sStatus === "delete") {
                    $(".my-grade-show").empty().text(bGrade);
                } else {
                    $(".my-grade-show").empty().text("未评分");
                }
                if (bStatus === "end" || bStatus === "unread") {
                    $("#sellerGrade").val(sGrade).attr("disabled", "true");
                    $("#buySubmit").unbind("click");
                }
            } else {
                //销售
                if (bStatus === "end" || bStatus === "delete") {
                    $(".my-grade-show").empty().text(sGrade);
                } else {
                    $(".my-grade-show").empty().text("未评分");
                }
                if (sStatus === "end") {
                    $("#buyerGrade").val(bGrade).attr("disabled", "true");
                    ("#sellSubmit").unbind("click");
                }
            }
        },
        deployUser: function (data) {
            $(".js-email").val(data.email);
            $(".js-phone").val(data.telephone);
        },
        getRegion:function () {
            var url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js";
            $.getScript(url,function () {
                region = remote_ip_info.city;
                $("#buttonRegion").find("span").empty().text(region);
                $("#region").val(region);
                home.init();
                regionList.init();
            });
        },
        closeBuy:function(data){
            if(data){
                $("#buyRecordList").hide().find(".order-list").empty();
                $("#buyRecordList").find(".order-list").hide();
                $("#buyRecordList").find(".order-specific").hide();
                $("#orderBackground").hide();
                $("body").css("overflow", "auto");
            }
        },
        closeSell:function (data) {
            if(data){
                $("#sellRecordList").hide().find(".order-list").empty();
                $("#sellRecordList").find(".order-list").hide();
                $("#sellRecordList").find(".order-specific").hide();
                $("#orderBackground").hide();
                $("body").css("overflow", "auto");
            }
        }
    };
    return {
        init: function () {
            innerHelper.buildEvent();
            innerHelper.buildUserEvent();
            innerHelper.checkUser();
            innerHelper.getRegion();
        },
    };
}
var head = new PageHead();
head.init();

