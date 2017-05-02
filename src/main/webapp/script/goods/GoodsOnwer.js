/**
 * Created by csl on 2017/5/1.
 */
function GoodsOwner() {
    var userID = undefined;
    var userPassword = undefined;
    var innerHelper = {
        buildEvent: function () {
            $("#buyButton").unbind("click").bind("click", function () {
                // 生成一个订单
                userID = $.cookie("userID");
                userPassword = $.cookie("userPassword");
                if (userID && userPassword) {
                    //
                } else {
                    alert("请先登入");
                }
            });
            $("#bayButton").unbind("click").bind("click", function () {
                $(".user-dialog-owner").hide();
                $(".backdrop").hide();
            })
        },
    };
    return {
        init: function () {
            innerHelper.buildEvent();
        },
    };
}
var goodsOwner = new GoodsOwner();
goodsOwner.init();