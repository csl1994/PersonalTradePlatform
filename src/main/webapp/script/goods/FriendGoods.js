/**
 * Created by csl on 2017/6/9.
 */
function FriendGoods() {
    var goodsList = undefined;
    var ajax = AjaxMethod();
    var href = HrefMethod();
    var innerHelper = {
        buildEvent: function () {
            $("#allGoods").unbind("click").bind("click", function (event) {
                var element = event.toElement;
                var elementClass = event.toElement.className;
                element = $(element).parent(".goods-container");
                if (element || elementClass === "goods-container") {
                    var goodsID = $(element).find("input").first().val();
                    var userID = $.cookie("userID");
                    var userPassword = $.cookie("userPassword");
                    if (userID && userPassword) {
                        href.viewGoodsSpecific(goodsID, userID);
                    } else {
                        alert("请先登入");
                    }
                }
                return false;
            });
        },
        getGoods: function () {
            var userID = $("#twoID").val();
            var defer = ajax.getRepository(userID);
            defer.done(innerHelper.showGoodsList).fail();
        },
        showGoodsList: function (data) {
            if (!goodsList) {
                var options = {
                    listElement: $("#allGoods"),
                    data: data,
                };
                goodsList = new GoodsList(options);
                goodsList.init();
            }
            else {
                goodsList.showPage(data);
            }
        }
    };
    return {
        init: function () {
            innerHelper.getGoods();
            innerHelper.buildEvent();
        },
    };
}
var friendGoods = new FriendGoods();
friendGoods.init();