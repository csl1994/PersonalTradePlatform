/**
 * Created by csl on 2017/3/21.
 */
function AjaxMethod() {
    return {
        getGoodsList: function (userID, region, goodsKind, sortBasis, page, text) {
            var options = {
                userID: userID,
                region: region,
                goodsKind: goodsKind,
                sortBasis: sortBasis,
                page: page,
                text: text,
            }
            return $.ajax({
                type: "GET",
                url: "/home/getGoodsList.html",
                data: options,
                dataType: "json",
            });
        },
        getPage: function (userID, region, goodsKind, text) {
            var options = {
                userID: userID,
                region: region,
                goodsKind: goodsKind,
                text: text,
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
        modifyMessage: function (ID, telephone) {
            var options = {
                ID: ID,
                telephone: telephone,
            };
            return $.ajax({
                type: "POST",
                url: "/user/modifyMessage.html",
                data: options,
                dataType: "json",
            });
        },
        addFriend: function (oneID, twoID, name) {
            var options = {
                oneID: oneID,
                twoID: twoID,
                name: name,
            };
            return $.ajax({
                type: "POST",
                url: "/user/addFriend.html",
                data: options,
                dataType: "json",
            });
        },
        getAllFriends: function (oneID) {
            var options = {
                oneID: oneID,
            };
            return $.ajax({
                type: "POST",
                url: "/user/getAllFriends.html",
                data: options,
                dataType: "json",
            });
        },
        checkFriend: function (oneID, twoID) {
            var options = {
                oneID: oneID,
                twoID: twoID,
            };
            return $.ajax({
                type: "POST",
                url: "/user/checkFriend.html",
                data: options,
                dataType: "json",
            });
        },
        removeFriend: function (oneID, twoID) {
            var options = {
                oneID: oneID,
                twoID: twoID,
            };
            return $.ajax({
                type: "POST",
                url: "/user/removeFriend.html",
                data: options,
                dataType: "json",
            });
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
        getFiveCity: function () {
            return $.ajax({
                type: "GET",
                url: "/user/getFiveCity.html",
                data: {},
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
        getTop5: function (userID) {
            var options = {
                userID: userID,
            };
            return $.ajax({
                type: "GET",
                url: "/goods/getTop5.html",
                data: options,
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
        getRepository: function (userID) {
            var options = {
                userID: userID,
            };
            return $.ajax({
                type: "GET",
                url: "/goods/getRepository.html",
                data: options,
                dataType: "json",
            });
        },
        update: function (goods) {
            var options = {
                goodsJSON: JSON.stringify(goods),
            };
            return $.ajax({
                type: "GET",
                url: "/goods/update.html",
                data: options,
                dataType: "json",
            });
        },
        remove: function (goodsID, type) {
            var options = {
                goodsID: goodsID,
                type: type,
            };
            return $.ajax({
                type: "GET",
                url: "/goods/remove.html",
                data: options,
                dataType: "json",
            });
        },
        addGoods: function (goods, userID) {
            var options = {
                goodsJSON: JSON.stringify(goods),
                userID: userID,
            };
            return $.ajax({
                type: "GET",
                url: "/goods/addGoods.html",
                data: options,
                dataType: "json",
            });
        },
        updateGoodsStatus: function (goodsID, status) {
            var options = {
                goodsID: goodsID,
                status: status,
            };
            return $.ajax({
                type: "GET",
                url: "/goods/updateStatus.html",
                data: options,
                dataType: "json",
            });
        },
        upImage: function (image) {
            var options = {
                imageFile: image,
            };
            return $.ajax({
                type: "POST",
                url: "/goods/upImage.html",
                data: options,
                dataType: "json",
            });
        },
        createOrder: function (sellerID, buyerID, goodsID) {
            var options = {
                sellerID: sellerID,
                buyerID: buyerID,
                goodsID: goodsID,
            };
            return $.ajax({
                type: "GET",
                url: "/order/createOrder.html",
                data: options,
                dataType: "json",
            });
        },
        getAllSellOrder: function (sellerID) {
            var options = {
                sellerID: sellerID,
            };
            return $.ajax({
                type: "GET",
                url: "/order/getAllSell.html",
                data: options,
                dataType: "json",
            });
        },
        getAllBuyOrder: function (buyerID) {
            var options = {
                buyerID: buyerID,
            };
            return $.ajax({
                type: "GET",
                url: "/order/getAllBuy.html",
                data: options,
                dataType: "json",
            });
        },
        updateSellerStatus: function (ID, status) {
            var options = {
                sellerID: ID,
                status: status,
            };
            return $.ajax({
                type: "POST",
                url: "/order/updateSellerStatus.html",
                data: options,
                dataType: "json",
            });
        },
        updateBuyerStatus: function (ID, status) {
            var options = {
                buyerID: ID,
                status: status,
            };
            return $.ajax({
                type: "POST",
                url: "/order/updateBuyerStatus.html",
                data: options,
                dataType: "json",
            });
        },
        newSellOrder: function (sellerID) {
            var options = {
                sellerID: sellerID,
            };
            return $.ajax({
                type: "GET",
                url: "/order/newSellOrder.html",
                data: options,
                dataType: "json",
            });
        },
        newBuyOrder: function (buyerID) {
            var options = {
                buyerID: buyerID,
            };
            return $.ajax({
                type: "GET",
                url: "/order/newBuyOrder.html",
                data: options,
                dataType: "json",
            });
        },
        updateSellerGrade: function (ID, sellerID, grade) {
            var options = {
                ID: ID,
                userID: sellerID,
                grade: grade,
            };
            return $.ajax({
                type: "GET",
                url: "/order/updateSellerGrade.html",
                data: options,
                dataType: "json",
            });
        },
        updateBuyerGrade: function (ID, buyerID, grade) {
            var options = {
                ID: ID,
                userID: buyerID,
                grade: grade,
            };
            return $.ajax({
                type: "GET",
                url: "order/updateBuyerGrade.html",
                data: options,
                dataType: "json",
            });
        }
    }
}