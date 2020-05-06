package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Appointment {

	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthCare", "root", "");

			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;

	}

	public String readAppointments() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading";

			}

			output = "<table border='1'><th>Name</th><th>Email</th>"
					+ "<th>PhoneNo</th><th>Gender</th><th>Symptoms</th><th>Date</th><th>Time</th>"
					+ "<th>Type</th><th>Hospital</th><th>Update</th><th>Remove</th>";

			String query = "select * from appointments";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String appointmentId = Integer.toString(rs.getInt("appointmentID"));
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phoneNo = Integer.toString(rs.getInt("phoneNo"));
				String gender = rs.getString("gender");
				String symptoms = rs.getString("symptoms");
				String date = rs.getString("date");
				String time = rs.getString("time");
				String appointmentType = rs.getString("appointmentType");
				String hospital = rs.getString("hospital");

				output += "<tr><td><input id='hidAppIDUpdate' name='hidAppIDUpdate' type='hidden' value='"
						+ appointmentId + "'>" + name + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + phoneNo + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + symptoms + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + time + "</td>";
				output += "<td>" + appointmentType + "</td>";
				output += "<td>" + hospital + "</td>";

				output += "<td><input name = 'btnUpdate' type = 'button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name = 'btnRemove' type = 'button' value='Remove' class='btnRemove btn btn-danger' data-appointmentId='"
						+ appointmentId + "'>" + "</td></tr>";

			}

			con.close();

			output += "</table>";
		}

		catch (Exception e) {
			output = "Error while reading the Appointments";
			System.err.print(e.getMessage());
		}
		return output;

	}

	public String insertAppointment(String Name, String Email, String PhoneNo, String Gender, String Symptoms,
			String Date, String Time, String AppointmentType, String Hospital) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			String query = " insert into appointments (`AppointmentID`,`Name`,`Email`,`PhoneNo`,`Gender`,`Symptoms`,`Date`,`Time`,`AppointmentType`,`Hospital`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, 0);
			ps.setString(2, Name);
			ps.setString(3, Email);
			ps.setString(4, PhoneNo);
			ps.setString(5, Gender);
			ps.setString(6, Symptoms);
			ps.setString(7, Date);
			ps.setString(8, Time);
			ps.setString(9, AppointmentType);
			ps.setString(10, Hospital);

			ps.execute();
			con.close();

			String newAppointment = readAppointments();
			output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while Inserting Appointment.\"}";
			System.out.println(e.getMessage());
		}
		return output;
	}

	public String updateAppointment(String AppointmentID, String Name, String Email, String PhoneNo, String Gender,
			String Symptoms, String Date, String Time, String AppointmentType, String Hospital) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			String query = "UPDATE appointments SET Name=?,Email=?,PhoneNo=?, Gender=?, Symptoms=?, Date=?, Time=?, AppointmentType=?, Hospital=? WHERE AppointmentID=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, Name);
			ps.setString(2, Email);
			ps.setInt(3, Integer.parseInt(PhoneNo));
			ps.setString(4, Gender);
			ps.setString(5, Symptoms);
			ps.setString(6, Date);
			ps.setString(7, Time);
			ps.setString(8, AppointmentType);
			ps.setString(9, Hospital);
			ps.setInt(10, Integer.parseInt(AppointmentID));

			ps.executeUpdate();
			con.close();

			String newAppointment = readAppointments();
			output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}";

		} catch (Exception e) {

			output = "{\"status\":\"error\", \"data\": \"Error while Updating Appointment Details.\"}";
			System.err.println(e.getMessage());
		}
		return output;

	}

	public String deleteAppointment(String appointmentID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			String query = "delete from appointments where AppointmentID=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, appointmentID);

			ps.execute();
			con.close();

			String newAppointment = readAppointments();
			output = "{\"status\":\"success\", \"data\": \"" + newAppointment + "\"}";

		} catch (Exception e) {

			output = "{\"status\":\"error\", \"data\": \"Error while Deleting Appointment.\"}";
			System.err.println(e.getMessage());

		}
		return output;

	}

}
