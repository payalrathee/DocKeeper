package services;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class FileHandlerService {
	
	private static final String UPLOAD_DIR = "uploads";
	
	public String uploadFile(HttpServletRequest request) throws IOException, ServletException {
		String applicationPath = request.getServletContext().getRealPath("");
		String uploadDirPath = applicationPath + File.separator + UPLOAD_DIR;
		String fileUrl = "";
		
		File uploadDir = new File(uploadDirPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		for(Part part : request.getParts()) {
			String filename = getFileName(part);
			
			if(filename != null && !filename.isEmpty()) {
				String filepath = uploadDirPath + File.separator + filename;
				part.write(filepath);
				fileUrl = UPLOAD_DIR + "/" + filename;
			}
		}
		
		return fileUrl;
	}
	
	public boolean deleteFile(HttpServletRequest request, String url) {
		String path = request.getServletContext().getRealPath("/uploads/") + url.substring("uploads/".length());
		File file = new File(path);
		
		if(file.exists() && file.delete()) {
			return true;
		}
		return false;
	}
	
	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	        	String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	            return timestamp + "_" + content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}

}
