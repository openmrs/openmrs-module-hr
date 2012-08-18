jQuery(document).ready(function() {
	hr = {

		/**
		 * Displays feedback information on whether a save operation has completed successfully.
		 *
		 * @param element The element to display the feedback information in
		 * @param result A result object containing a <code>code</code> and a <code>message</code> field
		 */
		setSaveResult : function(element, result) {
			if (result.code == "success") {
				element.attr('class', 'success');
			} else {
				element.attr('class', 'error');
				jQuery('#installSampleData').removeAttr('disabled');
			}
			element.text(result.message);
			element.fadeIn();
			setTimeout( function(){ element.fadeOut(); }, 2000 );
		},

		/**
		 * Saves a global property.
		 *
		 * A result is be passed to the callback function containing a result code ("success" or other) and
		 * a message explaining the code.
		 *
		 * @param property
		 * @param value
		 * @param resultElement
		 */
		saveGlobalProperty : function(property, value, resultElement) {
			jQuery.ajax({
				url : openmrsContextPath + "/ws/rest/v1/hr?saveGlobalProperty",
				type : "POST",
				contentType : "application/x-www-form-urlencoded",
				data: "property=" + property + "&value=" + value,
				success : function(result) {
					hr.setSaveResult(resultElement, result);
				}
			});
		},

	};
});


jQuery(document).ready(function() {

	jQuery('.globalPropertySubmit').click(function(event) {
		event.preventDefault();
		var btnId = jQuery(this).attr('id');
		var gpId = btnId.substring(btnId.lastIndexOf("_")+1);
		var textFieldSelector = '#globalPropertySelect_'+gpId;

		var gpName = jQuery(textFieldSelector).attr('name');
		var gpValue = jQuery(textFieldSelector).val();

		hr.saveGlobalProperty( gpName, gpValue, jQuery('#globalPropertyResult_'+gpId) );
	});

	jQuery('#installSampleData').click(function(event) {
		event.preventDefault();
		jQuery('#installSampleData').attr('disabled', 'disabled');
		jQuery.ajax({
			"url" : "setup/installSampleData.htm",
			"type" : "GET",
			"success" : function(result) {
				hr.setSaveResult(jQuery('#installationResult'), result);
			},
		});
	});

	jQuery('#finishSetup').click(function(event) {
		event.preventDefault();
		jQuery.ajax({
			"url" : "setup/finishSetup.htm",
			"type" : "GET",
			"success" : function(view) {
				window.location.href = view;
			}
		});
	});

});

