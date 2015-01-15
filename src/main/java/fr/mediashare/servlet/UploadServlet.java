package fr.mediashare.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@WebServlet(name = "uploadServlet", urlPatterns = { "/upload" }, initParams = { @WebInitParam(name = "simpleParam", value = "paramValue") })
/*@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
				maxFileSize=1024*1024*10,      // 10MB
				maxRequestSize=1024*1024*50)   // 50MB*/
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
 
        if (isMultipart) {
        	// Create a factory for disk-based file items
        	FileItemFactory factory = new DiskFileItemFactory();

        	// Create a new file upload handler
        	ServletFileUpload upload = new ServletFileUpload(factory);
 
            try {
            	// Parse the request
            	List /* FileItem */ items = upload.parseRequest(request);
                Iterator iterator = items.iterator();
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();
                    if (!item.isFormField()) {
                        String fileName = item.getName();	 
                        String root = getServletContext().getRealPath("/");
                        File path = new File(root + "/files");
                        if (!path.exists()) {
                            boolean status = path.mkdirs();
                        }
 
                        File uploadedFile = new File(path + "/" + fileName);
                        System.out.println(uploadedFile.getAbsolutePath());
                        item.write(uploadedFile);
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	
	
	
	/*
    private static final String SAVE_DIR = "files";
     
   
    protected void doPost(HttpServletRequest request,
          
    	HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	// gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;
         
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
         
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            part.write(savePath + File.separator + fileName);
        }
 
        request.setAttribute("message", "Upload has been done successfully!");
        getServletContext().getRequestDispatcher("/message.jsp").forward(
                request, response);
    }*/
 
    /**
     * Extracts file name from HTTP header content-disposition
     */
   /* private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println(contentDisp);
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
            	System.out.println(s);
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }*/

}