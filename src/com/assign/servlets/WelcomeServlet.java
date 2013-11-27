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
 * Servlet implementation class WelcomeServlet
 */

@WebServlet("/welcome.html")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

		Cookie userCookie = IndexServlet.detectCookies(
				IndexServlet.COOKIE_NAME, request.getCookies());

		if (userCookie == null) {

			String uname = request.getParameter(IndexServlet.PARAM_1);
			String pword = request.getParameter(IndexServlet.PARAM_2);

			if (uname == null && pword == null) {
				// direct access to welcome.html without a cookie
				// redirects to index.html
				response.sendRedirect("index.html");
				return; //
			}

			// continues to this blocks if uname and pword are not null
			if (validateUser(uname, pword)) {
				Cookie cookie = new Cookie(IndexServlet.COOKIE_NAME, uname);
				response.addCookie(cookie);

				String resp = "<body>" + "<h1>WELCOME!</h1>" + "<h2>" + uname
						+ "</h2>";

				showResponse(resp, response);
			} else {
				showResponse("<h1>Wrong username and password</h1>", response);
			}

		} else {

			String resp = "<body>" + "<h1>WELCOME!</h1>" + "<h2>"
					+ userCookie.getValue() + "</h2>";

			showResponse(resp, response);
		}
	}

	private boolean validateUser(String uname, String pword) {
		if (uname.equals("jordan@yahoo.com") && pword.equals("pass1234")) {
			return true;
		}

		return false;
	}

	private void showResponse(String resp, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();

		out.print("<html>");

		out.print("<head>");
		out.print("<title>Welcome</title>");
		out.print("<head>");

		out.print(resp);

		out.print("</body>");
		out.print("</html>");
	}

}
