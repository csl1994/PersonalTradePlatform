/**
 * Created by csl on 2017/3/23.
 */
var GoodsList = function (options) {
    var settings = {
        listElement: undefined,
        data: [],
    };
    var pageSetting = {
        pageElement: undefined,
        count: undefined,
        currentPageElement: undefined,
        border: 10,
        loadPage: undefined,
    };
    var currentPage = 0;
    if (options) {
        $.extend(settings, options);
    }
    this.init = function () {
        innerHelper.buildGoodsList();
        innerHelper.bindMarquee();
    };
    this.initPage = function (options) {
        if (options) {
            $.extend(pageSetting, options);
        }
        innerHelper.buildPage();
        innerHelper.buildPageEvent();
        innerHelper.releasePageEvent();
    };
    this.getCurrentPage = function () {
        return currentPage;
    };
    this.setCurrentPage = function () {
        innerHelper.setCurrentPage(currentPage);
    };
    this.showPage = function (data) {
        settings.data = data;
        innerHelper.buildGoodsList();
    };
    var innerHelper = {
        buildGoodsList: function () {
            var htmlString = "";
            if (settings.data.length <= 0) {
                return false;
            }
            $.each(settings.data, function (index, element) {
                if (index % 4 === 0) {
                    htmlString += "<div class='goods-list-row'>";
                }
                htmlString += "<div class='goods-container'>";
                htmlString += "<input type='hidden' value='" + element.id + "'/>";
                htmlString += "<div class='goods-watermark'>" + element.attentionDegree + "<span>人收藏</span></div>";
                htmlString += "<img src='" + element.imageUrl + "' alt='image' class='goods-image'/>";
                htmlString += "<div class='goods-information'>";
                htmlString += "<p class='goods-name'>" + element.name + "</p>";
                htmlString += "<p class='goods-description'><span>" + element.description + "</span></p>";
                htmlString += "<p class='goods-price'><span>￥</span>" + element.price + "</p>";
                // htmlString += "<a class='a-button goods-operation goods-operation-left' href='javascript:void(0)'>购买</a>";
                // htmlString += "<a class='a-button goods-operation goods-operation-middle' href='javascript:void(0)'>" + (element.currentUserAttend ? "取消收藏" : "收藏") + "</a>";
                // htmlString += "<a class='a-button goods-operation goods-operation-right' href='javascript:void(0)'>联系老板</a>";
                htmlString += "</div></div>";
                if (index % 4 === 3 || index === settings.data.length) {
                    htmlString += "</div>";
                }
            });
            $(settings.listElement).append(htmlString);
        },
        bindMarquee: function () {
            $(".goods-container").bind("mouseover mouseout", function (event) {
                var element = $(this).find(".goods-description > span").first();
                if (event.type === "mouseover") {
                    if ($(element).width() >= $(element).parent().width()) {
                        $(element).addClass("marquee");
                    }
                } else if (event.type === "mouseout") {
                    $(element).removeClass("marquee");
                }
            });
        },
        buildPage: function () {
            if (pageSetting.count > 0) {
                var htmlString = "<a class='a-button goods-page-previous' href='javascript:void(0)'><</a>";
                for (var item = 1; item <= pageSetting.count; item++) {
                    htmlString += "<a class='a-button goods-page' href='javascript:void(0)'>" + item + "</a>";
                }
                htmlString += "<a class='a-button goods-page-next' href='javascript:void(0)'>></a>";
                $(pageSetting.pageElement).append(htmlString);
                currentPage = 1;
                if (pageSetting.count > pageSetting.border) {
                    innerHelper.buildMoreButton(pageSetting.border - 1, pageSetting.count, 0);
                }
            }
        },
        buildMoreButton: function (left, right, direction) {
            var htmlString = "";
            if (left + 1 < right) {
                if (direction) {
                    htmlString = "<a class='a-button goods-more-previous' href='javascript:void(0)' data-left="
                        + left + " data-right=" + right + ">...</a>";
                } else {
                    htmlString = "<a class='a-button goods-more-next' href='javascript:void(0)' data-left="
                        + left + " data-right=" + right + ">...</a>";
                }
                for (var index = left; index < right - 1; index++) {
                    $(".goods-page:eq(" + index + ")").hide();
                }
                $(".goods-page:eq(" + (left - 1) + ")").after(htmlString);
            }
        },
        buildPageEvent: function () {
            $(".goods-page-previous").unbind("click").bind("click", innerHelper.clickPreviousEvent);
            $(".goods-page").unbind("click").bind("click", innerHelper.clickPageEvent);
            $(".goods-more-next").unbind("click").bind("click", innerHelper.nextPageEvent);
            $(".goods-page-next").unbind("click").bind("click", innerHelper.clickNextEvent);
        },
        clickPreviousEvent: function () {
            var index = currentPage - 1;
            $(".goods-page:eq(" + index + ")").removeClass("current-page").unbind("click").bind("click", innerHelper.clickPageEvent);
            if (currentPage > 1) {
                currentPage = currentPage - 1;
            }
            if (currentPage !== 1) {
                $(".goods-page-previous").removeClass("goods-page-border").unbind("click").bind("click", innerHelper.clickPreviousEvent);
            }
            if (currentPage !== pageSetting.count) {
                $(".goods-page-next").removeClass("goods-page-border").unbind("click").bind("click", innerHelper.clickNextEvent);
            }
            innerHelper.releasePageEvent();
        },
        clickPageEvent: function () {
            var index = currentPage - 1;
            $(".goods-page:eq(" + index + ")").removeClass("current-page").unbind("click").bind("click", innerHelper.clickPageEvent);
            currentPage = new Number($(this).text()) + 0;
            if (currentPage !== 1) {
                $(".goods-page-previous").removeClass("goods-page-border").unbind("click").bind("click", innerHelper.clickPreviousEvent);
            }
            if (currentPage !== pageSetting.count) {
                $(".goods-page-next").removeClass("goods-page-border").unbind("click").bind("click", innerHelper.clickNextEvent);
            }
            innerHelper.releasePageEvent();
        },
        clickNextEvent: function () {
            var index = currentPage - 1;
            $(".goods-page:eq(" + index + ")").removeClass("current-page").unbind("click").bind("click", innerHelper.clickPageEvent);
            if (currentPage < pageSetting.count) {
                currentPage = currentPage + 1;
            }
            if (currentPage !== 1) {
                $(".goods-page-previous").removeClass("goods-page-border").unbind("click").bind("click", innerHelper.clickPreviousEvent);
            }
            if (currentPage !== pageSetting.count) {
                $(".goods-page-next").removeClass("goods-page-border").unbind("click").bind("click", innerHelper.clickNextEvent);
            }
            innerHelper.releasePageEvent();
        },
        releasePageEvent: function () {
            var index = currentPage - 1;
            $(".goods-page:eq(" + index + ")").addClass("current-page").unbind("click");
            if (currentPage === 1) {
                $(".goods-page-previous").addClass("goods-page-border").unbind("click");
            }
            if (currentPage === pageSetting.count) {
                $(".goods-page-next").addClass("goods-page-border").unbind("click");
            }
        },
        nextPageEvent: function () {
            var left = new Number($(this).attr("data-left"));
            var right = new Number($(this).attr("data-right"));
            var index = 0;
            currentPage = left + 1;
            for (var i = 0; i < pageSetting.border - 2; i++) {
                index = i + left;
                if (index < right - 1) {
                    $(".goods-page:eq(" + index + ")").show();
                }
                else {
                    break;
                }
            }
            $(".goods-more-previous").remove();
            $(".goods-more-next").remove();
            innerHelper.buildMoreButton(1, left + 1, 1);
            innerHelper.buildMoreButton(index + 1, right, 0);
            $(".goods-more-previous").unbind("click").bind("click", innerHelper.previousPageEvent);
            $(".goods-more-next").unbind("click").bind("click", innerHelper.nextPageEvent);
        },
        previousPageEvent: function () {
            var left = new Number($(this).attr("data-left"));
            var right = new Number($(this).attr("data-right"));
            var index = 0;
            currentPage = right - 1;
            for (var i = 0; i < pageSetting.border - 2; i++) {
                index = right - i - 2;
                if (index >= left) {
                    $(".goods-page:eq(" + index + ")").show();
                }
                else {

                    break;
                }
            }
            $(".goods-more-previous").remove();
            $(".goods-more-next").remove();
            innerHelper.buildMoreButton(left, index + 1, 1);
            innerHelper.buildMoreButton(right - 1, pageSetting.count, 0);
            $(".goods-more-previous").unbind("click").bind("click", innerHelper.previousPageEvent);
            $(".goods-more-next").unbind("click").bind("click", innerHelper.nextPageEvent);
        },
        setCurrentPage: function (currentPage) {
            $(pageSetting.currentPageElement).val(currentPage);
        },
    };
}
var goodsList = undefined;