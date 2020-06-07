package iae.s20;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.glassfish.jersey.client.ClientConfig;

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
        Stack<Integer> noDups = removeDuplicates(prevItemsViewed);
           
           response.setContentType("text/html");
           response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
           PrintWriter out = response.getWriter();
		out.println("<br><br><h1>Previously Viewed Items</h1>");
        out.println("<table width='100% cellspacing=20'>");
        out.println("<tbody><tr>");
        int prevItemsRendered = 0;
        try(java.sql.Connection connection = DatabaseConnection.connect()) {
        for(Integer id : noDups) {
     	   if(prevItemsRendered < 5) {
     		   	ClientConfig config = new ClientConfig();
	          	Product product = new Product();
	          	Client client = ClientBuilder.newClient(config);
	          	WebTarget target = client.target(UriBuilder.fromUri("http://localhost:6060/PA3").build());
	          	try {
	          	String jsonResponse = target.path("rest").path("products").path(Integer.toString(id)).request(). //send a request
	                      accept(MediaType.APPLICATION_JSON). //specify the media type of the response
	                      get(String.class);
	          	System.out.println(jsonResponse);
	          	ObjectMapper objectMapper = new ObjectMapper();
	          	product = objectMapper.readValue(jsonResponse, new TypeReference<Product>() {});
	          	}
	          	catch(Exception e) {
	          		e.printStackTrace();
	          	}

         	   if(product != null) {
         		   	String idStr = Integer.toString(product.getId());
	    	    	 
	    	         String name = product.getName();
	    	         String summary = product.getSummary();
	    	         String thumbnail = product.getThumbnail();
	    	         String category = product.getCategory();
	    	         String detail = product.getDetail();
	    	         String price = Double.toString(product.getPrice());
	    	         thumbnail = "assets/" + thumbnail; 
         		   
         		   out.println("<td class='product'>");
//         		   String idStr = rs.getString("id");   	 
//	       	         String name = rs.getString("name");    	
//	       	         String price = rs.getString("price");    	         
//	       	         String thumbnail = rs.getString("thumbnail");
//	       	         String category = rs.getString("category");
	       	         
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
	
	private Stack<Integer> removeDuplicates(Stack<Integer> original){
		Set<Integer> usedDigits = new HashSet<Integer>();
		Stack<Integer> noDups = new Stack<Integer>();
		
		for(Integer i : original) {
			if(!usedDigits.contains(i)) {
				noDups.push(i);
			}
			usedDigits.add(i);
		}
		
		return noDups;
	}

}
