package iae.s20;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Stack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PreviousItems
 */
@WebServlet("/PreviousItems")
public class PreviousItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreviousItems() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
		Stack<Integer> prevItemsViewed = (Stack<Integer>) session.getAttribute("prevItemsViewed");
        if(prevItemsViewed == null) {
        	Stack<Integer> newPrevItemsViewed = new Stack<Integer>();
        	session.setAttribute("prevItemsViewed", newPrevItemsViewed);
        	prevItemsViewed = newPrevItemsViewed;
        }
           
           response.setContentType("text/html");
           response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
           PrintWriter out = response.getWriter();
		out.println("<br><br><h1>Previously Viewed Items</h1>");
        out.println("<table width='100% cellspacing=20'>");
        out.println("<tbody><tr>");
        int prevItemsRendered = 0;
        try(java.sql.Connection connection = DatabaseConnection.connect()) {
        for(Integer id : prevItemsViewed) {
     	   if(prevItemsRendered < 5) {
         	   Statement st = connection.createStatement();
         	   ResultSet rs = st.executeQuery("select * from products where id=" + Integer.toString(id) + ";");
         	   while(rs.next()) {
         		   out.println("<td class='product'>");
         		   String idStr = rs.getString("id");   	 
	       	         String name = rs.getString("name");    	
	       	         String price = rs.getString("price");    	         
	       	         String thumbnail = rs.getString("thumbnail");
	       	         String category = rs.getString("category");
	       	         thumbnail = "assets/" + thumbnail;
	       	         
	       	         out.println("<img src='" + thumbnail + "'/>");
	       	         out.println("<h4>" + category + "</h4>");
	       	         out.println("<h2>" + name + "</h2>");
	       	         out.println("<h3>" + price + "</h3>");
	  	       	    out.println("<form  action=\"DetailServlet\" method=\"GET\">");
                 out.println("<input type=hidden id=id name=id value="+ idStr +">");
                 out.println("<input type=submit>");               
                 out.println("</form>"); 
                 out.println("</td>");
         	   }
         	   prevItemsRendered += 1;
     	   }
        }
        out.println("</tr></tbody></table>");
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
