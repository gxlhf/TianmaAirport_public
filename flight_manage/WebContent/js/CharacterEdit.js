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
	});

	//绑定左右移动的多选列表
	var selected_count = 0;
	$('.select-table tbody > tr').click(function (e) {
		if($(this).children('td:nth-child(2)').text() != '')
			$(this).find('.glyphicon').toggleClass('glyphicon-check');
	});
	$('#btn-add').click(function () {
		var select_elem = $('#left-side-table td>.glyphicon-check:not(.td-check)');
		select_elem.addClass('td-check');

		var right_side_elem = $('#right-side-table td:nth-child(2):not(th)');
		select_elem.each(function () {
			var op_text = $(this).parent().next('td').text();
			$(right_side_elem[selected_count]).text(op_text);
			selected_count++;
		})
	});
	$('#btn-remove').click(function () {
		var select_elem = $('#right-side-table td>.glyphicon-check:not(.td-check)').parent().next('td');
		console.log(select_elem);

		for (var i = 0; i < select_elem.length; i++) {
			var empty_tr = $('<tr data-id="10003"><td><span class="glyphicon"></span></td><td></td></tr>');
			empty_tr.click(function (e) {
				if($(this).children('td:nth-child(2)').text() != '')
					$(this).find('.glyphicon').toggleClass('glyphicon-check');
			});
			empty_tr.insertBefore('#right-side-table tr:last');
		}

		select_elem.each(function () {
			$(this).prev().children().attr('class', 'glyphicon');
			var left_elem = $("td:contains('" + $(this).text() + "')");
			left_elem.prev().children().attr('class', 'glyphicon');
			$(this).parent().remove();
			// $('#right-side-table tr:last').before(empty_tr);
			selected_count--;
		});
	});
	//绑定全选事件
	$('th > span').click(function () {
		console.log($(this).parents('table').find('tbody > tr'));
		$(this).parents('table').find('tbody > tr .glyphicon').addClass('glyphicon-check');
	});
	//输入已分配的权限
	$('#btn-save').click(function () {
		var select_elem = $('#right-side-table td:nth-child(2):not(:empty)');
		console.log(select_elem);
		for (var i = 0; i < select_elem.length - 1; i++) {
			var lastVal = $('[name="rolePriv"]').val();
			if (lastVal != '') {
				lastVal += ',';
			}
			$('[name="rolePriv"]').val(lastVal + $(select_elem[i]).text());
		}
	});
});