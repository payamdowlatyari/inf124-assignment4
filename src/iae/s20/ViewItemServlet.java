package iae.s20;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//@WebServlet("/ViewItemServlet")
public class ViewItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewItemServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		  // if not existing session, create a new one
        HttpSession session = request.getSession(true);
        // get the time session created
        Date createTime = new Date(session.getCreationTime());
        // get last time of visit
        Date lastAccessTime = new Date(session.getLastAccessedTime());
         
        // date output  
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    
        String title = "Servlet Session";
        Integer visitCount = new Integer(0);
        String visitCountKey = new String("visitCount");
        String userIDKey = new String("userID");
        String userID = new String("meowmeow");
        if(session.getAttribute(visitCountKey) == null) {
            session.setAttribute(visitCountKey, new Integer(0));
        }

        // check if a new visitor
        if (session.isNew()){
            title = "Servlet Session";
             session.setAttribute(userIDKey, userID);
        } else {
             visitCount = (Integer)session.getAttribute(visitCountKey);
             visitCount = visitCount + 1;
             userID = (String)session.getAttribute(userIDKey);
        }
        session.setAttribute(visitCountKey,  visitCount);
    
        // response content
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Integer[] pidPast = new Integer[5];
        
        // read & write last 5 product info
        String pidNow = request.getParameter("pid");
        Object sessionPid = session.getAttribute("visitedItems");
        if(sessionPid == null) // create session attributes for the first time
        {
            pidPast = new Integer[]{-1,-1,-1,-1, Integer.parseInt(pidNow)};
            session.setAttribute("visitedItems",pidPast);
            System.out.print("gay");
        }
        else
        {
            sessionPid = (Object[])sessionPid;
            pidPast = (Integer[])sessionPid;
            for(int i=4;i>=0;--i)
            {
                pidPast[i]= i-1;
            }
            session.setAttribute("visitedItems",pidPast);
            System.out.print("gay2");
        }
      
        String docType = "<!DOCTYPE html>\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                 "<h2 align=\"center\">Session Info</h2>\n" +
                "<table border=\"1\" align=\"center\">\n" +
                "<tr bgcolor=\"#949494\">\n" +
                "  <th>Session Info</th><th>Value</th></tr>\n" +
                "<tr>\n" +
                "  <td>id</td>\n" +
                "  <td>" + session.getId() + "</td></tr>\n" +
                "<tr>\n" +
                "  <td>Time Created</td>\n" +
                "  <td>" +  df.format(createTime) + 
                "  </td></tr>\n" +
                "<tr>\n" +
                "  <td>Last Visited</td>\n" +
                "  <td>" + df.format(lastAccessTime) + 
                "  </td></tr>\n" +
                "<tr>\n" +
                "  <td>User ID</td>\n" +
                "  <td>" + userID + 
                "  </td></tr>\n" +
                "<tr>\n" +
                "  <td>Visit Count：</td>\n" +
                "  <td>" + visitCount + "</td></tr>\n" +
                // last 5 items
                "<tr>\n" +
                "  <td>Visited Item IDs (pid)：</td>\n" +
                "  <td>" + pidPast[0] + "<br>" + 
                        + pidPast[1] + "<br>" + 
                        + pidPast[2] + "<br>" + 
                        + pidPast[3] + "<br>" + 
                        + pidPast[4] + "<br>" + 
                        
                "</td></tr>\n" +
                "</table>\n" +
                "</body></html>"); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
