$(function () {
	//信息编辑页面-点击保存时弹框
	$('#btn-save').click(function (e) {
		$(this).parents('form').validator('validate');
		var validator_passed = !($('.has-error').length);
		if (validator_passed) {			
			//填写确认弹框
			var dataElem = $(this).parents('form').find('input, textarea, select');
			dataElem.each(function () {
				if($(this).attr('type') == 'radio' && (!this.checked))
					return;
				$('#' + $(this).attr('name') + '-ensure').text($(this).val());
			});
			$('#ensureBox').modal('show');
		}
	});

	//信息编辑页面-点击模态框提交按钮时提交表单
	$('.modal-footer > .btn-primary').click(function () {
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
			console.log($('.modal-body').find('label:contains("' + $(thElem[i]).text() + '：")').next().find('p'));
			//console.log($('.modal-body').find('label'));
			$('.modal-body').find('label:contains("' + $(thElem[i]).text() + '：")').next().find('p').text($(deletingElem[i]).text());
		}
		$('#ensureBox').modal('show');
		return false;
	});

	// 信息列表页面-点击模态框提交按钮时执行删除
	$('.modal-footer > .btn-danger').click(function () {
		location.href = $('#btn-delete').attr('href') + linkWord + opValue;
	});
});
