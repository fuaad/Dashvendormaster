jQuery.noConflict();
jQuery(document).ready(function($) {
	
	var GNtable = $('#GeneralproductTable').DataTable({
		"processing": true,
		"serverSide": false, // Change to true if using server-side processing
		"ajax": {
			"url": "/CRM/api/products/allproducts/general",
			"dataSrc": ""
		},
		"columns": [
			{ "data": null },
			{ "data": "productId" },
			{ "data": "productName" },
			{
				"data": null,
				"defaultContent": '<button class="btn btn-danger delete-btn">Delete</button>',
				"orderable": false
			}
		],
		"columnDefs": [
			{
				"targets": 0,
				"orderable": false,
				"searchable": false,
				"render": function(data, type, row, meta) {
					return meta.row + 1; // Add custom numbering
				}
			},
			{
				"targets": 1,
				"visible": false // Hide the productId column
			}
		],
		"pageLength": 15, // Default number of rows per page
		"searching": true, // Disable default searching behavior
		"ordering": false, // Disable sorting by clicking on column headers
		"info": true, // Show "Showing x to y of z entries" information
		"autoWidth": false // Disable automatic column width calculation
	});
	
	//Add General
	$("#addGeneralProductbtn").on('click', function() {

		var productName = $("#GNproductName").val();
		var CategoryType = "General";
		var form = new FormData();
		form.append("productName", productName);
		form.append("CategoryType", CategoryType);

		var settings = {
			"url": "/CRM/api/products/create",
			"method": "POST",
			"timeout": 0,

			"processData": false,
			"mimeType": "multipart/form-data",
			"contentType": false,
			"data": form
		};

		$.ajax(settings).done(function(response) {
			console.log(response);
			$("#productName").val("");
			alert("Category added successfully")
			GNtable.ajax.reload();
		});
	});
	
		// Handle delete button click
	$('#GeneralproductTable tbody').on('click', '.delete-btn', function() {
		var data = GNtable.row($(this).parents('tr')).data();
		var productId = data.productId;

		if (confirm('Are you sure you want to delete this product?')) {
			$.ajax({
				url: '/CRM/api/products/delete/' + productId,
				method: 'DELETE',
				success: function(response) {
					alert('Product deleted successfully');
					GNtable.ajax.reload(); // Reload the table data
				},
				error: function(xhr, status, error) {
					alert('Error deleting product: ' + error);
				}
			});
		}
	});
});