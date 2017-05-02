/**
 * Created by csl on 2017/4/21.
 */
function UserOperation() {
    var userID = "";
    var userPassword = "";
    var innerHeler = {
        bindEvent: function () {
            innerHeler.updateValue();
            //todo
            // if (userID && userPassword) {
            //     $("#user").unbind("mouseover").bind("mouseover", function () {
            //         $(".pop-menu").first().show();
            //         $("body").unbind("mouseover").bind("mouseover", function (event) {
            //             // var element = event.toElement;
            //             // var elementClass = event.toElement.className;
            //             // if (elementClass !== "pop-menu" && $(element).attr("id") !== "user") {
            //             //     $(".pop-menu").first().hide();
            //             // }
            //         });
            //     });
            //     // $(".pop-menu").unbind("mouseleave").bind("mouseleave",function () {
            //     //     $(".pop-menu").first().hide();
            //     // })
            // }
        },
        updateValue: function () {
            userID = $.cookie("userID");
            userPassword = $.cookie("userPassword");
        },
    }
    return {
        init: function () {
            innerHeler.bindEvent();
        },
        update: function () {
            innerHeler.bindEvent();
        }
    }
}
var userOperation = new UserOperation();
userOperation.init();