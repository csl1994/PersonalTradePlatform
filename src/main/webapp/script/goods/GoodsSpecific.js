/**
 * Created by csl on 2017/4/21.
 */
function GoodsSpecific() {
    var userID;
    var userPassword;
    var ajax = new AjaxMethod();
    var innerHelper = {
        buildEvent: function () {
            $("#collectionGoods").unbind("click").bind("click", function (event) {
                innerHelper.updateValue();
                if (userID && userPassword) {
                    var element = event.toElement;
                    var isAttention = true;
                    if ($(element).hasClass("js-add")) {
                        isAttention = true;
                        $(this).removeClass("js-add").addClass("js-sub").text("").text("收藏-1");
                    } else if ($(element).hasClass("js-sub")) {
                        isAttention = false;
                        $(this).removeClass("js-sub").addClass("js-add").text("").text("收藏+1");
                    }
                    var defer = ajax.attentionGoods($("#goodsID").val(), userID, isAttention);
                    defer.done(innerHelper.attentionSuccess).fail();
                } else {
                    alert("请登入");
                }
            });
            $("#orderGoods").unbind("click").bind("click", function () {
                $("body").css("overflow", "hidden");
                $("#ownerBackground").show();
                $("#goodsOwner").show();
                innerHelper.getOwner($("#goodsID").val());
            });
            $("#goodsBrief").unbind("click").bind("click", function () {
                $("#brief").show();
                $("#goodsBrief").addClass("current-a");
                $("#parameter").hide();
                $("#goodsParameters").removeClass("current-a");
            });
            $("#goodsParameters").unbind("click").bind("click", function () {
                $("#brief").hide();
                $("#goodsBrief").removeClass("current-a");
                $("#parameter").show();
                $("#goodsParameters").addClass("current-a");
            });
            $("#attentionUser").unbind("click").bind("click", function () {
                var oneID = $.cookie("userID");
                var twoID = $("#sellerID").val();
                var twoName = $("#sellerName").text();
                if (oneID && twoID && twoName) {
                    var defer = ajax.addFriend(oneID, twoID, twoName);
                    defer.done(innerHelper.successAdd).fail();
                } else {
                    alert("请登入");
                }
            });
            $("#unAttentionUser").unbind("click").bind("click",function () {
                var oneID = $.cookie("userID");
                var twoID = $("#sellerID").val();
                if (oneID && twoID) {
                    var defer = ajax.removeFriend(oneID, twoID);
                    defer.done(innerHelper.successRemove).fail();
                } else {
                    alert("请登入");
                }
            });
        },
        updateValue: function () {
            userID = $.cookie("userID");
            userPassword = $.cookie("userPassword");
        },
        attentionSuccess: function (data) {
            if (data === 1) {
                var attention = new Number($(".operation-goods-attention").first().text()) + 1;
                $(".operation-goods-attention").first().text(attention);
            } else if (data === 2) {
                var attention = new Number($(".operation-goods-attention").first().text()) - 1;
                $(".operation-goods-attention").first().text(attention);
            }
            else {
                alert("操作失败");
            }
        },
        getOwner: function (goodsID) {
            var defer = ajax.getOwner(goodsID);
            defer.done(innerHelper.showMessage).fail();
        },
        checkFriend: function () {
            debugger
            var oneID = $.cookie("userID");
            var twoID = $("#sellerID").val();
            if (oneID && twoID) {
                var defer = ajax.checkFriend(oneID, twoID);
                defer.done(innerHelper.showFriend).fail();
            }
        },
        showFriend: function (data) {
            debugger
            if (data === true) {
                $("#unAttentionUser").show();
            } else {
                $("#attentionUser").show();
            }
        },
        showMessage: function (data) {
            $("#userTelephone").empty().text(data.telephone);
            $("#userEmail").empty().text(data.email);
        },
        successAdd: function (data) {
            if (data === true) {
                alert("成功");
                $("#unAttentionUser").show();
                $("#attentionUser").hide();
            } else {
                alert("失败");
            }
        },
        successRemove:function (data) {
            if (data === true) {
                alert("成功");
                $("#attentionUser").show();
                $("#unAttentionUser").hide();
            } else {
                alert("失败");
            }
        }
    };
    return {
        init: function () {
            innerHelper.buildEvent();
            innerHelper.checkFriend();
        },
    };
}
var goodsSpecific = new GoodsSpecific();
goodsSpecific.init();