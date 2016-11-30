$(function () {
	$('#btn-save').click(function () {
		var dataElem = $(this).parents('form').find('input, textarea');
		console.log(dataElem);
		dataElem.each(function () {
			$('#' + $(this).attr('name') + '-ensure').text($(this).val());
		})
	})
});