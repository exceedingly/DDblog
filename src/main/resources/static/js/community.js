//
// function show_runtime() {
//     window.setTimeout("show_runtime()", 1000);
//     X = new Date("12/4/2018 00:00:00");
//     Y = new Date();
//     T = (Y.getTime() - X.getTime());
//     M = 24 * 60 * 60 * 1000;
//     a = T / M;
//     A = Math.floor(a);
//     b = (a - A) * 24;
//     B = Math.floor(b);
//     c = (b - B) * 60;
//     C = Math.floor((b - B) * 60);
//     D = Math.floor((c - C) * 60);
//     runtime_span.innerHTML = "网站已艰难运行" + A + "天" + B + "小时" + C + "分" + D + "秒"
//     show_runtime();
//
// }
// show_runtime();
App.directive('repeatFinish', function () {
    return {
        link: function (scope, element, attr) {
            if (scope.$last == true) {
                //列表渲染完毕后重新渲染 layui element 元素
                layui.use(['element'], function () {
                    var element = layui.element;
                    //初始化动态元素，一些动态生成的元素如果不设置初始化，将不会有默认的动态效果
                    element.init();
                });
            }
        }
    }
});
