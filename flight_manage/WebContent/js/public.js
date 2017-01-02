var opValue = '';
var linkWord = '?';

$('.select-table tbody > tr').click(function (e) {
	opValue = $(this).attr('data-id');
	$(this).parent().find('.glyphicon').removeClass('glyphicon-check');
	$(this).find('.glyphicon').addClass('glyphicon-check');
	
	if (opValue[0] == '&')
		linkWord = '';
});

$('#btn-modify').click(function (e) {
	if (opValue == '') {
		$('#errorBox').modal('show');
		return false;
	}
	$(this).attr('href', $('#btn-modify').attr('href') + linkWord + opValue);
});

function getBrowser(){
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器
    var isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器
    var isChrome = userAgent.indexOf("Chrome") > -1;//判断是否为Chrome
    var isSafari = userAgent.indexOf("Safari") > -1; //判断是否Safari浏览器
    if (isIE) {
        var IE5 = IE55 = IE6 = IE7 = IE8 = false;
        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        reIE.test(userAgent);
        var fIEVersion = parseFloat(RegExp["$1"]);
        IE55 = fIEVersion == 5.5;
        IE6 = fIEVersion == 6.0;
        IE7 = fIEVersion == 7.0;
        IE8 = fIEVersion == 8.0;
        IE9 = fIEVersion == 9.0;
        IE10 = fIEVersion == 10.0;
        if (IE55 | IE6 | IE7 | IE8 | IE9) {
            return "IE9";
        }
        if (IE10) {
            return "IE10";
        }
    }//isIE end
    if (isFF) 
        return "FF";
    if (isOpera) 
        return "Opera";
    if(isChrome)
    	return "Chrome";
    if(isSafari && !isChrome)
    	return "Safari";
}

$(function () {
	if(getBrowser() == 'IE9'){
		alert("本网站不支持使用IE9及更旧版本的IE浏览器访问，请升级您的浏览器");
		if((window.location.href).search("Edit.jsp") != -1)
			window.location.href = "../index.jsp";
	}
	
	//绑定时间控件
	var datetimeInputs = $('[type="datetime"]:input');
	if(datetimeInputs.length > 0)
	{
		datetimeInputs.each(function () {
			var min = $(this).attr('minView');
			var max = $(this).attr('maxView');
			var start = $(this).attr('startView');
			var form = $(this).attr('format');
			var date_config = {
		        format: form,
		        language:  'zh-CN',
		        weekStart: 1,
		        todayBtn:  0,
		        autoclose: 1,
		        todayHighlight: 1,
		        startView: start,
		        minView: min,
		        maxView: max,
		        forceParse: 1,
		        minuteStep: 1
		    };

			$(this).datetimepicker(date_config);
		})
	};

	//显示天气
	$.ajax({
		type: "GET",
		url: "https://free-api.heweather.com/v5/now?city=changsha&key=e8afecb61bec489db5a206efef55d23f",
		dataType: "json",
		success: function (resp) {
			var weather = resp.HeWeather5[0];
			var pageElem = $('#weather');
			pageElem.html(weather.basic.city + " " + "<img src = 'http://files.heweather.com/cond_icon/" + weather.now.cond.code + ".png'> " +  weather.now.cond.txt + " " + weather.now.tmp + "℃");
		}
	});

	//回到顶部按钮
	$(document).scroll(function () {
		if($("#backToTop-btn").length != 0){
			if($(window).scrollTop() > 113)
				$("#backToTop-btn").fadeIn(500);
			else 
				$("#backToTop-btn").fadeOut(500);
		}
	});

	$("[data-toggle='popover']").popover({
		html : true,
		trigger : 'hover'
	});

	
	var inputElem = $('form').find('input,textarea');
	for (var i = 0; i < inputElem.length; i++) {
		$(inputElem[i]).blur(function (e) {
			var temp;
			temp = $(this).val().replace(/\+/g,"＋");
			temp = temp.replace(/\&/g,"＆");
			temp = temp.replace(/\#/g,"＃");
			temp = temp.replace(/\"/g,"＂");
			temp = temp.replace(/\</g,"＜");
			temp = temp.replace(/\>/g,"＞");
			temp = temp.replace(/\=/g,"＝");			
			temp = temp.replace(/\\/g,"＼");
			$(this).val(temp);
			temp = '';
		});
	}

});


//关闭click.bs.dropdown.data-api事件，使顶级菜单可点击
// $(document).off('click.bs.dropdown.data-api');
// //自动展开
// $('.nav .dropdown').mouseenter(function(){
//  $(this).addClass('open');
// });
// //自动关闭
// $('.nav .dropdown').mouseleave(function(){
//  $(this).removeClass('open');
// });


// Bootstrap Hover Dropdown
// ;(function($, window, undefined) {
//     // outside the scope of the jQuery plugin to
//     // keep track of all dropdowns
//     var $allDropdowns = $();

//     // if instantlyCloseOthers is true, then it will instantly
//     // shut other nav items when a new one is hovered over
//     $.fn.dropdownHover = function(options) {

//         // the element we really care about
//         // is the dropdown-toggle's parent
//         $allDropdowns = $allDropdowns.add(this.parent());

//         return this.each(function() {
//             var $this = $(this).parent(),
//                 defaults = {
//                     delay: 500,
//                     instantlyCloseOthers: true
//                 },
//                 data = {
//                     delay: $(this).data('delay'),
//                     instantlyCloseOthers: $(this).data('close-others')
//                 },
//                 options = $.extend(true, {}, defaults, options, data),
//                 timeout;

//             $this.hover(function() {
//                 if(options.instantlyCloseOthers === true)
//                     $allDropdowns.removeClass('open');

//                 window.clearTimeout(timeout);
//                 $(this).addClass('open');
//             }, function() {
//                 timeout = window.setTimeout(function() {
//                     $this.removeClass('open');
//                 }, options.delay);
//             });
//         });
//     };

//     $('[data-hover="dropdown"]').dropdownHover();
// })(jQuery, this);