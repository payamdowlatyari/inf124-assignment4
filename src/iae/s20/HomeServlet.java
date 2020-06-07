package iae.s20;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class HomeServlet
 */
//@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    @SuppressWarnings("unchecked")
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	
    	ClientConfig config = new ClientConfig();
    	List<Product> products = new ArrayList<Product>();
    	Client client = ClientBuilder.newClient(config);
    	WebTarget target = client.target(UriBuilder.fromUri("http://localhost:6060/PA3").build());
    	try {
    	String jsonResponse = target.path("rest").path("products").request(). //send a request
                accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                get(String.class);
    	System.out.println(jsonResponse);
    	ObjectMapper objectMapper = new ObjectMapper();
    	products = objectMapper.readValue(jsonResponse, new TypeReference<List<Product>>() {});
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
	   	
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Stack<Integer> prevItemsViewed = (Stack<Integer>) session.getAttribute("prevItemsViewed");
        if(prevItemsViewed == null) {
        	Stack<Integer> newPrevItemsViewed = new Stack<Integer>();
        	session.setAttribute("prevItemsViewed", newPrevItemsViewed);
        	prevItemsViewed = newPrevItemsViewed;
        }
           
           response.setContentType("text/html");
           response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
           PrintWriter out = response.getWriter();
           
               out.println("<!DOCTYPE html>");
               out.println("<html>");
               out.println("<head>");
               out.println("<title>Home</title>");  
               out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">");
               out.println(" <link href=\"style.css\" rel=\"stylesheet\">");
               out.println("</head>");
               out.println("<body>");          
               out.println("<div class=\"container\">");         
               out.println("<div class=\"header\">");
               out.println("<div class=\"content\"> ");
               out.println("<div class=\"logo\"> ");
               out.println("<a href=\"HomeServlet\"> ");
               out.println("<h1>Sports Store</h1>");
               out.println("</a></div></div>");
               out.println("<div class=\"navbar\"> ");
               out.println("<ul class=\"content\"> ");
               out.println("<li><a href=\"index.jsp\" class=\"active\">Home</a></li> ");
               out.println("<li><a href=\"ProductServlet\">Products</a></li> ");
               out.println("<li><a href=\"AboutServlet\">About Us</a></li> ");
               out.println("<li><a href=\"ContactServlet\">Contact Us</a></li>");
               out.println("<li><a href=\"CartServlet\">Shopping Cart</a></li>");
               out.println("</ul></div></div>");
               int prevItemsRendered = 0;
               try(Connection connection = DatabaseConnection.connect()) { 
	               out.println(" <div class=\"main\">");
	               out.println(" <div class=\"content\">");   
//	               out.println("<br><br><h1>Previously Viewed Items</h1>");
//	               out.println("<table width='100% cellspacing=20'>");
//	               out.println("<tbody><tr>");
//	               for(Integer id : prevItemsViewed) {
//	            	   if(prevItemsRendered < 5) {
//		            	   Statement st = connection.createStatement();
//		            	   ResultSet rs = st.executeQuery("select * from products where id=" + Integer.toString(id) + ";");
//		            	   while(rs.next()) {
//		            		   out.println("<td class='product'>");
//		            		   String idStr = rs.getString("id");   	 
//		  	       	         String name = rs.getString("name");    	
//		  	       	         String price = rs.getString("price");    	         
//		  	       	         String thumbnail = rs.getString("thumbnail");
//		  	       	         String category = rs.getString("category");
//		  	       	         thumbnail = "assets/" + thumbnail;
//		  	       	         
//		  	       	         out.println("<img src='" + thumbnail + "'/>");
//		  	       	         out.println("<h4>" + category + "</h4>");
//		  	       	         out.println("<h2>" + name + "</h2>");
//		  	       	         out.println("<h3>" + price + "</h3>");
//			  	       	    out.println("<form  action=\"DetailServlet\" method=\"GET\">");
//		                    out.println("<input type=hidden id=id name=id value="+ idStr +">");
//		                    out.println("<input type=submit>");               
//		                    out.println("</form>"); 
//		                    out.println("</td>");
//		            	   }
//		            	   prevItemsRendered += 1;
//	            	   }
//	               }
//	               out.println("</tr></tbody></table>");
	               request.getRequestDispatcher("PreviousItems").include(request, response);
	               out.println(" <br><br><h1>New Product</h1>");

		            String id = ""; 
		       		String name = "";
		       		String price = "";
		       		String thumbnail = "";
		       		String category = "";
       			
//                   Statement st = connection.createStatement();    	 
//                   ResultSet rs = st.executeQuery("SELECT * FROM products;");
                   
                   out.println("<table width=\"100% cellspacing=20\">");
                   out.println( "<tbody><tr>");
       	      // iterate through the java resultset
	       	      for(Product product: products)
	       	      {
	       	    	  out.println("<td class=\"product\">");
	
	       	    	 id = Integer.toString(product.getId());  	 
	    	         name = product.getName(); 	
	    	         price = Double.toString(product.getPrice());   	         
	    	         thumbnail = product.getThumbnail();
	    	         category = product.getCategory();
	    	         thumbnail = "assets/" + thumbnail;  
	       	         
	       	         
	       	         out.println("<img src=\""+thumbnail+"\"/>");
	       	         out.println("<h4>"+ category +"</h4>");
	                    out.println("<h2>"+ name +"</h2>");
	                    out.println("<h3>"+ price +"</h3>");
	
	                    out.println("<form  action=\"DetailServlet\" method=\"GET\">");
	                    out.println("<input type=hidden id=id name=id value="+ id +">");
	                    out.println("<input type=submit>");               
	                    out.println("</form>"); 
	                    out.println("</td>");
	                    
	                    if (Integer.parseInt(id) == 5) {               	 
	                   	 out.println("</tr>");                 	 
	                   	 out.println("</table>\n" + 
	                   	 		"            </div>\n" + 
	                   	 		"            <div class=\"hotdeal\">\n" + 
	                   	 		"                <div class=\"detail\">\n" + 
	                   	 		"                    <h1>Back to school deal</h1>\n" + 
	                   	 		"                    <div class=\"countdown\">00 DAYS, 00 HOURS, 00 MINS, 00 SECS</div>\n" + 
	                   	 		"                    <h2>UP TO 50% OFF</h2>\n" + 
	                   	 		"                    <a href=\"products.php\" class=\"getdeal\">Shop Now</a>\n" + 
	                   	 		"                </div>\n" + 
	                   	 		"            </div>\n" + 
	                   	 		"            <div class=\"content\">\n" + 
	                   	 		"                <h1>TOP SELLING</h1>\n" + 
	                   	 		"                <table width=\"100%\" cellspacing=\"20\"><br>");
	                   	 
	                   	out.println("<tr>");      
	                    }
	
	       	      }
       	      
       	     out.println("</tr></tbody>");      		
        		 out.println("</table>");
        		 out.println("</div>");
       		 out.println("</div>");
       		 out.println("</div>");	
       		 out.println("<br><div class=\"footer\">\n" + 
               		"    <div class=\"content\">\n" + 
               		"        <table width=\"100%\" cellspacing=\"20\">\n" + 
               		"            <tbody>\n" + 
               		"                <tr>\n" + 
               		"                    <td>\n" + 
               		"                        <h3>WHO WE ARE</h3>\n" + 
               		"                        <p> Welcome to Sports Store!</p>\n" + 
               		"                        <p>\n" + 
               		"                            We are an online business currently based in\n" + 
               		"                            Irvine, California.\n" + 
               		"                        </p>\n" + 
               		"                        <a href=\"/about.php\">Learn more... </a>\n" + 
               		"                    </td>\n" + 
               		"                    <td>\n" + 
               		"                        <h3>MAIN OFFICE</h3>\n" + 
               		"                        <p>\n" + 
               		"                            <address>\n" + 
               		"                                40234 Sunrise Blvd,\n" + 
               		"                                Irvine, California, 92811\n" + 
               		"                            </address>\n" + 
               		"                        </p>\n" + 
               		"                        <p> Tel: (949) 800 2221</p>\n" + 
               		"                        <p> Email: info@sportstore.com</p>\n" + 
               		"                        <a href=\"/contact.php\">Learn More...</a>\n" + 
               		"                    </td>\n" + 
               		"                    <td>\n" + 
               		"                        <h3>QUICK LINKS</h3>\n" + 
               		"                        <a href=\"/index.php\">First Page</a>\n" + 
               		"                        <a href=\"/products.php\">What We Offer</a>\n" + 
               		"                        <a href=\"/order.php\">Order Now</a>\n" + 
               		"                        <a href=\"/about.php\">Who We Are</a>\n" + 
               		"                        <a href=\"/contact.php\">Get In Touch</a>\n" + 
               		"                    </td>\n" + 
               		"                    <td>\n" + 
               		"                        <h3>FOLLOW US</h3>\n" + 
               		"                        <a href=\"#\">Facebook</a>\n" + 
               		"                        <a href=\"#\">Instagram</a>\n" + 
               		"                        <a href=\"#\">Twitter</a>\n" + 
               		"                        <a href=\"#\">LinkedIn</a>\n" + 
               		"                        <a href=\"#\">Pinterest</a>\n" + 
               		"                    </td>\n" + 
               		"                </tr>\n" + 
               		"            </tbody>\n" + 
               		"        </table>\n" + 
               		"    </div>\n" + 
               		"    <p class=\"copyright\">\n" + 
               		"        Copyright &copy; 2020 Sports Store - All Rights Reserved\n" + 
               		"    </p>\n" + 
               		"</div>");       
               out.println("</div>");
               out.println(" <script type=\"text/javascript\" src=\"main.js\"></script>");
               out.println(" <script type=\"text/javascript\" src=\"js/countdown.js\"></script>");
               out.println("</body>");
               out.println("</html>");
       	    }
       	    catch (Exception e)
       	    {
       	        System.err.println ("Cannot connect to database server");
       	        e.printStackTrace();

       	    
       		} 
       		finally 
       	    {    			
       			DatabaseConnection.disconnect();
       			
       		} 				
       	
       }
       
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
