/**
 * Created by csl on 2017/5/24.
 */
function RegionList() {
    var ajax = new AjaxMethod();
    var innerHelper = {
        bindEvent: function () {
            $(".region-city").unbind("click").bind("click",function () {
                var region = $(this).text();
                $("#buttonRegion").find("span").text(region);
                if(region === "全国"){
                    $("#region").val("");
                }else {
                    $("#region").val(region);
                }
                $(".region-menu").first().hide();
                home.reLoad();
            });
        },
        showTop5: function (data) {
            if (data) {
                var htmlString = ""
                $.each(data, function (index, element) {
                    htmlString += "<span class='region-city'>" + element + "</span>  ";
                });
                $("#fiveCity").append(htmlString);
            }
            innerHelper.bindEvent();
        },
        getRegion: function () {
            $("#regionCurrent").text("").text($("#region").val());
            var defer = ajax.getFiveCity();
            defer.done(innerHelper.showTop5).fail();
        },
    };
    return {
        init: function () {
            innerHelper.getRegion();
        },
    }
}
var regionList = new RegionList();