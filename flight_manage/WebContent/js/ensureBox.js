$(function () {
	var validator_passed = true;

	$('[data-toggle="validator"]').validator().on('invalid.bs.validator',function () {
		validator_passed = false;
	});


	$('#btn-save').click(function (e) {
		validator_passed = true;
		$(this).parents('form').validator('validate');
		if (validator_passed) {			
			//填写确认弹框
			var dataElem = $(this).parents('form').find('input, textarea');
			dataElem.each(function () {
				$('#' + $(this).attr('name') + '-ensure').text($(this).val());
			});
			$('#ensureBox').modal('show');
		}		
	});
});