$(window).resize(function () {
	$('.item').height($(window).height() - $('nav').height());
});
$('#box-search button').click(function (e) {
	e.preventDefault();
	var flight_num_exp = /([a-z]{2}[0-9]{3,4}|[a-z]{1}[0-9]{4,5}|[0-9]{1}[a-z]{1}[0-9]{3,4})/i;
	var form_elem = $(this).parent();
	var key_text = form_elem.find('[name = "key"]').val().toUpperCase();
	$('[name="is_flightNo"]').val(flight_num_exp.test(key_text));
	if(form_elem.find('[name = "key"]').val() == ''){
		$('#errorBox').modal('show');
		return;
	}

	form_elem.submit();
});

$(function () {
	$('.item').height($(window).height() - $('nav').height());
});