var UIConfirmations = function() {
	var n = function() {
		$('.bs_confirm').confirmation({
			placement : 'top',
			title : 'Are you sure you want to delete?',
			singleton : true,
			btnOkClass : 'btn-success',
			btnOkIcon : 'icon-like',
			btnCancelClass : 'btn-danger',
			btnCancelIcon : 'icon-close',
			onConfirm: function () {
				App.startPageLoading({
					animate : !0
				}), window.setTimeout(function() {
					App.stopPageLoading()
				}, 2e3)
			}
		});
		
		$('.bs_confirm').confirmation();
	
		$('.btndeleteall').confirmation({
			title : 'Are you sure?',
			singleton : true,
			placement: 'right',
			btnOkClass : 'btn-success',
			btnOkIcon : 'icon-like',
			btnCancelClass : 'btn-danger',
			btnCancelIcon : 'icon-close',
			onConfirm: function () {
				App.startPageLoading({
					animate : !0
				}), window.setTimeout(function() {
					App.stopPageLoading()
				}, 2e3)
			}
		});
		
		$('.btndeleteall').confirmation();
				
	};
	return {
		init : function() {
			n()
		}
	}
}();
jQuery(document).ready(function() {
	UIConfirmations.init()
});