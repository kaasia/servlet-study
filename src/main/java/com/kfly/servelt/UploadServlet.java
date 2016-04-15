package com.kfly.servelt;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Kfly on 2016/4/13.
 */
public class UploadServlet extends HttpServlet {
    private String filepath;
    private boolean isMultipart;
    private int maxFileSize = 50 *1024;
    private int maxMenSize = 4 * 1024;
    private File file;
    public void init(){
        filepath = getServletContext().getInitParameter("file-upload");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if(!isMultipart){
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>No file uploaded</p>");
            out.println("</body>");
            out.println("</html>");
            return;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(maxMenSize);
        factory.setRepository(new File("D:\\IdeaProjects\\servlet-study\\temp"));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(maxFileSize);
        try {
            List fileItems = upload.parseRequest(request);
            Iterator i = fileItems.iterator();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            while (i.hasNext()){
                FileItem fi =(FileItem)i.next();
                if(!fi.isFormField()){
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();
                    if(fileName.lastIndexOf("\\")>=0){
                        file = new File(filepath+"\\"+fileName.substring(fileName.lastIndexOf("\\")));
                    }else{
                        file = new File(filepath+"\\"+fileName.substring(fileName.lastIndexOf("\\")+1));
                    }
                    fi.write(file);
                    System.out.println(filepath);
                    System.out.println(file.toString());
                    out.println("uploaded Filename:"+fileName+"<br>");

                }
            }
            out.println("</body>");
            out.println("</html");
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new ServletException("Get method used with"+getClass().getName()+":Post method required");
    }
}
