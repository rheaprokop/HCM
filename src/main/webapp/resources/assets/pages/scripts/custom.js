var UICustoms = function() {
	var n = function() {
		 $('.group-checkable').change(function() {
			  if ($(this).is(':checked')) {
				  $(".btndeleteall").show();
			  } else {
				  $(".btndeleteall").hide();
			  }
			});v
	};
	return {
		init : function() {
			n()
		}
	}
}();
jQuery(document).ready(function() {
	UICustoms.init()
});