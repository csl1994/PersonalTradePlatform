/**
 * Created by csl on 2017/5/1.
 */
function GoodsOwner() {
    var userID = undefined;
    var userPassword = undefined;
    var goodsID = undefined;
    var ajax = new AjaxMethod();
    var href = new HrefMethod();
    var innerHelper = {
        buildEvent: function () {
            $("#buyButton").unbind("click").bind("click", function () {
                userID = $.cookie("userID");
                userPassword = $.cookie("userPassword");
                goodsID = $("#goodsID").val();
                if (userID && userPassword) {
                    var defer = ajax.createOrder($("#sellerID").val(), userID, $("#goodsID").val());
                    defer.done(innerHelper.updateGoods).fail();
                } else {
                    alert("请先登入");
                }
            });
            $("#bayButton").unbind("click").bind("click", function () {
                $("body").css("overflow", "auto");
                $(".user-dialog-owner").hide();
                $(".backdrop").hide();
            })
        },
        updateGoods: function (data) {
            if (data === true) {
                var defer = ajax.updateGoodsStatus($("#goodsID").val(), "sold");
                defer.done(innerHelper.cancelAttention).fail();
            } else {
                alert("操作失败");
            }
        },
        cancelAttention: function (data) {
            if (data === true) {
                var defer = ajax.attentionGoods(goodsID, userID, false);
                defer.done(innerHelper.backHome).fail();
            } else {
                alert("操作失败");
            }
        },
        backHome: function () {
            href.goHome();
        }
    };
    return {
        init: function () {
            innerHelper.buildEvent();
        },
    };
}
var goodsOwner = new GoodsOwner();
goodsOwner.init();