package iae.s20;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

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
 * Servlet implementation class CartServlet
 */
//@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

 public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {	
	   	
        response.setContentType("text/html;charset=UTF-8");
           
           response.setContentType("text/html");
           response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
           PrintWriter out = response.getWriter();

//           String quantity = request.getParameter("quantity");
          
           HttpSession session = request.getSession(false);
           HashMap<Integer, Integer> idList = (HashMap<Integer, Integer>)session.getAttribute("idList");
           if(idList == null) {
        	   HashMap<Integer, Integer> newIdList = new HashMap<Integer, Integer>();
        	   session.setAttribute("idList", newIdList);
        	   idList = newIdList;
           }
//           String id = (String)session.getAttribute("id"); 
//           String price = (String)session.getAttribute("price");
//           String name = (String)session.getAttribute("name");
//           float pi = Float.valueOf(price.trim()).floatValue();
//           float qi = Float.valueOf(quantity.trim()).floatValue();
//           float priceFloat = pi * qi;
           
           //String totalPrice = "";
   
//	         session.setAttribute("quantity", quantity); 
//	         session.setAttribute("priceFloat", priceFloat);

               out.println("<!DOCTYPE html>");
               out.println("<html>");
               out.println("<head>");
               out.println("<title>Cart</title>");  
               out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">");
               out.println(" <link href=\"style.css\" rel=\"stylesheet\">");
               out.println(" <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js\"></script>\n" + 
	            		"    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\"></script>");
               
               out.println("</head>");
               out.println("<script type='text/javascript'>");
               out.println("function updateTotalPrice(){\n" + 
               		"	var subtotal = document.getElementById('subtotal');\n" + 
               		"	console.log(subtotal);\n" + 
               		"	var shippingMethod = document.getElementById('method');\n" + 
               		"	console.log(shippingMethod);\n" + 
               		"}");
               out.println("</script>");
               out.println("<script src='checkout.js'></script>");
               out.println("<body>");    
               out.println(" <script type=\"text/javascript\" src=\"main.js?version=51\"></script>");
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
               out.println("<br><br>  <h1>Shopping Card</h1>");   
               out.println("<div class='shopping-cart'>");
               
               out.println("<form method='POST' action='ConfirmationServlet'>");
               out.println("<ul>");
               float subtotal = (float) 0.0;
               for (HashMap.Entry<Integer, Integer> entry : idList.entrySet()) {
            	   out.println("<li>");
            	   try{
            		   int id = entry.getKey();
            		   int quantity = entry.getValue();
            		   ClientConfig config = new ClientConfig();
   	            		Product product = new Product();
   	            		Client client = ClientBuilder.newClient(config);
   	            		WebTarget target = client.target(UriBuilder.fromUri("http://localhost:6060/PA3").build());
   	            		String jsonResponse = target.path("rest").path("products").path(Integer.toString(id)).request(). //send a request
   		                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
   		                        get(String.class);
   		            	System.out.println(jsonResponse);
   		            	ObjectMapper objectMapper = new ObjectMapper();
   		            	product = objectMapper.readValue(jsonResponse, new TypeReference<Product>() {});
            		   if(product != null) {
	            		   String name = product.getName();
	            		   Float price = (float) product.getPrice();
	            		   subtotal += (quantity*price);
	            		   out.println("<ul>" + name + "\tPrice:" + Float.toString(price) + " Quantity:" + Integer.toString(quantity) + "</ul>");
            		   }
            	   }
            	   catch(Exception e) {
            		   e.printStackTrace();
            	   }
       			}
               session.setAttribute("subtotal", subtotal);
               
               out.println("</ul>");
               out.println("<p name='subtotal' id='" + Float.toString(subtotal) + "'>Subtotal: " + Float.toString(subtotal) + "</p>");
               out.println("<div class='orderform'>");
               out.println("<div class='row'>");
               out.println("<div class='col-50'>");
               out.println("<h3>Buyer's Information</h3>");
               out.println("<label for='fname'>First Name</label>");
               out.println("<input type='text' id='name' name='fullname' placeholder='John White' required />");
               out.println("<label for='email'>Email</label>");
               out.println("<input type='text' id='email' name='email' placeholder='jwhite@john.com' required />");
               out.println("<label for='email'>Phone Number</label>");
               out.println("<input type='text' id='phoneNumber' name='phoneNumber' placeholder='1231231234' required />");
               out.println("<label for='method'>Shipping Method</label>");
               out.println("<select id='method' name='shippingMethod' onchange='updateTotalPrice()' required>");
               out.println("<option value='overnight'>Overnight ($11.00)</option>");
               out.println("<option value='2day' selected>2-day expedited ($9.50)</option>");
               out.println("<option value='7day'>7-day ground ($6.25)</option>");
               out.println("</select>");
               out.println("<h3>Shpping Information</h3>");
               out.println("<label for='adr'>Address</label>");
               out.println("<input type='text' id='adr' name='address' placeholder='123 Bob St' required />");
               out.println("<label for='city'>City</label>");
               out.println("<input type='text' id='city' name='city' placeholder='New York City' required />");
               out.println("<label for='state'>State</label>");
               out.println("<input type='text' id='state' name='state' placeholder='New York' required />");
               out.println("<label for='zip'>Zip Code</label>");
               out.println("<input type='text' id='zip' name='zip' placeholder='10001' required />");
               out.println("<h3>Payment Details</h3>");
               out.println("<label for='cname'>Name on Card</label>");
               out.println("<input type='text' id='cname' name='cardname' placeholder='John White' required />");
               out.println("<label for='ccnum'>Credit Card Number</label>");
               out.println("<input type='text' id='ccnum' name='cardnumber' placeholder='111-111-111' required />");
               out.println("<label for='expmonth'>Expiry Month</label>");
               out.println("<select id='expmonth' name='expmonth' placeholder='September' required>");
               out.println("<option selected>1</option>");
               for(int i = 2; i <= 12; i++) {
            	   out.println("<option>" + Integer.toString(i) + "</option>");
               }
               out.println("</select>");
               out.println("<label for='expyear'>Expiry year</label>");
               out.println("<input type='text' id='expyear' name='expyear' placeholder='2020' required />");
               out.println("<label for='cvv'>CVV</label>");
               out.println("<input type='text' id='cvv' name='cvv' placeholder='352' required />");
               out.println("<div id=\"price-table\">\n" + 
                  		"\n" + 
                  		"                                    <div>Total Subtotal:</div>\n" + 
                  		"                                    <div class=\"price-item\">&nbsp; &nbsp;$<span id=\"total-price\"></span></div>\n" + 
                  		"\n" + 
                  		"                                        <div>Shipping: </div>\n" + 
                  		"                                        <div class=\"price-item\">+ $<span id=\"shipping\"></span></div>\n" + 
                  		"                                    </div>\n" + 
                  		"\n" + 
                  		"                                    <div>\n" + 
                  		"                                        <h4>Final Price</h4>\n" + 
                  		"                                        <div class=\"price-item\">= $<span id=\"final-price\"></span></div>\n" + 
                  		"                                        <input type=\"hidden\" id=\"totalPrice\" name=\"totalPrice\" value=\"\" />\n" + 
                  		"                                    </div>\n" + 
                  		"                                </div>");
               out.println("<button type='submit' id='order-submit' class='js-submit-order btn' tabindex='0' id='form-submit' name='purchase'>");
               out.println("Submit Order</button>");
               out.println("</div></div></div>");
               out.println("</form></div>");
       		  out.println("</div>");	
       		  out.println("<br><div class=\"footer\">\n" + 
       				 "        <table width=\"100%\" cellspacing=\"20\">\n" + 
               		"    <div class=\"content\">\n" + 
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
//               out.println(" <script type=\"text/javascript\" src=\"main.js\"></script>");
               out.println("</body>");
               out.println("</html>");			
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
		HttpSession session = request.getSession(true);
		@SuppressWarnings("unchecked")
		HashMap<Integer, Integer> idList =(HashMap<Integer, Integer>)session.getAttribute("idList");
		if(idList == null) {
			HashMap<Integer, Integer> newIdList = new HashMap<Integer, Integer>();
			session.setAttribute("idList", newIdList);
			idList = newIdList;
		}
		String idStr = request.getParameter("id");
		String quantityStr = request.getParameter("quantity");
		int id = Integer.parseInt(idStr);
		int quantity = Integer.parseInt(quantityStr);
		if(!idList.containsKey(id)) {
			idList.put(id, quantity);
		}
		else {
			int initialQuantity = idList.get(id);
			idList.put(id, quantity + initialQuantity);
		}
		session.setAttribute("idList", idList);
		doGet(request, response);
	}

}
