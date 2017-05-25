/**
 * Created by csl on 2017/3/21.
 */
function HomePage() {
    var goodsList = undefined;
    var userID = $.cookie("userID");
    var userPassword = $.cookie("userPassword");
    var region = $("#region").val();
    var goodsKind = $("#goodsKind").val();
    var sortBasis = $("#sortBasis").val();
    var page = $("#page").val();
    var count = 16;
    var goodsText = "";
    var ajax = new AjaxMethod();
    var href = new HrefMethod();
    var innerHelper = {
        initPage: function () {
            innerHelper.updateValue();
            innerHelper.getInformation();
            innerHelper.bindEvent();
        },
        bindEvent: function () {
            $(".goods-filter").first().unbind("click").bind("click", function (event) {
                var element = event.toElement;
                var elementClass = event.toElement.className;
                if (elementClass === "filter-name a-button" && $(element).attr("data-kind") !== $("#goodsKind").val()) {
                    var goodsKind = $(element).attr("data-kind");
                    $("#goodsKind").val(goodsKind);
                    if (goodsKind) {
                        switch (goodsKind){
                            case "clothes":
                                $("#currentKind").text("服装");
                                break;
                            case "digitalProduct":
                                $("#currentKind").text("数码");
                                break;
                            case "sportsProduct":
                                $("#currentKind").text("体育");
                                break;
                            case "food":
                                $("#currentKind").text("食物");
                                break;
                            case "dailyUse":
                                $("#currentKind").text("日常用品");
                                break;
                            case "schoolThings":
                                $("#currentKind").text("学习用品");
                                break;
                            case "luxury":
                                $("#currentKind").text("奢侈品");
                                break;
                            case "others":
                                $("#currentKind").text("其他");
                                break;
                        };
                    } else {
                        $("#currentKind").text("全部");
                    }
                    $("#searchText").val("");
                    innerHelper.clearPage();
                    innerHelper.initPage();
                }
            });
            $(".goods-filter").last().unbind("click").bind("click", function (event) {
                var element = event.toElement;
                if ($(element).attr("data-kind") !== $("#sortBasis").val()) {
                    $("#sortBasis").val($(element).attr("data-kind"));
                    innerHelper.clearPage();
                    innerHelper.initPage();
                }
            });
            $(".goods-page-list").first().unbind("click").bind("click", function () {
                if (page != goodsList.getCurrentPage()) {
                    goodsList.setCurrentPage();
                    $("#goodsList").empty();
                    innerHelper.updateValue();
                    innerHelper.getInformation();
                }
            });
            $("#goodsList").unbind("click").bind("click", function (event) {
                var element = event.toElement;
                var elementClass = event.toElement.className;
                element = $(element).parent(".goods-container");
                if (element || elementClass === "goods-container") {
                    var goodsID = $(element).find("input").first().val();
                    innerHelper.updateValue();
                    if (!(userID && userPassword)) {
                        userID = "";
                    }
                    if (goodsID) {
                        href.viewGoodsSpecific(goodsID, userID);
                    }
                }
                return false;
            });
            $("#searchGoods").unbind("click").bind("click", function () {
                innerHelper.clearPage();
                innerHelper.initPage();
                return false;
            });
        },
        getInformation: function () {
            if (userID && userPassword) {
                userID = $.cookie("userID");
            } else {
                userID = "";
            }
            var defer = ajax.getGoodsList(userID, region, goodsKind, sortBasis, page, goodsText);
            defer.done(innerHelper.showGoodsList).fail(innerHelper.showError);
        },
        getPageInformation: function () {
            innerHelper.updateValue();
            if (!(userID && userPassword)) {
                userID = "";
            }
            var defer = ajax.getPage(userID, region, goodsKind, goodsText);
            defer.done(innerHelper.showPageList).fail();
        },
        updateValue: function () {
            userID = $.cookie("userID");
            userPassword = $.cookie("userPassword");
            region = $("#region").val();
            goodsKind = $("#goodsKind").val();
            sortBasis = $("#sortBasis").val();
            page = $("#page").val();
            goodsText = $("#searchText").val();
        },
        showGoodsList: function (data) {
            if (!goodsList) {
                var options = {
                    listElement: $("#goodsList"),
                    data: data,
                };
                goodsList = new GoodsList(options);
                goodsList.init();
            }
            else {
                goodsList.showPage(data);
            }
            if (!$(".goods-page-list").text()) {
                innerHelper.getPageInformation();
            }
        },
        showError: function () {
            alert("error");
        },
        showPageList: function (data) {
            var options = {
                pageElement: $(".goods-page-list"),
                count: Math.ceil(data / count),
                currentPageElement: $("#page"),
            };
            goodsList.initPage(options);
        },
        clearPage: function () {
            $("#goodsList").empty();
            $(".goods-page-list").empty();
        },
    };
    return {
        init: function () {
            innerHelper.initPage();
        },
        reLoad: function () {
            innerHelper.clearPage();
            innerHelper.initPage();
        }
    };
}
var home = new HomePage();