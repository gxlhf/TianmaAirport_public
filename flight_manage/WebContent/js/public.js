$('.select-table tbody > tr').click(function (e) {
	var opValue = $(this).attr('data-id');
	console.log(opValue);
	$(this).parent().find('.glyphicon').removeClass('glyphicon-check');
	$(this).find('.glyphicon').addClass('glyphicon-check');
	console.log($(this).parent().find('[name="select-table-result"]'));
	// $('[name="selected-option"]').val(opValue);
	
	var linkWord = '?';
	console.log(opValue[0]);
	if (opValue[0] == '&')
		linkWord = '';
	$('#btn-modify').attr('href', $('#btn-modify').attr('href') + linkWord + opValue);
	$('#btn-delete').attr('href', $('#btn-delete').attr('href') + linkWord + opValue);
});

$('#btn-modify').click(function (e) {
	// $(this).attr('href', $(this).attr('href') + '?' + $('[name="selected-option"]').val());
	console.log($(this).attr('href'));
});
$('#btn-delete').click(function (e) {
	// $(this).attr('href', $(this).attr('href') + '?' + $('[name="selected-option"]').val());
	console.log($(this).attr('href'));
});

$(function () {
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

		    console.log(min, max, start, date_config);

			$(this).datetimepicker(date_config);
		}
		)
	};

	//显示天气
	$.ajax({
		type: "GET",
		url: "https://free-api.heweather.com/v5/now?city=changsha&key=e8afecb61bec489db5a206efef55d23f",
		dataType: "json",
		success: function (resp) {
			var weather = resp.HeWeather5[0];
			console.log(resp);
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
	})
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