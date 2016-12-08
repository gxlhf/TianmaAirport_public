$(function () {
	$('#btn-save').click(function (e) {
		console.log(e);

		//填写确认弹框
		var dataElem = $(this).parents('form').find('input, textarea');
		dataElem.each(function () {
			$('#' + $(this).attr('name') + '-ensure').text($(this).val());
		});
	})
});