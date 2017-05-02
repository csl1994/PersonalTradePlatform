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
    }
}