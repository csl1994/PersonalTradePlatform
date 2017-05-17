/**
 * Created by csl on 2017/5/17.
 */
function Timer() {
    var innerHelper = {
        updateSellOrder: function (data) {
            if (data === true) {
                $("#user").parent().find(".new-sell-mark").remove();
                $("#user").parent().find("div").append("<span class='new-sell-mark'>new</span>");
            }
        },
        updateBuyOrder: function (data) {
            if (data === true) {
                $("#user").parent().find(".new-buy-mark").remove();
                $("#user").parent().find("div").append("<span class='new-buy-mark'>new</span>");
            }
        },
    };
    return {
        checkUser: function () {
            var userName = $.cookie("userName");
            var userID = $.cookie("userID");
            var userPassword = $.cookie("userPassword");
            var ajax = new AjaxMethod();
            if (userID && userName && userPassword) {
                var defer1 = ajax.newSellOrder(userID);
                defer1.done(innerHelper.updateSellOrder).fail();
                var defer2 = ajax.newBuyOrder(userID);
                defer2.done(innerHelper.updateBuyOrder).fail();
            }
        },
    }
}
var timer = new Timer();
var timerTest = setInterval("timer.checkUser()", 6000);