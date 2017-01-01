function html2n() {
	//将新闻页面新闻正文的html标签替换为换行
	if($('[name="news-context"]').length != 0){
		var newsContext = $('[name="news-context"]').val();
		while(newsContext.search('</p><p>') != -1){
			newsContext = newsContext.replace('</p><p>', "\n");
		}
		$('[name="news-context"]').val(newsContext);
	}
}

function n2html() {
	//将新闻页面新闻正文的换行替换为html标签
	if($('[name="news-context"]').length != 0){
		var newsContext = $('[name="news-context"]').val();
		newsContext = newsContext.replace(/\n/g, '</p><p>');
		$('[name="news-context"]').val(newsContext);
	}
}

$(function () {

	html2n();

	//信息编辑页面-点击保存时弹框
	$('#btn-save').click(function (e) {
		$(this).parents('form').validator('validate');
		var validator_passed = !($('.has-error').length);

		if($('#inp-flight-counter-mirror').length != 0){
			validator_passed = ($('#inp-flight-counter-mirror').val() != '') && validator_passed;
		}

		if (validator_passed) {			

			n2html();

			//填写确认弹框
			var dataElem = $(this).parents('form').find('input, textarea, select');
			dataElem.each(function () {
				if($(this).attr('type') == 'radio' && (!this.checked))
					return;
				$('#' + $(this).attr('name') + '-ensure').html($(this).val());
			});
			$('#ensureBox').modal('show');
			html2n();
		}
	});
    $('#btn-save').click(function (e) {
        $('#inp-flight-counter-mirror').focus();
        $('#inp-flight-counter-mirror').blur();
    });

	//信息编辑页面-点击模态框提交按钮时提交表单
	$('.modal-footer > .btn-primary').click(function () {
		n2html();
		$('form').submit();
	});
 

	// 信息列表页面-点击删除时弹框
	$('#btn-delete').click(function () {
		var thElem = $('th');
		var deletingElem = $('[data-id="' + opValue + '"]').children('td');

		if (deletingElem.length == 0){
			$('#errorBox').modal('show');
			return false;
		}

		for (var i = 1; i < deletingElem.length; i++) {
			$('.modal-body').find('label:contains("' + $(thElem[i]).text() + '：")').next().find('p').html($(deletingElem[i]).text());
		}
		$('#ensureBox').modal('show');
		return false;
	});

	// 信息列表页面-点击模态框提交按钮时执行删除
	$('.modal-footer > .btn-danger').click(function () {
		location.href = $('#btn-delete').attr('href') + linkWord + opValue;
	});



	// 未保存离开当前页提示
	if((window.location.href).search("Edit.jsp") != -1 || (window.location.href).search("EditMyInfo.jsp") != -1){
		window.onbeforeunload = function(e){
			var srcElem = document.activeElement;
			var shallAlert = ($(srcElem).parents("#ensureBox").length == 0);
			var isSafari = (getBrowser() == "Safari");
			if(shallAlert && !isSafari)
				return "当前页面信息尚未保存，确认离开？";
			else if(shallAlert && isSafari)
				return  confirm("当前页面信息尚未保存，确认离开？"); 
			else
				return;
		}

	}

});
