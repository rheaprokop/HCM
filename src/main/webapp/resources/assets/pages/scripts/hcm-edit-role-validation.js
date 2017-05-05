var FormValidationMd = function() {
	var e = function() {
		var e = $("#frmRole"), r = $(".alert-danger", e), i = $(
				".alert-success", e);
		e
				.validate({
					errorElement : "span",
					errorClass : "help-block help-block-error",
					focusInvalid : !1,
					ignore : "",
					messages : { 
						"frmRole:j_idt167" : {
							required : "Please check some options",
							minlength : jQuery.validator
									.format("At least {0} items must be selected")
						} 
					},
					rules : {
						'frmRole:rolename' : {
							minlength : 2,
							required : !0
						},
						'frmRole:roledescription' : {
							minlength : 2,
							required : !0
						} 
					},
					highlight : function(e) {
						$(e).closest(".form-group").addClass("has-error")
					},
					unhighlight : function(e) {
						$(e).closest(".form-group").removeClass("has-error")
					}
				})
	};
	return {
		init : function() {
			e()
		}
	}
}();
jQuery(document).ready(function() {
	FormValidationMd.init()
});