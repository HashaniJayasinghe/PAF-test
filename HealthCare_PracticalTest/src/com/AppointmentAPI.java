package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Appointment;

/**
 * Servlet implementation class AppointmentAPI
 */
@WebServlet("/AppointmentAPI")
public class AppointmentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	Appointment appObj = new Appointment();

	public AppointmentAPI() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String output = appObj.insertAppointment(request.getParameter("name"), request.getParameter("email"),
				request.getParameter("phoneNo"), request.getParameter("gender"), request.getParameter("symptoms"),
				request.getParameter("date"), request.getParameter("time"), request.getParameter("appointmentType"),
				request.getParameter("hospital"));

		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */

	private Map<String, String> getParasMap(HttpServletRequest request) {

		Map<String, String> map = new HashMap<String, String>();

		try {

			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");

			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();

			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {

		}
		return map;
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> param = getParasMap(request);

		String output = appObj.updateAppointment(param.get("hidAppIDSave").toString(), param.get("name").toString(),
				param.get("email").toString(), param.get("phoneNo").toString(), param.get("gender").toString(),
				param.get("symptoms").toString(), param.get("date").toString(), param.get("time").toString(),
				param.get("appointmentType").toString(), param.get("hospital").toString());

		response.getWriter().write(output);

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> param = getParasMap(request);

		String output = appObj.deleteAppointment(param.get("appointmentId").toString());

		response.getWriter().write(output);
	}

}
