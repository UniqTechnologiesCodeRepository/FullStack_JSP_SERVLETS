package controlLayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MyFirstServlet")
public class MyFirstServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (PrintWriter out = response.getWriter()) {

			String user = request.getParameter("uname");
			String pass = request.getParameter("pwd");

			if (user.equals("uniq123") && pass.equals("admin123")) {

				System.out.println("welome Admin");
				out.println("welome Admin");
			} else {

				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fullstack", "root", "admin");
				Statement stmt = con.createStatement();

				ResultSet resultSet = stmt.executeQuery("select * from register");

				int flag = 0;
				while (resultSet.next()) {

					String userFromDb = resultSet.getString("username");
					String passFromDb = resultSet.getString("password");

					if (user.equals(userFromDb) && pass.equals(passFromDb)) {
						System.out.println("welome User");
						out.println("welome User");
						flag = 1;
					}

				}
				
				if(flag ==0) {
					out.println("Sorry");
				}
			}

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

}
