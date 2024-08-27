package servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import beans.Doc;
import beans.User;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import services.DocService;
import services.FileHandlerService;
import services.UserService;

/**
 * Servlet implementation class DocServlet
 */
@WebServlet("/doc/*")
@MultipartConfig
public class DocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DocService docService;
	private FileHandlerService fileHandlerService;
	
	@Override
	public void init() throws ServletException {
		
		super.init();
		this.docService = (DocService) getServletContext().getAttribute("docService");
		this.fileHandlerService = (FileHandlerService) getServletContext().getAttribute("fileHandlerService");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		HttpSession session = request.getSession(false);
		String path = requestUri.substring((contextPath + "/doc").length());
		
		try {
			
			int userId = 4;
//			if(session != null) {
//				User user = (User) session.getAttribute("user");
//				if(user != null) {
//					userId = user.getId();
//				}
//			}
			
			switch(path) {
				case "/form":
					int docId = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : -1;
					if(docId != -1) {
						Doc doc = docService.getDoc(docId);
						request.setAttribute("doc", doc);
					}
					request.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(request, response);
					break;
				case "/view":
					int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : -1;
					Doc doc = docService.getDoc(id);
					request.setAttribute("doc", doc);
					request.getRequestDispatcher("/WEB-INF/view/doc.jsp").forward(request, response);
					break;
				case "/all":
					List<Doc> docs = docService.getAllDocs(userId);
					request.setAttribute("docs", docs);
					request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
					break;
				case "/delete":
					int deleteId = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : -1;
					Doc deleteDoc = docService.getDoc(deleteId);
					fileHandlerService.deleteFile(request, deleteDoc.getUrl());
					docService.deleteDoc(deleteId);
					response.sendRedirect(contextPath + "/doc/all");
					break;
			}
			
		} catch(Exception e) {
			System.out.println(e);
			
			if(e.getMessage().equals("DocNotFound")) {
				
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/WEB-INF/view/doc.jsp").forward(request, response);
				
			} else {
				
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
				
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		User user = null;
//		HttpSession session = request.getSession(false);
//		if(session != null) {
//			user = (User) session.getAttribute("user");
//		}
		int userId = 4;
		String contextPath = request.getContextPath();
		String name = request.getParameter("name");
		String note = request.getParameter("note");
		Doc doc = new Doc();
		
		try {
			
			if(name == null || name.isBlank()) {
				request.setAttribute("error", "Name can't be empty.");
				request.setAttribute("doc", doc);
				request.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(request, response);
				return;
			}
			
			// File upload
			String url = fileHandlerService.uploadFile(request);
				
			doc.setName(name.trim());
			doc.setNote(note.trim());
			doc.setUrl(url);
			doc.setUserId(userId);
			docService.addDoc(doc);
			response.sendRedirect(contextPath + "/doc/all");
			
		} catch(Exception e) {
			System.out.println(e);
			
			if(e.getMessage().equals("DocNotInserted")) {
				
				request.setAttribute("error", "Unable to add doc. Please try again.");
				
			} else {
				
				request.setAttribute("error", e.getMessage());
				
			}
			
			request.setAttribute("doc", doc);
			request.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(request, response);

		}
	}

//	@Override
//	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		super.doDelete(req, resp);
//	}
//
//	@Override
//	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		super.doPut(req, resp);
//	}
//	
	

}
