$(document).ready(function() {
	$("#frmcomponent").validate({
		errorElement : "span",
		errorClass : "help-block help-block-error",
		focusInvalid : !1,
		ignore : "",
		rules : {
			'frmcomponent:title' : {
				maxlength : 20,
				required : true
			},
			'frmcomponent:compdesc' : {
				maxlength : 100,
				required : true
			},
			'frmcomponent:icon' : {
				valueNotEquals : "---Select An Icon---"
			},
		},
		errorPlacement : function(e, r) {
			var i = $(r).parent(".input-icon").children("i");
			i.removeClass("fa-check").addClass("fa-warning"), i.attr(
					"data-original-title", e.text()).tooltip({
				container : "body"
			})
		},
		highlight : function(e) {
			$(e).closest(".form-group").removeClass("has-success")
					.addClass("has-error")
		},
		unhighlight : function(e) {
		}
	});
});