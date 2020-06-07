package iae.s20;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

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
 * Servlet implementation class CheckOutServlet
 */
//@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {	
	   	
        response.setContentType("text/html;charset=UTF-8");
           
           response.setContentType("text/html");
           response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
           PrintWriter out = response.getWriter();
           
               out.println("<!DOCTYPE html>");
               out.println("<html>");
               out.println("<head>");
               out.println("<title>Check Out</title>");  
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
               
              
           
               HttpSession session = request.getSession(false);
               
               String id = (String)session.getAttribute("id"); 
               String quantity = (String)session.getAttribute("quantity"); 
               float priceFloat = (float)session.getAttribute("priceFloat");
               
               			session.setAttribute(id, "id");
                             
                              
                              DecimalFormat df = new DecimalFormat();
                              df.setMaximumFractionDigits(2);
               
               try {   
            	   
            	  
                   String name = "";
    	    		String summary= "";
    	    		String thumbnail = "";
    	    		String category = "";
    	    		String detail = "";
    	    		String price = "";
	    			
//	    			java.sql.Connection connection = DatabaseConnection.connect();
//	                Statement st = connection.createStatement();    	 
//	                ResultSet rs = st.executeQuery("SELECT * FROM products where id= " + id);
    	    		ClientConfig config = new ClientConfig();
	            	Product product = new Product();
	            	Client client = ClientBuilder.newClient(config);
	            	WebTarget target = client.target(UriBuilder.fromUri("http://localhost:6060/PA3").build());
	            	String jsonResponse = target.path("rest").path("products").path(id).request(). //send a request
		                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
		                        get(String.class);
		            System.out.println(jsonResponse);
		            ObjectMapper objectMapper = new ObjectMapper();
		            product = objectMapper.readValue(jsonResponse, new TypeReference<Product>() {});
	    	      
	    	      if (product != null)
	    	      {
	    	    	 
	    	         name = product.getName(); 
	    	         summary = product.getSummary();
	    	         thumbnail = product.getThumbnail();
	    	         category = product.getCategory();
	    	         detail = product.getDetail();
	    	         price = Double.toString(product.getPrice());  	         
	    	         
	    	      }
	    	      	    	      
   	           		
               out.println(" <h1><br>Check Out Form</h1>\n" + 
               		"                <div class=\"orderform\">\n" + 
               		"                        <p class=\"form-message\"></p>\n" + 
               		"                        <div class=\"row\">\n" + 
               		"                            <div class=\"col-50\">\n" + 
               		"                                <h3>Buyer's Information</h3>\n" + 
               		"                                <br />\n" + 
               		"                                <label for=\"fname\"> First Name</label>\n" + 
               		"                                <input type=\"text\" id=\"fname\" name=\"firstname\" placeholder=\"John\" required />\n" + 
               		"                                <label for=\"lname\"> Last Name</label>\n" + 
               		"                                <input type=\"text\" id=\"lname\" name=\"lastname\" placeholder=\"White\" required />\n" + 
               		"                                <label for=\"email\"> Email\n" + 
               		"                                    </label>\n" + 
               		"                                <input type=\"text\" id=\"email\" name=\"email\" placeholder=\"john@example.com\" required />\n" + 
               		"                                <label for=\"phone\"> Phone Number\n" + 
               		"                                <input type=\"text\" id=\"phone\" name=\"phone\" placeholder=\"123-123-1234\" required />\n" + 
               		"                               <br><br> <h3>Shipping Address</h3>\n" + 
               		"                                <br />\n" + 
               		"\n" + 
               		"                                <label for=\"adr\"> Address</label>\n" + 
               		"                                <input type=\"text\" id=\"adr\" name=\"address\" placeholder=\"542 W. 15th Street\" required />\n" + 
               		"                                <label for=\"city\"> City</label>\n" + 
               		"                                <input type=\"text\" id=\"city\" name=\"city\" placeholder=\"New York\" required />\n" + 
               		"\n" + 
               		"                                <div class=\"row\">\n" + 
               		"                                    <div class=\"col-50\">\n" + 
               		"                                        <label for=\"state\">State</label>\n" + 
               		"                                        <input type=\"text\" id=\"state\" name=\"state\" placeholder=\"New York\" required />\n" + 
               		"                                        <div id=\"stateList\"></div>\n" + 
               		"                                    </div>\n" + 
               		"                                    <div class=\"col-50\">\n" + 
               		"                                        <label for=\"zip\">Zip</label>\n" + 
               		"                                        <input type=\"text\" id=\"zip\" name=\"zip\" placeholder=\"10001\" required />\n" + 
               		"                                    </div>\n" + 
               		"                                </div>\n" + 
               		"                                <br>\n" + 
               		"                                <label>\n" + 
               		"                                    <input id=\"same-address\" type=\"checkbox\" checked=\"checked\" name=\"sameaddr\" />\n" + 
               		"                                    Billing address same as shipping\n" + 
               		"                                </label>\n" + 
               		"                                <div id=\"billing-address\">\n" + 
               		"                                    <h3>Billing Address</h3>\n" + 
               		"                                    <br />\n" + 
               		"                                    <label for=\"billaddr\">Address</label>\n" + 
               		"                                    <input type=\"text\" id=\"billaddr\" name=\"billaddr\" placeholder=\"542 W. 15th Street\" />\n" + 
               		"                                    <label for=\"billcity\">City</label>\n" + 
               		"                                    <input type=\"text\" id=\"billcity\" name=\"billcity\" placeholder=\"New York\" />\n" + 
               		"                                    <div class=\"row\">\n" + 
               		"                                        <div class=\"col-50\">\n" + 
               		"                                            <label for=\"billstate\">State</label>\n" + 
               		"                                            <input type=\"text\" id=\"billstate\" name=\"billstate\" placeholder=\"New York\" />\n" + 
               		"                                            <div id=\"stateList\"></div>\n" + 
               		"                                        </div>\n" + 
               		"                                        <div class=\"col-50\">\n" + 
               		"                                            <label for=\"billzip\">Zip</label>\n" + 
               		"                                            <input type=\"text\" id=\"billzip\" name=\"billzip\" placeholder=\"10001\" />\n" + 
               		"                                        </div>\n" + 
               		"                                    </div>\n" + 
               		"                                </div>\n" + 
               		"                                <br />\n" + 
               		"                            </div>\n" + 
               		"\n" + 
               		"                            <div class=\"col-50\">\n" + 
               		"                                <h3>Order Details</h3>\n" + 
               		"                                <br />\n" + 
               		"                                <input type=\"hidden\" style=\"margin-bottom: 20px\" name=id value= \"" + id +"\" >\n" + 
               		"                                <input type=\"hidden\" id=\"pid\" name=\"productid\" value=\"12345\" />\n" + 
			
 					"								<label for=\"cname\">Quantity</label>\n" + 
 					"                               		 <input type=\"text\" id=\"quantity-form\" name=\"quantity\" value="+ quantity +" required />\n" + 
               		"                                <label for=\"method\">Shipping method</label>\n" + 
               		"                                <select id=\"method\" name=\"method\">\n" + 
               		"                                    <option>Overnight ($11.00)</option>\n" + 
               		"                                    <option selected>2-day expedited ($9.50)</option>\n" + 
               		"                                    <option>7-day ground ($6.25)</option>\n" + 
               		"                                </select><br><br>\n" + 
               		"                                <h3>Payment Information</h3>\n" + 
               		"                                <br>\n" + 
               		"                                <label for=\"cname\">Name on Card</label>\n" + 
               		"                                <input type=\"text\" id=\"cname\" name=\"cardname\" placeholder=\"John More Doe\" required />\n" + 
               		"                                <label for=\"ccnum\">Credit card number</label>\n" + 
               		"                                <input type=\"text\" id=\"ccnum\" name=\"cardnumber\" placeholder=\"1111-2222-3333-4444\"\n" + 
               		"                                    required />\n" + 
               		"                                <label for=\"expmonth\">Exp Month\n" + "</label>\n" + 
               		"                                <select id=\"expmonth\" name=\"expmonth\" placeholder=\"September\" required>\n" + 
               		"                                    <option selected>1</option>\n" + 
               		"                                    <option>2</option>\n" + 
               		"                                    <option>3</option>\n" + 
               		"                                    <option>4</option>\n" + 
               		"                                    <option>5</option>\n" + 
               		"                                    <option>6</option>\n" + 
               		"                                    <option>7</option>\n" + 
               		"                                    <option>8</option>\n" + 
               		"                                    <option>9</option>\n" + 
               		"                                    <option>10</option>\n" + 
               		"                                    <option>11</option>\n" + 
               		"                                    <option>12</option>\n" + 
               		"                                </select>\n" + 
               		"\n" + 
               		"                                <div class=\"row\">\n" + 
               		"                                    <div class=\"col-50\">\n" + 
               		"                                        <label for=\"expyear\">Exp Year\n" + 
               		"                                           </label>\n" + 
               		"                                        <input type=\"text\" id=\"expyear\" name=\"expyear\" placeholder=\"2018\" required />\n" + 
               		"                                    </div>\n" + 
               		"                                    <div class=\"col-50\">\n" + 
               		"                                        <label for=\"cvv\">CVV</label>\n" + 
               		"                                        <input type=\"text\" id=\"cvv\" name=\"cvv\" placeholder=\"352\" required />\n" + 
               		"                                    </div>\n" + 
               		"                                    <br /><br />\n" + 
               		"                                </div>\n" + 
               		"                                <div id=\"price-table\">\n" + 
               		"\n" + 
               		"                                    <div>Total Price:</div>\n" +
               		
               		"                                    <div class=\"price-item\">&nbsp; &nbsp;$<span id=\"total-price\">"+ df.format(priceFloat) +"</span></div>\n" );
//               												out.printf("%.2f", priceFloat);
               												out.println("<div>Total Tax: </div>\n" + 
               		"                                    <div class=\"price-item\">+ $<span id=\"tax-amount\"></span>" +"</div>\n" + 
               		"                                    <div>\n" + 
               		"                                        <div>Shipping: </div>\n" + 
               		"                                        <div class=\"price-item\">+ $<span id=\"shipping\"></span></div>\n" + 
               		"                                    </div>\n" + 
               		"\n" + 
               		"                                    <div>\n" + 
               		"                                        <h4>Final Price</h4>\n" + 
               		"                                        <div class=\"price-item\">= $<span id=\"final-price\">" +" </span></div>\n" + 
               		"                                    </div>\n" + 
               		"                                </div>\n" + 
               		"                            </div>\n" + 
               		"                        </div>\n" + 
               		"                        <button type=\"submit\" id=\"order-submit\" class=\"js-submit-order btn\" tabindex=\"0\" id=\"formSubmit\"\n" + 
               		"                            name=\"purchase\">\n" + 
               		"                            Submit Order\n" + 
               		"                        </button>\n");
         

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
               out.println(" <script type=\"text/javascript\" src=\"checkout.js\"></script>");
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
//	DatabaseConnection.disconnect();
}

    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		service(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}
