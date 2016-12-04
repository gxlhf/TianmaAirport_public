$(window).resize(function () {
	$('.item').height($(window).height() - $('nav').height());
});
$('#box-search button').click(function (e) {
	e.preventDefault();
	var flight_num_exp = /([a-z]{2}[0-9]{4})/i;
	var form_elem = $(this).parent();
	console.log(flight_num_exp.test(form_elem.find('[name = "key"]').val()));
	// form_elem.submit();
});
$('#carousel').carousel({
	keybord: false
});
$(function () {
	$('.item').height($(window).height() - $('nav').height());
});