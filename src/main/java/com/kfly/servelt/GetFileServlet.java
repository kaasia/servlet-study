package com.kfly.servelt;

import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kfly on 2016/4/15.
 */
public class GetFileServlet extends HttpServlet {
    private String filePath;
    public void init(){
        filePath = getServletContext().getInitParameter("file-upload");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String parm = request.getParameter("file");
        if(parm==null) {
            String theUrl = request.getRequestURL().toString();
            List<String> files = new ArrayList<String>();
            File f = new File(filePath);
            File[] fs = f.listFiles();
            for (File q : fs) {
                if (q.isFile()) {
                    files.add(q.getName());
                }
            }

            for (String s : files) {
                out.println("<a href='" + theUrl + "?file=" + s + "'><b>" + s + "<b></a>" + "</br>");
            }
        }else{
            String theFileName = filePath+"\\"+parm;
            File theF = new File(theFileName);

            if(theF.exists()){
                out.println("<b>size:"+theF.length()+"</b></br>");
                String content = FileUtils.readFileToString(theF);
                out.println("<textarea>"+content+"</textarea>");
            }
        }


    }
}
