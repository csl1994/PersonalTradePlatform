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
                url: "/home/getGoodsList.html",
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
                url: "/home/getCount.html",
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
                url: "/user/getUserByEmail.html",
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
                url: "/user/getUserByID.html",
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
                url: "/user/validateUser.html",
                data: options,
                dataType: "text",
            });
        },
        getValidateCode: function () {
            return $.ajax({
                type: "GET",
                url: "/user/getValidateCode.html",
                data: {},
                dataType: "json",
            });
        },
        sendValidateCode: function (email) {
            var options = {
                email: email,
            };
            return $.ajax({
                type: "GET",
                url: "/user/sendValidateCode.html",
                data: options,
                dataType: "text",
            });
        },
        checkValidateCode: function (validateCode, kind) {
            var options = {
                validateCode: validateCode,
                kind: kind,
            };
            return $.ajax({
                type: "GET",
                url: "/user/checkValidateCode.html",
                data: options,
                dataType: "text",
            });
        },
        checkEmail: function (userEmail) {
            var options = {
                userEmail: userEmail,
            };
            return $.ajax({
                type: "GET",
                url: "/user/checkEmail.html",
                data: options,
                dataType: "text",
            });
        },
        checkName: function (userName) {
            var options = {
                userName: userName,
            };
            return $.ajax({
                type: "GET",
                url: "/user/checkName.html",
                data: options,
                dataType: "text",
            });
        },
        checkTelephone: function (telephone) {
            var options = {
                telephone: telephone,
            };
            return $.ajax({
                type: "GET",
                url: "/user/checkTelephone.html",
                data: options,
                dataType: "text",
            });
        },
        registerAccount: function (email, password, name, telephone, region) {
            var options = {
                email: email,
                password: password,
                name: name,
                telephone: telephone,
                region: region,
            };
            return $.ajax({
                type: "POST",
                url: "/user/registerAccount.html",
                data: options,
                dataType: "json",
            });
        },
        updateAccount: function (email, password) {
            var options = {
                email: email,
                password: password,
            };
            return $.ajax({
                type: "POST",
                url: "/user/updateAccount.html",
                data: options,
                dataType: "json",
            })
        },
        getOwner: function (goodsID) {
            var options = {
                goodsID: goodsID,
            };
            return $.ajax({
                type: "GET",
                url: "/user/getOwner.html",
                data: options,
                dataType: "json",
            });
        },
        attentionGoods: function (goodsID, userID, isAttention) {
            var options = {
                goodsID: goodsID,
                userID: userID,
                isAttention: isAttention,
            };
            return $.ajax({
                type: "POST",
                url: "/goods/attentionGoods.html",
                data: options,
                dataType: "json",
            });
        },
        getTop5: function () {
            return $.ajax({
                type: "GET",
                url: "/goods/getTop5.html",
                data: {},
                dataType: "json",
            });
        },
        getCollection: function (userID) {
            var options = {
                userID: userID,
            };
            return $.ajax({
                type: "GET",
                url: "/goods/getCollection.html",
                data: options,
                dataType: "json",
            });
        },
    }
}