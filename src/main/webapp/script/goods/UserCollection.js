/**
 * Created by csl on 2017/4/26.
 */
function UserCollection() {
    var goodsList = undefined;
    var ajax = new AjaxMethod();
    var href =  new HrefMethod();
    var innerHelper = {
        getGoodsList: function () {
            var userID = $.cookie("userID");
            var defer = ajax.getCollection(userID);
            defer.done(innerHelper.showGoods).fail();
        },
        showGoods: function (data) {
            var options = {
                listElement: $("#goodsCollection"),
                data: data,
            };
            goodsList = new GoodsList(options);
            goodsList.init();
        },
        buildEvent: function () {
            $("#goodsCollection").unbind("click").bind("click", function (event) {
                var element = event.toElement;
                var elementClass = event.toElement.className;
                element = $(element).parent(".goods-container");
                if (element || elementClass === "goods-container") {
                    var goodsID = $(element).find("input").first().val();
                    var userID = $.cookie("userID");
                    var userPassword = $.cookie("userPassword");
                    if (!(userID && userPassword)) {
                        userID = "";
                    }
                    href.viewGoodsSpecific(goodsID, userID);
                }
                return false;
            });
        },
    };
    return {
        init: function () {
            innerHelper.getGoodsList();
            innerHelper.buildEvent();
        }
    };
}
var userCollection = new UserCollection();
userCollection.init();