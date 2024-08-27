package listeners;

import dao.DocDao;
import dao.UserDao;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import services.DocService;
import services.FileHandlerService;
import services.UserService;
import services.ValidateUser;
import utilities.ConnectionManager;

/**
 * Application Lifecycle Listener implementation class BeanInitializer
 *
 */
@WebListener
public class BeanInitializer implements ServletContextListener {

	private UserDao userDao;
	private DocDao docDao;
	private UserService userService;
	private DocService docService;
	private ValidateUser validateUser;
	private FileHandlerService fileHandlerService;
	
    public BeanInitializer() {
    	
    }

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ConnectionManager.closeConnection();
		userDao = null;
		docDao = null;
		userService = null;
		docService = null;
		validateUser = null;
		fileHandlerService = null;
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext ctx = sce.getServletContext();
		
		// Repositories
		userDao = new UserDao();
		docDao = new DocDao();
		
		// Services
		userService = new UserService(userDao);
		docService = new DocService(docDao);
		validateUser = new ValidateUser();
		fileHandlerService = new FileHandlerService();
		
		ctx.setAttribute("userService", userService);
		ctx.setAttribute("docService", docService);
		ctx.setAttribute("validateUser", validateUser);
		ctx.setAttribute("fileHandlerService", fileHandlerService);
	}
	
}
