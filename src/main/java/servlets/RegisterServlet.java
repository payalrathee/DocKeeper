package servlets;

import java.io.IOException;
import java.util.HashMap;

import beans.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.UserService;
import services.ValidateUser;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private ValidateUser validateUser;

    public RegisterServlet() {
        
    }
    

	@Override
	public void init() throws ServletException {
		
		super.init();
		this.userService = (UserService) getServletContext().getAttribute("userService");
        this.validateUser = (ValidateUser) getServletContext().getAttribute("validateUser");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null) {
			request.getRequestDispatcher("home.jsp").forward(request, response);
			return;
		}
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = new User(username, password, email);
		
		try {
			
			HashMap<String, String> valError = validateUser.validateUser(user);
			System.out.println(valError);
			if(!valError.isEmpty()) {
				request.setAttribute("valError", valError);
				request.setAttribute("user", user);
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
			
			user = userService.addUser(user);
			
			// Auto login
			session.setAttribute("user", user);
			response.sendRedirect("home.jsp");
			
		} catch(Exception e) {
			System.out.println(e);
			
			if(e.getMessage().equals("UserNotInserted")) {
				
				request.setAttribute("error", "Unable to register. Please try again.");
				
			} else if(e.getMessage().equals("UsernameAlreadyExists")) {
				
				request.setAttribute("error", "Username already exists");
				
			} else if(e.getMessage().equals("EmailAlreadyExists")) {
				
				request.setAttribute("error", "Email is already registered");
				
			} else {
				
				request.setAttribute("error", e.getMessage());
				
			}
			
			request.setAttribute("user", user);
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}
}
