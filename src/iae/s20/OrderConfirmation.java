package iae.s20;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

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
 * Servlet implementation class OrderConfirmation
 */
@WebServlet("/OrderConfirmation")
public class OrderConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderConfirmation() {
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
           
           response.setContentType("text/html");
           response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
           PrintWriter out = response.getWriter();
           
               out.println("<!DOCTYPE html>");
               out.println("<html>");
               out.println("<head>");
               out.println("<title>Confirmation</title>");  
               out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">");
               out.println(" <link href=\"style.css\" rel=\"stylesheet\">");
               out.println(" <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js\"></script>\n" + 
	            		"    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\"></script>");
               out.println("</head>");
               out.println("<body>");          
               out.println("<div class=\"container\">");         
               out.println("<div class=\"header\">");
               out.println("<div class=\"content\"> ");
               out.println("<div class=\"logo\"> ");
               out.println("<a href=\"index.jsp\"> ");
               out.println("<h1>Sports Store</h1>");
               out.println("</a></div></div>");
               out.println("<div class=\"navbar\"> ");
               out.println("<ul class=\"content\"> ");
               out.println("<li><a href=\"index.jsp\">Home</a></li> ");
               out.println("<li><a href=\"products.jsp\">Products</a></li> ");
               out.println("<li><a href=\"AboutServlet\">About Us</a></li> ");
               out.println("<li><a href=\"ContactServlet\">Contact Us</a></li>");
               out.println("<li><a href=\"CartServlet\">Shopping Cart</a></li>");
               out.println("</ul></div></div>");
               out.println(" <div class=\"main\">");
               out.println(" <div class=\"content\">"); 
               out.println(" <h1><br>Confirmation</h1>\n");
               
               
               try {                               
            	       
            	   @SuppressWarnings("unchecked")
            	   HashMap<Integer, Integer> idList = (HashMap<Integer, Integer>) session.getAttribute("idList");
            	   int orderID = (int) session.getAttribute("newID");
            	   out.println("<h4>Order has been confirmed with id: "  + Integer.toString(orderID) + "</h4>");
            	   ClientConfig config = new ClientConfig();
            	   Order order = new Order();
            	   Client client = ClientBuilder.newClient(config);
            	   WebTarget target = client.target(UriBuilder.fromUri("http://localhost:6060/PA3").build());
            	   try {
            		   String jsonResponse = target.path("rest").path("orders").path(Integer.toString(orderID)).request(). //send a request
	                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
	                        get(String.class);
            		   System.out.println(jsonResponse);
            		   ObjectMapper objectMapper = new ObjectMapper();
            		   order = objectMapper.readValue(jsonResponse, new TypeReference<Order>() {});
            	   }
            	   catch(Exception e) {
            		   e.printStackTrace();
            	   }
            	   out.println("<h3>Order Information</h3>");
            	   out.println("<p>Name: " + order.getName() + "</p>");
            	   out.println("<p>Email: " + order.getEmail() + "</p>");
            	   out.println("<p>Phone Number: " + order.getPhone() + "</p>");
            	   out.println("<p>Total Price: " + Double.toString(order.getTotalPrice()) + "</p>");
            	   out.println("<p>Card Number: " + replaceAsterix(order.getCardnumber()) + "</p>");
            	   out.println("<p>Card Name: " + order.getCardname() + "</p>");
            	   out.println("<h3>Products Purchased</h3>");
            	   out.println("<table><tr>");
            	   out.println("<th>Product</th>");
            	   out.println("<th>Quantity</th>");
            	   out.println("</tr>");
            	   for(Map.Entry<Integer, Integer> mapElement : idList.entrySet()) {
            		   out.println("<tr>");
//            		   Statement st = connection.createStatement();    	 
//            		   ResultSet rs = st.executeQuery("SELECT * FROM products where id= " + Integer.toString(mapElement.getKey()));
            		   Product indProduct = new Product();
            		   Client client2 = ClientBuilder.newClient(new ClientConfig());
            		   WebTarget target2 = client2.target(UriBuilder.fromUri("http://localhost:6060/PA3").build());
            		   try {
       	            	String jsonResponse = target2.path("rest").path("products").path(Integer.toString(mapElement.getKey())).request(). //send a request
       	                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
       	                        get(String.class);
       	            	System.out.println(jsonResponse);
       	            	ObjectMapper objectMapper = new ObjectMapper();
       	            	indProduct = objectMapper.readValue(jsonResponse, new TypeReference<Product>() {});
       	            	}
       	            	catch(Exception e) {
       	            		e.printStackTrace();
       	            	}
            		   if(indProduct != null) {
            			   String name = indProduct.getName();
            			   out.println("<td>" + name + "</td>");
            		   }
            		   out.println("<td>" + Integer.toString(mapElement.getValue()) + "</td>");
            		   out.println("</tr>");
            	   }
            	   out.println("</table>");
            	   
	                
 
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
               out.println(" <script type=\"text/javascript\" src=\"\"></script>");
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
				HashMap<Integer, Integer> newIdList = new HashMap<Integer, Integer>();
				session.setAttribute("idList", newIdList);
				
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String replaceAsterix(String s) {
		int len = s.length();
		String toReturn = "";
		for(int i = 0; i < len-4; i++) {
			toReturn += "*";
		}
		return toReturn + s.substring(len-4, len);
	}

}
