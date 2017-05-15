/**
 * Created by csl on 2017/5/14.
 */
var OrdersList = function (options) {
    var settings = {
        listElement: undefined,
        data: [],
        kind: undefined,
    };
    if (options) {
        $.extend(settings, options);
    }
    this.init = function () {
        innerHelper.buildList();
    };
    var innerHelper = {
        buildList: function () {
            var htmlString = "";
            if (settings.kind > 0) {
                $.each(settings.data, function (index, element) {
                    var datetimeStr = element.datetime;
                    var datetime = new Date();
                    datetime.setTime(datetimeStr);
                    var year = datetime.getFullYear();
                    var month = datetime.getMonth() + 1;
                    var date = datetime.getDate();
                    datetimeStr = year + "-" + month + "-" + date;
                    htmlString += "<div class=\"order-item\">";
                    htmlString += "<div class=\"order-goods\">" + element.goodsName + "</div>";
                    htmlString += "<div class=\"order-seller\">from:" + element.sellerName + "</div>";
                    htmlString += "<div class=\"order-time\">" + datetimeStr + "</div>";
                    htmlString += "<div class=\"order-operation hide\" title=\"删除\">x</div>";
                    htmlString += "<input type='hidden' value='" + element.sellerID + "'/>";
                    htmlString += "<input type='hidden' value='" + element.buyerGrade + "'/>";
                    htmlString += "</div>";
                });
            } else {
                $.each(settings.data, function (index, element) {
                    var datetimeStr = element.datetime;
                    var datetime = new Date();
                    datetime.setTime(datetimeStr);
                    var year = datetime.getFullYear();
                    var month = datetime.getMonth() + 1;
                    var date = datetime.getDate();
                    datetimeStr = year + "-" + month + "-" + date;
                    htmlString += "<div class=\"order-item\">";
                    htmlString += "<div class=\"order-goods\">" + element.goodsName + "</div>";
                    htmlString += "<div class=\"order-seller\">to:" + element.buyerName + "</div>";
                    htmlString += "<div class=\"order-time\">" + datetimeStr + "</div>";
                    htmlString += "<div class=\"order-operation hide\" title=\"删除\">x</div>";
                    htmlString += "<input type='hidden' value='" + element.buyerID + "'/>";
                    htmlString += "<input type='hidden' value='" + element.sellerGrade + "'/>";
                    htmlString += "</div>";
                });
            }
            $(settings.listElement).append(htmlString);
        },
    };
}