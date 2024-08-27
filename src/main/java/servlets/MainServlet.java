package servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/app/*")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = requestUri.substring((contextPath + "/app").length());

		switch(path) {
			case "/home":
				System.out.println("Log: Home page requested");
				request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
				break;
			case "/login":
				System.out.println("Log: Login page requested");
				request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
				break;
			case "/register":
				System.out.println("Log: Register page requested");
				request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
				break;
			default:
				System.out.println("Log: Default page");
				request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
				
		}
		
	}


}
