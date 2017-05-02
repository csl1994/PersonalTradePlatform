/**
 * Created by csl on 2017/3/21.
 */
function HomePage() {
    var userID = $.cookie("userID");
    var userPassword = $.cookie("userPassword");
    var region = $("#region").val();
    var goodsKind = $("#goodsKind").val();
    var sortBasis = $("#sortBasis").val();
    var page = $("#page").val();
    var count = 16;
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
                    $("#goodsKind").val($(element).attr("data-kind"));
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
                    href.viewGoodsSpecific(goodsID, userID);
                }
                return false;
            });
        },
        getInformation: function () {
            if (userID && userPassword) {
                userID = $.cookie("userID");
            } else {
                userID = "";
            }
            var defer = ajax.getGoodsList(userID, region, goodsKind, sortBasis, page);
            defer.done(innerHelper.showGoodsList).fail(innerHelper.showError);
        },
        getPageInformation: function () {
            innerHelper.updateValue();
            if (!(userID && userPassword)) {
                userID = "";
            }
            var defer = ajax.getPage(userID, region, goodsKind);
            defer.done(innerHelper.showPageList).fail();
        },
        updateValue: function () {
            userID = $.cookie("userID");
            userPassword = $.cookie("userPassword");
            region = $("#region").val();
            goodsKind = $("#goodsKind").val();
            sortBasis = $("#sortBasis").val();
            page = $("#page").val();
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
home.init();