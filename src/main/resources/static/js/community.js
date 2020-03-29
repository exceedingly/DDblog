$(function() {
    $(".nav2").hide();
    $(window).scroll(function() {
        if($(document).scrollTop() >= 20) {
            $(".nav2").addClass("fixnav").slideDown();
        } else {
            $(".nav2").hide();
        }
    })
})

var tew=document.getElementById('cont')
function getTime(){     	//获取时间
    var date=new Date();

    var year=date.getFullYear();
    var month=date.getMonth()+2;
    var day=date.getDate();

    var hour=date.getHours();
    var minute=date.getMinutes();
    var second=date.getSeconds();

    //这样写显示时间在1~9会挤占空间；所以要在1~9的数字前补零;
    if (hour<10) {
        hour='0'+hour;
    }
    if (minute<10) {
        minute='0'+minute;
    }
    if (second<10) {
        second='0'+second;
    }


    var x=date.getDay();//获取星期



    var time=year+'/'+month+'/'+day+'/'+hour+':'+minute+':'+second
    tew.innerHTML=time;//将时间显示在div内
}


// $(function () {
//     setInterval(function () {
//         $("#autore").load(location.href + " #autore");//注意后面DIV的ID前面的空格，很重要！没有空格的话，会出双眼皮！（也可以使用类名）
//     }, 8000);//8秒自动刷新
// })
// $.ajax({
//     url: "index.html",
//     context: document.body,
//     success: function(){
//         $('#nav_bottom_left_button').html('请求得到的数据内容');
//
//     }});
//
