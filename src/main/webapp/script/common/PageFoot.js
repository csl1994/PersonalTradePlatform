/**
 * Created by csl on 2017/5/17.
 */
function PageFoot() {
    var innerHelper = {
        bindEvent: function () {
            $("#platformDescription").unbind("click").bind("click", function () {
                $("#footBackground").show();
                $("#first").show();
            });
            $("#platformRule").unbind("click").bind("click", function () {
                $("#footBackground").show();
                $("#second").show();
            });
            $("#firstClose").unbind("click").bind("click", function () {
                $("#footBackground").hide();
                $("#first").hide();
            });
            $("#secondClose").unbind("click").bind("click", function () {
                $("#footBackground").hide();
                $("#second").hide();
            });
        },
    };
    return {
        init: function () {
            innerHelper.bindEvent();
        },
    };
}
var pageFoot = new PageFoot();
pageFoot.init();