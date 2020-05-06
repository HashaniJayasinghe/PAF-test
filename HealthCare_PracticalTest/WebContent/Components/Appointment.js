$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

// SAVE===============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts-----------------------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation--------------------------------
	var status = validateAppointmentForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;

	}

	// If valid---------------------------------------

	var type = ($("#hidAppIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "AppointmentAPI",
		type : type,
		data : $("#formAppointment").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onAppointmentSaveComplete(response.responseText, status);
		}
	});
});

function onAppointmentSaveComplete(response, status) {

	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();

			$("#divAppGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	} else {
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidAppIDSave").val("");
	$("#formAppointment")[0].reset();
}

// UPDATE===================================================================
$(document).on("click",".btnUpdate", function(event){
	$("#hidAppIDSave").val($(this).closest("tr").find('#hidAppIDUpdate').val());
	$("#name").val($(this).closest("tr").find('td:eq(0)').text());
	$("#email").val($(this).closest("tr").find('td:eq(1)').text());
	$("#phoneNo").val($(this).closest("tr").find('td:eq(2)').text());
	$("gender").val($(this).closest("tr").find('td:eq(3)').text());
	$("symptoms").val($(this).closest("tr").find('td:eq(4)').text());
	$("date").val($(this).closest("tr").find('td:eq(5)').text());
	$("time").val($(this).closest("tr").find('td:eq(6)').text());
	$("appointmentType").val($(this).closest("tr").find('td:eq(7)').text());
	$("hospital").val($(this).closest("tr").find('td:eq(8)').text());

});

//REMOVE=================================================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "AppointmentAPI",
		type : "DELETE",
		data : "appointmentId=" + $(this).data("appointmentId"),
		dataType : "text",
		complete : function(response, status) {
			onAppointmentDeletedComplete(response.responseText, status);
		}
	});

});

function onAppointmentDeletedComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();

			$("#divAppGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

// CLIENT-MODEL=======================================

function validateAppointmentForm() {
	// NAME
	if ($("#name").val().trim() == "") {
		return "Insert Name.";

	}

	// Email
	if ($("#email").val().trim() == "") {
		return "Insert Email";

	}

	// Phone No
	if ($("#phoneNo").val().trim() == "") {
		return "Insert Phone No.";

	}

	// is numerical value
	var phnNo = $("#phoneNo").val().trim();
	if (!$.isNumeric(phnNo)) {
		return "Insert a numerical value for Phone Number.";

	}

	// Gender
	if ($("#gender").val().trim() == "") {
		return "Insert Gender.";

	}
	// Symptoms

	if ($("#symptoms").val().trim() == "") {
		return "Insert Symptom.";

	}

	// Date
	if ($("#date").val().trim() == "") {
		return "Insert date.";

	}

	// Time
	if ($("#time").val().trim() == "") {
		return "Insert Time.";

	}
	// Appointment Type
	if ($("#appointmentType").val().trim() == "") {
		return "Insert Appointment Type.";

	}
	// Hospital Name
	if ($("#hospital").val().trim() == "") {
		return "Insert Hospital Name.";

	}

	return true;

}

