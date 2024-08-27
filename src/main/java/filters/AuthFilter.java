//package filters;
//
//import java.io.IOException;
//
//import beans.User;
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.FilterConfig;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.annotation.MultipartConfig;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
///**
// * Servlet Filter implementation class StaticResourceFilter
// */
//
//@MultipartConfig
//@WebFilter("/*")
//public class AuthFilter implements Filter {
//
//	/**
//	 * @see Filter#destroy()
//	 */
//	public void destroy() {
//		// TODO Auto-generated method stub
//	}
//
//	/**
//	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
//	 */
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		System.out.println(request.getParameter("name"));
//		HttpServletRequest httpRequest = (HttpServletRequest)request;
//		HttpServletResponse httpResponse = (HttpServletResponse)response;
//		String requestUri = httpRequest.getRequestURI();
//		String contextPath = httpRequest.getContextPath();
//		String servletPath = httpRequest.getServletPath();
//		String path = requestUri.substring(contextPath.length());
//		HttpSession session = httpRequest.getSession(false);
//
//		if (path.startsWith("/resources") || path.endsWith(".jsp")) {
//		    chain.doFilter(request, response); // Goes to default servlet.
//		} else {
//			
//			
//			if(		requestUri.equals(contextPath) ||
//					requestUri.equals(contextPath + "/") ||
//					requestUri.endsWith("/login") ||
//					requestUri.endsWith("/loginHandler") ||
//					requestUri.endsWith("/register") ||
//					requestUri.endsWith("/registerHandler")) {
//				
//				request.getRequestDispatcher("/app" + path).forward(request, response);
//				return;
//			}
//			
//			if(session != null) {
//				User user = (User) session.getAttribute("user");
//				if(user == null) {
//					httpResponse.sendRedirect(contextPath + "/login");
//					return;
//				}
//			}
//			request.getRequestDispatcher("/app" + path).forward(request, response);
//
//		}
//	}
//
//	/**
//	 * @see Filter#init(FilterConfig)
//	 */
//	public void init(FilterConfig fConfig) throws ServletException {
//		// TODO Auto-generated method stub
//	}
//
//}
