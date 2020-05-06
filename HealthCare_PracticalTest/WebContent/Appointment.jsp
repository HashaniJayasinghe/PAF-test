<%@ page import="model.Appointment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointment Management</title>
<link rel="stylesheet" href="Views/css/bootstrap.min.css">
<Script src="Components/jquery-3.5.1.min.js"></Script>
<script src="Components/Appointment.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-8">
				<h1>Appointment Management</h1>

				<form id="formAppointment" name="formAppointment"
					action="Appointment.jsp" method="post">

					Full Name: <input id="name" name="name" type="text"
						class="form-control form-control-sm"> <br> 
					Email: <input
						id="email" name="email" type="email"
						class="form-control form-control-sm"> <br> 
					Phone NO:
					<input id="phnNo" name="phnNo" type="text"
						class="form-control form-control-sm"> <br> 
					Gender: <input
						id="gender" name="gender" type="text"
						class="form-control form-control-sm"> <br> 
					Symptoms:
					<input id="symptoms" name="symptoms" type="text"
						class="form-control form-control-sm"> <br> 
					Date: <input
						id="date" name="date" type="date"
						class="form-control form-control-sm"> <br> 
					Time: <input
						id="time" name="time" type="time"
						class="form-control form-control-sm"> <br>
					Appointment Type: <input id="appType" name="appType" type="text"
						class="form-control form-control-sm"> <br> 
					Hospital:
					<input id="hospital" name="hospital" type="text"
						class="form-control form-control-sm"> <br> 
						
						<input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> 
						<input type="hidden" id="hidAppIDSave" name="hidAppIDSave" value="">


				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divAppGrid">
					<%
						Appointment appObj = new Appointment();
						out.print(appObj.readAppointments());
					%>
				</div>
			</div>


		</div>


	</div>

</body>
</html>