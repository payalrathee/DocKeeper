package servlets;

import java.io.IOException;
import java.util.HashMap;

import beans.User;
import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.UserService;
import services.ValidateUser;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/app/loginHandler")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService;

    public LoginServlet() {

    }
    
    @Override
	public void init() throws ServletException {
		
		super.init();
		this.userService = (UserService) getServletContext().getAttribute("userService");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Log: Request for login");
		String contextPath = request.getContextPath();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user") != null) {
			response.sendRedirect(contextPath + "/home");
			return;
		}
		
		String login = request.getParameter("login");
		String pass = request.getParameter("password");
		
		try {
			
			HashMap<String, String> valError = new HashMap<>();
			if(login.isEmpty()) {
				valError.put("username", "Username is required");
			}
			
			if(pass.isEmpty()) {
				valError.put("password", "Password is required");
			}
			
			if(!valError.isEmpty()) {
				request.setAttribute("valError", valError);
				request.setAttribute("login", login);
				request.setAttribute("password", pass);
				
				request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
				return;
			}
			
			login = login.trim();
			pass = pass.trim();
			User user = userService.validate(login, pass);
			
			session.setAttribute("user", user);
			
			// Redirect to home page
			response.sendRedirect(contextPath + "/home");
			
		} catch(Exception e) {
			if(e.getMessage().equals("UserNotFound")) {
				request.setAttribute("error", "User doesn't exist.");
			} else if(e.getMessage().equals("InvalidPassword")) {
				request.setAttribute("error", "Invalid password!");
			} else {
				request.setAttribute("error", e.getMessage());
			}
			
			// Redirect to login page again
			request.setAttribute("login", login);
			request.setAttribute("password", pass);
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
		}
		
	}

}
