/**
 * Created by csl on 2017/4/25.
 */
function Carousel() {
    var curIndex = 0;
    var imgLen = 5;
    var interval = 3000;
    var ajax = new AjaxMethod();
    var href = new HrefMethod();
    var innerHelper = {
        autoChange: function () {
            setInterval(function () {
                if (curIndex < imgLen - 1) {
                    curIndex++;
                } else {
                    curIndex = 0;
                }
                innerHelper.changeImage(curIndex);
            }, interval);
        },
        buildEvent: function () {
            //左箭头滑入滑出事件处理
            $("#previousGoods").hover(function () {
                //滑入清除定时器
                clearInterval(innerHelper.autoChange);
            }, function () {
                //滑出则重置定时器
                innerHelper.autoChangeAgain();
            });
            //左箭头点击处理
            $("#previousGoods").click(function () {
                //根据curIndex进行上一个图片处理
                curIndex = (curIndex > 0) ? (--curIndex) : (imgLen - 1);
                innerHelper.changeImage(curIndex);
            });
            //右箭头滑入滑出事件处理
            $("#nextGoods").hover(function () {
                //滑入清除定时器
                clearInterval(innerHelper.autoChange);
            }, function () {
                //滑出则重置定时器
                innerHelper.autoChangeAgain();
            });
            //右箭头点击处理
            $("#nextGoods").click(function () {
                curIndex = (curIndex < imgLen - 1) ? (++curIndex) : 0;
                innerHelper.changeImage(curIndex);
            });
            //对右下角按钮index进行事件绑定处理等
            $(".indexList").find("li").each(function (item) {
                $(this).hover(function () {
                    clearInterval(innerHelper.autoChange);
                }, function () {
                    innerHelper.autoChangeAgain();
                }).click(function () {
                    innerHelper.changeImage(item);
                    curIndex = item;
                    return false;
                });
            });
            $(".img-list").find("a").each(function (index) {
                $(this).click(function () {
                    var goodsID = $(".img-list").find("input").eq(index).val();
                    var userID = $.cookie("userID");
                    var userPassword = $.cookie("userPassword");
                    if (!(userID && userPassword)) {
                        userID = "";
                    }
                    href.viewGoodsSpecific(goodsID, userID);
                    return false
                });
            })
        },
        autoChangeAgain: function () {
            //清除定时器时候的重置定时器--封装
            innerHelper.autoChange = setInterval(function () {
                if (curIndex < imgLen - 1) {
                    curIndex++;
                } else {
                    curIndex = 0;
                }
                //调用变换处理函数
                innerHelper.changeImage(curIndex);
            }, 2500);
        },
        changeImage: function (index) {
            var goLeft = index * 1200;
            $(".img-list").animate({left: "-" + goLeft + "px"}, 500);
            $(".index-list").find("li").removeClass("index-on").eq(index).addClass("index-on");
        },
        getTop5: function () {
            var userID = $.cookie("userID");
            var defer = ajax.getTop5(userID == null ? "" : userID);
            defer.done(innerHelper.getSuccess).fail();
        },
        getSuccess: function (data) {
            var htmlStringImage = "";
            var htmlStringIndex = "";
            $.each(data, function (index, element) {
                htmlStringImage += "<li><a href='javascript:void(0)'><img src='" + element.imageUrl + "' class='image-li' " +
                    "alt='puss in boots1'></a><input type='hidden' value='" + element.id + "'/></li>";
                htmlStringIndex += "<li>·</li>";
            });
            $("#carouselImage").empty().append(htmlStringImage).css("width", 1200 * data.length + "px");
            $("#carouselIndex").empty().append(htmlStringIndex);
            $("#carouselIndex li").first().addClass("index-on");
            imgLen = data.length;
            innerHelper.autoChange();
            innerHelper.buildEvent();
        }
    };
    return {
        init: function () {
            innerHelper.getTop5();
        },
    }
}
var carousel = new Carousel();
carousel.init();