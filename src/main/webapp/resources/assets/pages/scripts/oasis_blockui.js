/**
 * 
 */
$('.blockui_css3').click(function() {
	App.startPageLoading({
		animate : true
	});

	window.setTimeout(function() {
		App.stopPageLoading();
	}, 2000);
});