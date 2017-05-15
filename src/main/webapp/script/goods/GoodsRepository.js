/**
 * Created by csl on 2017/5/2.
 */
function GoodsRepository() {
    var goodsList = undefined;
    var ajax = AjaxMethod();
    var href = HrefMethod();
    var innerHelper = {
        buildEvent: function () {
            $("#goodsRepository").unbind("click").bind("click", function (event) {
                var element = event.toElement;
                var elementClass = event.toElement.className;
                element = $(element).parent(".goods-container");
                if (element || elementClass === "goods-container") {
                    var goodsID = $(element).find("input").first().val();
                    var userID = $.cookie("userID");
                    var userPassword = $.cookie("userPassword");
                    if(userID && userPassword){
                        href.editGoods(goodsID);
                    }else {
                        alert("请先登入");
                    }
                }
                return false;
            });
        },
        getGoods: function () {
            var userID = $.cookie("userID");
            var defer = ajax.getRepository(userID);
            defer.done(innerHelper.showGoodsList).fail();
        },
        showGoodsList: function (data) {
            if (!goodsList) {
                var options = {
                    listElement: $("#goodsRepository"),
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
var goodsRepository = new GoodsRepository();
goodsRepository.init();