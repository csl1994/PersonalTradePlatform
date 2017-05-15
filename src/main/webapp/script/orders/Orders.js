/**
 * Created by csl on 2017/5/14.
 */
function Orders() {
    var innerHelper = {
        buildEvent: function () {
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
            });
            $("#buyRecordClose").unbind("click").bind("click", function () {
                $("#buyRecordList").hide();
                $("#orderBackground").hide();
            });
            $("#sellRecordClose").unbind("click").bind("click",function () {
                $("#sellRecordList").hide();
                $("#orderBackground").hide();
            });
        },
    };
    return {
        init: function () {
            innerHelper.buildEvent();
        },
    };
}