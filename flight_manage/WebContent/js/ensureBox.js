$(function () {
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

	//点击模态框提交按钮时提交表单
	$('.modal-footer > .btn-primary').click(function () {
		$('form').submit();
	});
});
