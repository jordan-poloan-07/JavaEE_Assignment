package com.assign.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index.html")
public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final String COOKIE_NAME = "username";
	public static final String PARAM_1 = "uname";
	public static final String PARAM_2 = "pword";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Cookie userCookie = detectCookies(COOKIE_NAME, request.getCookies());

		// do display depending on cookie value;
		if (userCookie == null) {
			showLogin(response);
		} else {
			response.sendRedirect("welcome.html");
		}

	}

	public static Cookie detectCookies(String cookieName, Cookie[] cookies) {

		// iterate through cookies

		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(COOKIE_NAME)) {
					return c;
				}
			}
		}

		return null;
	}

	private void showLogin(HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();

		out.print("<html>");

		out.print("<head>");
		out.print("<title>Login</title>");
		out.print("<head>");

		out.print("<body>");
		out.print("<h1>LOGIN</h1>");
		out.print("<form action='welcome.html' method='POST'>");
		out.print("Username: <input name='uname' type='text' ><br>");
		out.print("Password: <input name='pword' type='password' ><br>");
		out.print("<input value='Submit' type='submit' ><br>");
		out.print("</form>");

		out.print("</body>");
		out.print("</html>");
	}

}
