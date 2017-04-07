/**
 * Created by csl on 2017/3/21.
 */
function AjaxMethod() {
    return {
        getGoodsList: function (userID, region, goodsKind, sortBasis, page) {
            var options = {
                userID: userID,
                region: region,
                goodsKind: goodsKind,
                sortBasis: sortBasis,
                page: page,
            }
            return $.ajax({
                type: "GET",
                url: "home/getGoodsList.html",
                data: options,
                dataType: "json",
            });
        },
        getPage: function (userID, region, goodsKind) {
            var options = {
                userID: userID,
                region: region,
                goodsKind: goodsKind,
            }
            return $.ajax({
                type: "GET",
                url: "home/getCount.html",
                data: options,
                dataType: "json",
            });
        },
        getUserByEmail: function (email) {
            var options = {
                email: email,
            };
            return $.ajax({
                type: "GET",
                url: "user/getUserByEmail.html",
                data: options,
                dataType: "json",
            });
        },
        getUserByID: function (ID) {
            var options = {
                ID: ID,
            };
            return $.ajax({
                type: "GET",
                url: "user/getUserByID.html",
                data: options,
                dataType: "json",
            });
        },
        validateUser: function (email, password) {
            var options = {
                email: email,
                password: password,
            };
            return $.ajax({
                type: "GET",
                url: "user/validateUser.html",
                data: options,
                dataType: "text",
            });
        },
        getValidateCode: function () {
            return $.ajax({
                type: "GET",
                url: "user/getValidateCode.html",
                data: {},
                dataType: "json",
            });
        },
    }
}