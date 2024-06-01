jQuery.noConflict();
jQuery(document).ready(function($) {

	var supplierId = $("#supplierId").val();
	console.log("supplier Id:", supplierId); // Output: 123

	var contactDetails = {}; // Object to store contact details

	var table = $('#productTable').DataTable({
		"processing": true,
		"serverSide": false, // Change to true if using server-side processing
		"ajax": {
			"url": "/CRM/api/products/allproducts/medical",
			"dataSrc": ""
		},
		"columns": [
			{ "data": "#" },
			{ "data": "productId" },
			{ "data": "productName" },
			{
				"data": null,
				"render": function(data, type, row) {
					var html = '<div id="contact_' + row.productId + 'contact-details" class="contact-details">';
					html += '<button class="btn btn-primary btn-sm add-contact" data-product-id="' + row.productId + '">Add Contact</button>';
					html += '</div>';
					html += '<div><button id="contactbtn_' + row.productId + '" class="btn btn-primary btn-sm save-contact" style="display:none" data-product-id="' + row.productId + '">Save</button></div>';
					return html;
				}
			}

		],"columnDefs": [
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


	//add existing contacts
	var contactssettings = {
		"url": "/CRM/allproductsuppliers/" + supplierId,
		"method": "get",
		"timeout": 0,
		"headers": {
			"Content-Type": "application/json"
		},

	};

	$.ajax(contactssettings).done(function(response) {
		console.log(response);
		//Assuming response is your AJAX response array
		response.forEach(item => {
			let contacts = item.contacts;
			let productId = item.productId;
			let psid = item.psid;

			contacts.forEach(contact => {
				let contactName = contact.contactName;
				let phoneNumber = contact.phoneNumber;
				let email = contact.email;


				// Create container for contact details
				let container = document.getElementById('contact_' + productId + 'contact-details'); // Assuming 'container' is the id of a container element
				let div = document.createElement('div');
				div.classList.add('form-row', 'align-items-center');

				// Create input fields for contact details
				let nameInput = document.createElement('input');
				nameInput.type = 'text';
				nameInput.value = contactName;
				nameInput.classList.add('form-control', 'col-3', 'mb-1');
				nameInput.setAttribute('readonly', true);
				div.appendChild(nameInput);

				let phoneInput = document.createElement('input');
				phoneInput.type = 'text';
				phoneInput.value = phoneNumber;
				phoneInput.classList.add('form-control', 'col-3', 'mb-1');
				phoneInput.setAttribute('readonly', true);
				div.appendChild(phoneInput);

				let emailInput = document.createElement('input');
				emailInput.type = 'text';
				emailInput.value = email;
				emailInput.classList.add('form-control', 'col-3', 'mb-1');
				emailInput.setAttribute('readonly', true);
				div.appendChild(emailInput);

				// Create delete button
				let deleteButton = document.createElement('button');
				deleteButton.textContent = 'Delete';
				deleteButton.classList.add('btn', 'btn-danger', 'mb-1');
				deleteButton.setAttribute('id', psid);
				deleteButton.setAttribute('style', 'margin-left: 10px');
				deleteButton.addEventListener('click', (event) => {
					let psToDelete = event.target.id;
					if (window.confirm('Are you sure you want to delete this contact ?')) {
						// Logic to delete the contact
						$.ajax({
							url: '/CRM/deleteps/' + psToDelete,
							type: 'DELETE',
							contentType: 'application/json',
							success: function(response) {
								console.log('Product supplier deleted successfully');
								// Optionally handle the response if needed
								div.remove(); // Remove the contact's details from the UI
								//   location.reload();
							},
							error: function(jqXHR, textStatus, errorThrown) {
								console.error('Error deleting product supplier:', errorThrown);
							}
						});

					}
				});
				div.appendChild(deleteButton);

				container.appendChild(div);
			});
		});

	});



	// Add contact button functionality
	$('#productTable tbody').on('click', 'button.add-contact', function() {
		var productId = $(this).data('product-id');
		var contactDiv = $('#contact_' + productId + 'contact-details');
		var index = contactDiv.children().length + 1;
		var phoneId = 'phone_' + productId;
		var emailId = 'email_' + productId;
		var contactid = 'contact_' + productId; //+ '_' + index;
		var html = '<div class="mb-2 form-row align-items-center" >';
		html += '<input type="text" id="' + contactid + '" class="form-control col-3 mb-1" placeholder="Contact Name">';
		html += '<input type="text" id="' + phoneId + '" class="form-control col-3 mb-1" placeholder="Phone Number">';
		html += '<input type="email" id="' + emailId + '" class="form-control col-3 mb-1" placeholder="Email">';
		html += '<button class="btn btn-danger btn-sm delete-contact" style="margin-left: 10px" data-product-id="' + productId + '">Delete</button>';
		html += '</div>';
		contactDiv.append(html);
		$('#contactbtn_' + productId).show();
	});

	// Delete contact button functionality
	$('#productTable tbody').on('click', 'button.delete-contact', function() {
		var productId = $(this).data('product-id');
		$(this).closest('.mb-2').remove();
		$('#contactbtn_' + productId).hide();
	});


	$('#productTable tbody').on('click', 'button.save-contact', function() {
		var productId = $(this).data('product-id');
		var contactDiv = $('#contact_' + productId + 'contact-details'); // Target the specific contact details section
		var contacts = [];
		var prodsupp = {};
		var supplier = {};
		var product = {};
		supplier.supplierId = supplierId;
		product.productId = productId.toString();

		prodsupp.supplier = supplier;
		prodsupp.product = product;
		var isValid = true;

		// Iterate over each contact within the contact details section
		contactDiv.find('.mb-2').each(function() {
			var contactDetailsDiv = $(this);
			var contactName = contactDetailsDiv.find('input[type="text"]').val();
			var phoneNumber = contactDetailsDiv.find('input[type="text"]').eq(1).val(); // Get the second input (phone)
			var email = contactDetailsDiv.find('input[type="email"]').val();

			// Validate fields
			if (contactName.trim() === '' || phoneNumber.trim() === '' || email.trim() === '') {
				isValid = false;
				alert('Please fill Contact Details before saving!!');
				return false; // Exit the loop early if any field is empty
			}


			contacts.push({ contactName: contactName, phoneNumber: phoneNumber, email: email });
		});

		if (!isValid) {
			return;
		}

		// Alert the contact details
		// alert(JSON.stringify(contacts, null, 2));
		prodsupp.contacts = contacts;
		var datass = JSON.stringify(prodsupp);
		console.log(datass)
		var settings = {
			"url": "/CRM/createps",
			"method": "POST",
			"headers": {
				"Content-Type": "application/json"
			},
			"data": datass,
		};

		$.ajax(settings).done(function(response) {
			console.log(response);
			contactDiv.find('input').prop('readonly', true);
			$('#contactbtn_' + productId).hide();
		});

	});


	var GNtable = $('#GeneralproductTable').DataTable({
		"processing": true,
		"serverSide": false, // Change to true if using server-side processing
		"ajax": {
			"url": "/CRM/api/products/allproducts/general",
			"dataSrc": ""
		},
		"columns": [
			{ "data": "#" },
			{ "data": "productId" },
			{ "data": "productName" },
			{
				"data": null,
				"render": function(data, type, row) {
					var html = '<div id="contact_' + row.productId + 'contact-details" class="contact-details">';
					html += '<button class="btn btn-primary btn-sm add-contact" data-product-id="' + row.productId + '">Add Contact</button>';
					html += '</div>';
					html += '<div><button id="contactbtn_' + row.productId + '" class="btn btn-primary btn-sm save-contact" style="display:none" data-product-id="' + row.productId + '">Save</button></div>';
					return html;
				}
			}

		],"columnDefs": [
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
	
});