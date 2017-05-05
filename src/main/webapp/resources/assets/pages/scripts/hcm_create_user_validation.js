var FormValidationMd = function() {
	var e = function() {
		var e = $("#frmUser"), r = $(".alert-danger", e), i = $(
				".alert-success", e);
		e
				.validate({
					errorElement : "span",
					errorClass : "help-block help-block-error",
					focusInvalid : !1,
					ignore : "",
					messages : { 
						"frmUser:j_idt157" : {
							required : "Please check some options",
							minlength : jQuery.validator
									.format("At least {0} items must be selected")
						},
						"frmUser:email" : {
							required : "Email is invalid!",
							email : "Email is invalid!"
						} 
					},
					rules : {
						'frmUser:firstname' : {
							minlength : 2,
							required : !0
						},
						'frmUser:lastname' : {
							minlength : 2,
							required : !0
						},
						'frmUser:email' : {
							required : !0,
							email : true
						}, 
						'frmUser:password' : {
							required : !0 
						}, 
						"frmUser:j_idt157" : {
							required : !0,
							minlength : 2
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