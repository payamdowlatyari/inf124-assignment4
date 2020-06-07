package iae.s20;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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


//@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		 
		 String id = request.getParameter("id");		
		  response.setContentType("text/html;charset=UTF-8");
	        
	        response.setContentType("text/html");
	        response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
	        
	            out.println("<!DOCTYPE html>");
	            out.println("<html>");
	            out.println("<head>");
	            out.println("<title>Details</title>");  
	            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">");
	            out.println(" <link href=\"style.css\" rel=\"stylesheet\">");
	            out.println(" <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js\"></script>\n" + 
	            		"    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\"></script>");

	            out.println("</head>");
	            out.println("<body>");
	            
	            out.println("<div class=container>");
	            out.println("<div class=\"header\">");
	            
	            out.println("<div class=\"content\"> ");
	            out.println("<div class=\"logo\"> ");
	            out.println("<a href=\"#\"> ");
	            out.println("<h1>Sports Store</h1>");
	            out.println("</a></div></div>");
	            out.println("<div class=\"navbar\"> ");
	            out.println("<ul class=\"content\"> ");
	            out.println("<li><a href=\"index.jsp\">Home</a></li> ");
	            out.println("<li><a href=\"products.jsp\"class=\"active\">Products</a></li> ");
	            out.println("<li><a href=\"AboutServlet\">About Us</a></li> ");
	            out.println("<li><a href=\"ContactServlet\">Contact Us</a></li>");
	            out.println("<li><a href=\"CartServlet\">Shopping Cart</a></li>");
	            out.println("</ul></div></div>");
	              

	             out.println(" <div class=\"main\">");
	             out.println(" <div class=\"content\">");

	            out.println("<br><br> <h1> Product Details </h1>");

	    		String name = "";
	    		String summary= "";
	    		String thumbnail = "";
	    		String category = "";
	    		String detail = "";
	    		String price = "";
	    		
	    		
	    		try {   		
	    			
//	    			java.sql.Connection connection = DatabaseConnection.connect();
//	                Statement st = connection.createStatement();    	 
//	                ResultSet rs = st.executeQuery("SELECT * FROM products where id= " + id);
	                
	                ClientConfig config = new ClientConfig();
	            	Product product = new Product();
	            	Client client = ClientBuilder.newClient(config);
	            	WebTarget target = client.target(UriBuilder.fromUri("http://localhost:6060/PA3").build());
	            	try {
	            	String jsonResponse = target.path("rest").path("products").path(id).request(). //send a request
	                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
	                        get(String.class);
	            	System.out.println(jsonResponse);
	            	ObjectMapper objectMapper = new ObjectMapper();
	            	product = objectMapper.readValue(jsonResponse, new TypeReference<Product>() {});
	            	}
	            	catch(Exception e) {
	            		e.printStackTrace();
	            	}

	    	      
	    	      if (product != null)
	    	      {
	    	    	  
	    	    	 id = Integer.toString(product.getId());
	    	    	 
	    	         name = product.getName();
	    	         summary = product.getSummary();
	    	         thumbnail = product.getThumbnail();
	    	         category = product.getCategory();
	    	         detail = product.getDetail();
	    	         price = Double.toString(product.getPrice());
	    	         thumbnail = "assets/" + thumbnail; 
	    	         
	    	         
	    	         HttpSession session = request.getSession(); /* Creating a new session*/
	    	         @SuppressWarnings("unchecked")
					Stack<Integer> prevViewedItems = (Stack<Integer>) session.getAttribute("prevItemsViewed");
	    	         if(prevViewedItems == null) {
	    	        	 Stack<Integer> newPVI = new Stack<Integer>();
	    	        	 session.setAttribute("prevItemsViewed", newPVI);
	    	        	 prevViewedItems = newPVI;
	    	         }
	    	         System.out.println("At page: " + id);
	    	         prevViewedItems.push(Integer.parseInt(id));
	    	         session.setAttribute("prevItemsViewed", prevViewedItems);
	    	         
	    	         
	    	         session.setAttribute("id", id); 
	    	         session.setAttribute("name", name); 
	    	         session.setAttribute("summary", summary); 
	    	         session.setAttribute("thumbnail", thumbnail); 
	    	         session.setAttribute("category", category); 
	    	         session.setAttribute("detail", detail); 
	    	         session.setAttribute("price", price); 
	    	         
	    	      
	    	        // print the results
	    	         out.println("<table width=\"100%\" cellspacing=\"20\">");
	    	         out.println("<tbody>");
	    	         out.println("<tr>");
	        		 out.println("<td>");
	    	         out.println("<img src=\""+thumbnail+"\"class=\"thumbnail\" id=\"img-detail\" width=\"250\"/>");
	    	         out.println("</td>");
	    	         out.println("<td>");
	                 out.println("<h4>Name: </h4>");
	        		 out.println("<p>" +  name + " </p>");

	                 out.println("<h4>"+ summary +"</h4>");
	                 out.println("<h4>Category: </h4>");
	        		 out.println("<p>" +  category + " </p>");
	        		 
	                
	                 out.println("<form  action=\"CartServlet\" method=\"POST\">");
	                 out.println("<h4>Quantity: </h4>");
	        		 out.println(" <select class=\"quantity-option\" id=\"quantity\" onchange=\"updatePrice()\" name=\"quantity\">\n" + 
	        		 		"                                    <option>1</option>\n" + 
	        		 		"                                    <option>2</option>\n" + 
	        		 		"                                    <option>3</option>\n" + 
	        		 		"                                    <option>4</option>\n" + 
	        		 		"                                    <option>5</option>\n" + 
	        		 		"                                </select>");
	                 out.println("<h4>Unit Price: </h4>");
	        		 out.println("<p id=\"unitPrice\">$" +  price + "</p>");
	                 out.println("<input type=hidden id=\"name\" name=\"name\" value="+ name +">");
	                 out.println("<input type=hidden id=\"price\" name=\"price\" value="+ price +">");
	                 out.println("<input type=hidden id=\"id\" name=\"id\" value="+ id +">");

//	        		 out.println("<h4>Total Price: </h4>");
//	        		 out.println("<span>$</span><span id=\"total-price\"></span> <br><br>");
	        		 
	        		 
	                 out.println("<input type=\"submit\" value=\"Add to Cart\">");  
	                 out.println("</form>");


	        		 out.println("</td>");
	        		 out.println("</tr>");
	        		 out.println("</tbody>");
	        		 out.println("</table>");
	        		 
	        		 out.println("<h1>Details</h1>");
	                 out.println("<div class=\"detail\">"+ detail +"</div>");

	    	      }
	    	      
	    	      out.println("</div>");	    
		    		 out.println("</div>");	  
		    		 
		    		  out.println("<div class=\"footer\">\n" + 
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
