jQuery.noConflict();
jQuery(document).ready(function($) {

	var table = new DataTable('#productTable', {

		layout: {
			topStart: {
				buttons: ['copyHtml5', 'excelHtml5', 'csvHtml5', 'pdfHtml5']
			}
		}
	});
	var data;
	var settings = {
		"url": "/CRM/getAllProductsps",
		"method": "GET",
		"timeout": 0,

	};

	$.ajax(settings).done(function(response) {
		console.log(response);
		data = response;

		data.forEach(function(item) {
			item.suppliers.forEach(function(supplier) {
				supplier.contacts.forEach(function(contact) {
					table.row.add([item.productName, supplier.name, contact.name, contact.phone, contact.email]).draw();
				});
			});
		});
	});


});