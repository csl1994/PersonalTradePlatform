/**
 * Created by csl on 2017/6/9.
 */
function UserList(options) {
    var settings = {
        listElement: undefined,
        data: undefined,
    }
    if (options) {
        $.extend(settings, options);
    }
    this.init = function () {
        innerHelper.buildList();
    }
    var innerHelper = {
        buildList: function () {
            var htmlString = "";
            $.each(settings.data, function (index, element) {
                htmlString += "<div class='order-item'>"+element.name+"";
                htmlString += "<input type='hidden' value=" + element.id + " />";
                htmlString += "</div>";
            });
            if(htmlString === ""){
                htmlString = "什么也没有";
            }
            $(settings.listElement).append(htmlString);
        }
    };
}