$(function () {
	$('[name="flight-no"]').blur(function () {
		console.log($(this));
		$(this).val($(this).val().toUpperCase());
	});
})