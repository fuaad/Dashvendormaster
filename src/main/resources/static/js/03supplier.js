jQuery.noConflict();
jQuery(document).ready(function($) {

	//supplier
	var table = $('#SupplierTable').DataTable({
		"processing": true,
		"serverSide": false, // Change to true if using server-side processing
		"ajax": {
			"url": "/CRM/api/supplier/allsuppliers2",
			"dataSrc": ""
		},
		"columns": [
			{ "data": "#" },
			{ "data": "supplierId" },
			{ "data": "supplierName" },
			{ "data": "supplierCode" },
			{
				"data": null,
				"defaultContent": '<button class="btn btn-info info-btn" data-toggle="modal" data-target="#myModal" >View Credential</button>',
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

	//Add Supplier
	$('#addSupplierbtn').on('click', function() {

		var supplierName = $('#supplierName').val();
		var supplierCode = $('#supplierCode').val();
		var location = $('#location').val();
		var Username = $('#Username').val();
		var Password = $('#Password').val();
		var role = "supplier";
		if (!supplierName.trim() || !Username.trim() || !Password.trim() || !supplierCode.trim()) {
			alert("Supplier Name, Username, and Password are mandatory fields.");
		} else {
			var supplierdata = {
				"supplierName": supplierName,
				"supplierCode": supplierCode,
				"location": location,
				"username": Username,
				"password": Password,
				"role": role
			};
			console.log(supplierdata);

			// Making the AJAX request
			var settings = {
				"url": "/CRM/api/supplier/create",
				"method": "POST",
				"headers": {
					"Content-Type": "application/json"
				},
				"data": JSON.stringify(supplierdata),
				"error": function(xhr, status, error) {
					console.error("Error:", error);
					alert("Username already exist");
				}
			};

			$.ajax(settings).done(function(response) {
				console.log(response);
				window.location.reload();

			});
		}
	});

	$('#SupplierTable tbody').on('click', '.info-btn', function() {
		var data = table.row($(this).parents('tr')).data();
		var supplierId = data.supplierId;
		

		// Make AJAX request to fetch user credentials
		$.ajax({
			url: '/CRM/api/supplier/getUserCredentials',
			method: 'GET',
			data: { supplierId: supplierId },
			success: function(response) {
				// Assuming response contains the username and password
				
				$('#viewUsername').val(response.username);
				$('#viewPassword').val(response.password);
				
			},
			error: function(xhr, status, error) {
				console.error('Error fetching credentials:', error);
			}
		});
	});


});