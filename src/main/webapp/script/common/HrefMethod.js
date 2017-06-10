/**
 * Created by csl on 2017/4/19.
 */
function HrefMethod() {
    return {
        goHome: function () {
            window.location.href = "http://localhost:8080/";
        },
        viewCollection: function () {
            window.open("/goods/viewCollection.html");
        },
        viewGoodsSpecific: function (goodsID, userID) {
            window.open("/goods/goodsSpecific/" + goodsID + "&" + userID + ".html");
        },
        viewRepository: function () {
            window.open("/goods/viewRepository.html");
        },
        viewRepositoryThisPage: function () {
            window.location.href = "http://localhost:8080/goods/viewRepository.html";
        },
        editGoods: function (goodsID) {
            window.location.href = "http://localhost:8080/goods/editGoods/" + goodsID + ".html";
        },
        createGoods: function () {
            window.open("/goods/createGoods.html");
        },
        viewFriendGoods: function (twoID) {
            window.location.href = "http://localhost:8080/goods/friendGoods/" + twoID + ".html";
        }
    }
}