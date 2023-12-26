package controlLayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddNewUser")
public class AddNewUser extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (PrintWriter out = response.getWriter()) {

			String user = request.getParameter("u");
			String pass = request.getParameter("p");
			String cpass = request.getParameter("cp");

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fullstack", "root", "admin");
			Statement stmt = con.createStatement();

			int resp = stmt.executeUpdate("insert into register(username, password, cpassword) values('" + user + "','"
					+ pass + "','" + cpass + "') ");

			if (resp == 1) {
				out.println("successfully inserted ");
			}

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
