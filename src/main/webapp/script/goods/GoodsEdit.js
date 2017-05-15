/**
 * Created by csl on 2017/5/7.
 */
function GoodsEdit() {
    var goodsID = "";
    var goodsName = "";
    var goodsDescription = "";
    var goodsPrice = "";
    var goodsKind = "";
    var logDate = "";
    var status = "";
    var attentionDegree = "";
    var imageUrl = "";
    var isCurrentUserAttend = new Boolean(false);
    var createDate = "";
    var goodsLength = "";
    var goodsWidth = "";
    var goodsHeight = "";
    var goodsColor = "";
    var isChangeImage = false;
    var imageFile = undefined;
    var ajax = new AjaxMethod();
    var href = new HrefMethod();
    var innerHelper = {
        buildEvent: function () {
            $(".filePath").unbind("change").bind("change", function () {
                var url = innerHelper.getObjectURL(this.files[0]);
                isChangeImage = true;
                imageFile = this.files[0];
                $(this).nextAll(".goods-close").show();
                $(this).nextAll("#camera").hide();
                $(this).nextAll("#imageUrl").show();
                $("#imageUrl").attr("src", url);
                $("#imageForm").submit();
            });
            $(".goods-close").unbind("click").bind("click", function () {
                imageFile = undefined;
                $(this).hide();
                $(this).nextAll("#camera").show();
                $(this).nextAll("#imageUrl").hide();
                $(this).nextAll("#imageUrl").attr("src", "");
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
            $("#commit").unbind("click").bind("click", function () {
                var goods = innerHelper.getGoods();
                if (goods) {
                    var defer = ajax.update(goods);
                    defer.done(innerHelper.saveResult).fail(innerHelper.saveResult);
                }
                return false;
            });
            $("#delete").unbind("click").bind("click", function () {
                var defer = undefined;
                goodsID = $("#goodsID").val();
                status = $("#goodsStatus").attr("data-status");
                if (status === "sold") {
                    defer = ajax.remove(goodsID, 2);
                } else {
                    defer = ajax.remove(goodsID, 1);
                }
                defer.done(innerHelper.removeResult).fail();
            });
            $("#add").unbind("click").bind("click", function () {
                var goods = innerHelper.getGoods();
                if (goods) {
                    var defer = ajax.addGoods(goods, $.cookie("userID"));
                    defer.done(innerHelper.saveResult).fail(innerHelper.saveResult);
                }
                return false;
            });
        },
        saveResult: function (data) {
            if (data == true) {
                alert("已保存");
                href.viewRepositoryThisPage();
            } else {
                alert("保存失败");
            }
        },
        removeResult: function (data) {
            if (data === true) {
                href.viewRepositoryThisPage();
            } else {
                alert("删除失败");
            }
        },
        getObjectURL: function (file) {
            var url = null;
            if (window.createObjectURL != undefined) {
                url = window.createObjectURL(file)
            } else if (window.URL != undefined) {
                url = window.URL.createObjectURL(file)
            } else if (window.webkitURL != undefined) {
                url = window.webkitURL.createObjectURL(file)
            }
            return url
        },
        getGoods: function () {
            var goods = {};
            if ($("#pageType").val() === "edit") {
                if (isChangeImage) {
                    imageUrl = "";
                } else {
                    imageUrl = $("#imageUrl").attr("src");
                }
            } else {
                if (isChangeImage) {
                    imageUrl = "";
                } else {
                    alert("请上传图片");
                }
            }
            goodsID = $("#goodsID").val();
            goodsName = $("#goodsName").val();
            goodsDescription = $("#goodsDescription").text();
            goodsPrice = $("#goodsPrice").val();
            goodsKind = $("#goodsKind").val();
            logDate = $("#logDate").val();
            status = $("#goodsStatus").attr("data-status");
            attentionDegree = $("#goodsAttention").text();
            createDate = $("#createDate").val();
            goodsLength = $("#goodsLength").val();
            goodsWidth = $("#goodsWidth").val();
            goodsHeight = $("#goodsHeight").val();
            goodsColor = $("#goodsColor").val();
            if (status === "sold") {
                alert("已售完，不能修改该商品");
                return false;
            }
            if (goodsName && goodsKind && goodsPrice && goodsDescription && logDate && createDate) {
                goods.ID = goodsID;
                goods.name = goodsName;
                goods.description = goodsDescription;
                goods.price = new Number(goodsPrice) + 0;
                goods.kind = goodsKind;
                goods.datetime = +new Date(logDate);
                goods.status = status;
                goods.attentionDegree = new Number(attentionDegree) + 0;
                goods.imageUrl = imageUrl;
                goods.currentUserAttend = isCurrentUserAttend;
                goods.createDate = +new Date(createDate);
                goods.length = new Number(goodsLength) + 0;
                goods.width = new Number(goodsWidth) + 0;
                goods.height = new Number(goodsHeight) + 0;
                goods.color = goodsColor;
                return goods;
            } else {
                alert("信息不全");
            }
            return false;
        },
    };
    return {
        init: function () {
            innerHelper.buildEvent();
        },
    };
}
var goodsEditor = new GoodsEdit();
goodsEditor.init();