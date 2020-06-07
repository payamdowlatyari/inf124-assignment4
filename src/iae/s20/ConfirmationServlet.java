package iae.s20;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Future;

import javax.json.JsonObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONObject;

/**
 * Servlet implementation class ConfirmationServlet
 */
//@WebServlet("/ConfirmationServlet")
public class ConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {	
	   	
//        response.setContentType("text/html;charset=UTF-8");
//        HttpSession session = request.getSession(); 
//           
//           response.setContentType("text/html");
//           response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
//           PrintWriter out = response.getWriter();
//           
//               out.println("<!DOCTYPE html>");
//               out.println("<html>");
//               out.println("<head>");
//               out.println("<title>Confirmation</title>");  
//               out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">");
//               out.println(" <link href=\"style.css\" rel=\"stylesheet\">");
//               out.println(" <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js\"></script>\n" + 
//	            		"    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\"></script>");
//               out.println("</head>");
//               out.println("<body>");          
//               out.println("<div class=\"container\">");         
//               out.println("<div class=\"header\">");
//               out.println("<div class=\"content\"> ");
//               out.println("<div class=\"logo\"> ");
//               out.println("<a href=\"HomeServlet\"> ");
//               out.println("<h1>Sports Store</h1>");
//               out.println("</a></div></div>");
//               out.println("<div class=\"navbar\"> ");
//               out.println("<ul class=\"content\"> ");
//               out.println("<li><a href=\"HomeServlet\">Home</a></li> ");
//               out.println("<li><a href=\"ProductServlet\">Products</a></li> ");
//               out.println("<li><a href=\"AboutServlet\">About Us</a></li> ");
//               out.println("<li><a href=\"ContactServlet\">Contact Us</a></li>");
//               out.println("</ul></div></div>");
//               out.println(" <div class=\"main\">");
//               out.println(" <div class=\"content\">"); 
//               out.println(" <h1><br>Confirmation</h1>\n");
//               
//               
//               try(java.sql.Connection connection = DatabaseConnection.connect()) {                               
//            	       
//            	   @SuppressWarnings("unchecked")
//            	   HashMap<Integer, Integer> idList = (HashMap<Integer, Integer>) session.getAttribute("idList");
//            	   int orderID = (int) session.getAttribute("newID");
//            	   out.println("<h4>Order has been confirmed with id: "  + Integer.toString(orderID) + "</h4>");
//            	   out.println("<h3>Products Purchased</h3>");
//            	   out.println("<table><tr>");
//            	   out.println("<th>Product</th>");
//            	   out.println("<th>Quantity</th>");
//            	   out.println("</tr>");
//            	   for(Map.Entry<Integer, Integer> mapElement : idList.entrySet()) {
//            		   out.println("<tr>");
//            		   Statement st = connection.createStatement();    	 
//            		   ResultSet rs = st.executeQuery("SELECT * FROM products where id= " + Integer.toString(mapElement.getKey()));
//            		   if(rs.next()) {
//            			   String name = rs.getString("name");
//            			   out.println("<td>" + name + "</td>");
//            		   }
//            		   out.println("<td>" + Integer.toString(mapElement.getValue()) + "</td>");
//            		   out.println("</tr>");
//            	   }
//            	   out.println("</table>");
//            	   
//	                
// 
//       		 out.println("</div>");
//       		 out.println("</div>");	
//       		 out.println("<br><div class=\"footer\">\n" + 
//               		"    <div class=\"content\">\n" + 
//               		"        <table width=\"100%\" cellspacing=\"20\">\n" + 
//               		"            <tbody>\n" + 
//               		"                <tr>\n" + 
//               		"                    <td>\n" + 
//               		"                        <h3>WHO WE ARE</h3>\n" + 
//               		"                        <p> Welcome to Sports Store!</p>\n" + 
//               		"                        <p>\n" + 
//               		"                            We are an online business currently based in\n" + 
//               		"                            Irvine, California.\n" + 
//               		"                        </p>\n" + 
//               		"                        <a href=\"/about.php\">Learn more... </a>\n" + 
//               		"                    </td>\n" + 
//               		"                    <td>\n" + 
//               		"                        <h3>MAIN OFFICE</h3>\n" + 
//               		"                        <p>\n" + 
//               		"                            <address>\n" + 
//               		"                                40234 Sunrise Blvd,\n" + 
//               		"                                Irvine, California, 92811\n" + 
//               		"                            </address>\n" + 
//               		"                        </p>\n" + 
//               		"                        <p> Tel: (949) 800 2221</p>\n" + 
//               		"                        <p> Email: info@sportstore.com</p>\n" + 
//               		"                        <a href=\"/contact.php\">Learn More...</a>\n" + 
//               		"                    </td>\n" + 
//               		"                    <td>\n" + 
//               		"                        <h3>QUICK LINKS</h3>\n" + 
//               		"                        <a href=\"/index.php\">First Page</a>\n" + 
//               		"                        <a href=\"/products.php\">What We Offer</a>\n" + 
//               		"                        <a href=\"/order.php\">Order Now</a>\n" + 
//               		"                        <a href=\"/about.php\">Who We Are</a>\n" + 
//               		"                        <a href=\"/contact.php\">Get In Touch</a>\n" + 
//               		"                    </td>\n" + 
//               		"                    <td>\n" + 
//               		"                        <h3>FOLLOW US</h3>\n" + 
//               		"                        <a href=\"#\">Facebook</a>\n" + 
//               		"                        <a href=\"#\">Instagram</a>\n" + 
//               		"                        <a href=\"#\">Twitter</a>\n" + 
//               		"                        <a href=\"#\">LinkedIn</a>\n" + 
//               		"                        <a href=\"#\">Pinterest</a>\n" + 
//               		"                    </td>\n" + 
//               		"                </tr>\n" + 
//               		"            </tbody>\n" + 
//               		"        </table>\n" + 
//               		"    </div>\n" + 
//               		"    <p class=\"copyright\">\n" + 
//               		"        Copyright &copy; 2020 Sports Store - All Rights Reserved\n" + 
//               		"    </p>\n" + 
//               		"</div>");       
//               out.println("</div>");
//               out.println(" <script type=\"text/javascript\" src=\"\"></script>");
//               out.println("</body>");
//               out.println("</html>");	
//               
//	    	                
//			}
//			catch (Exception e)
//			{
//			    System.err.println ("Cannot connect to database server");
//			    e.printStackTrace();
//			
//			
//			} 
//			finally 
//			{    			
//				HashMap<Integer, Integer> newIdList = new HashMap<Integer, Integer>();
//				session.setAttribute("idList", newIdList);
//				
//			}
	RequestDispatcher rd = getServletContext().getRequestDispatcher("/OrderConfirmation");
	rd.forward(request, response);

}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(true);
		Enumeration<String> paramNames = request.getParameterNames();
		HashMap<String, String> params = new HashMap<String, String>();
		@SuppressWarnings("unchecked")
		HashMap<Integer, Integer> idList = (HashMap<Integer, Integer>)session.getAttribute("idList");
		if(idList == null) {
			System.out.println("idList does not exist");
		}
		while(paramNames.hasMoreElements()) {
			String paramName = (String)paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			String paramValue = paramValues[0];
			System.out.println("key: " + paramName + "  value: " + paramValue);
			params.put(paramName, paramValue);
		}
		Float subtotal = (Float)session.getAttribute("subtotal");
		try{
			JSONObject formData = new JSONObject();
			ClientConfig config = new ClientConfig();
        	Client client = ClientBuilder.newClient(config);
        	WebTarget target = client.target(UriBuilder.fromUri("http://localhost:6060/PA3/rest/orders").build());
        	//Form formData = new Form();
//        	MultivaluedMap<String,String> formData = new MultivaluedHashMap<String,String>();
			Order newOrder = new Order();
			newOrder.setAddress(params.get("address"));
			formData.put("address", params.get("address"));
			newOrder.setCardname(params.get("cardname"));
//			formData.param("cardname", params.get("cardname"));
			newOrder.setEmail(params.get("email"));
			int newID = Math.abs((int)(Math.random()* 10000));
			session.setAttribute("newID", newID);
			newOrder.setId(newID);
			formData.put("id", newID);
			formData.put("email", params.get("email"));
			newOrder.setPhone(params.get("phoneNumber"));
			formData.put("phoneNumber", params.get("phoneNumber"));
			newOrder.setCity(params.get("city"));
			formData.put("city", params.get("city"));
			
			newOrder.setState(params.get("state"));
			formData.put("state", params.get("state"));
			newOrder.setZip(Integer.parseInt(params.get("zip")));
			formData.put("zip", params.get("zip"));
			newOrder.setMethod(params.get("shippingMethod"));
			formData.put("shippingMethod", params.get("shippingMethod"));
			newOrder.setCardname(params.get("cardname"));
			formData.put("cardname", params.get("cardname"));
			newOrder.setCardnumber(params.get("cardnumber"));
			formData.put("cardnumber", params.get("cardnumber"));
			newOrder.setExpmonth(Integer.parseInt(params.get("expmonth")));
			formData.put("expmonth", Integer.parseInt(params.get("expmonth")));
			newOrder.setExpyear(Integer.parseInt(params.get("expyear")));
			formData.put("expyear", Integer.parseInt(params.get("expyear")));
			newOrder.setCvv(Integer.parseInt(params.get("cvv")));
			formData.put("cvv", Integer.parseInt(params.get("cvv")));
			newOrder.setName(params.get("fullname"));
			formData.put("fullname", params.get("fullname"));
			Double newPrice = Double.parseDouble(Float.toString(subtotal));
			System.out.println(newPrice);
			newOrder.setTotalPrice(newPrice);
			formData.put("subtotal", subtotal);
			System.out.println(formData);
			Response res = client.target("http://localhost:6060/PA3/rest/orders").request(MediaType.APPLICATION_JSON).post(Entity.json(newOrder));
			String result = res.readEntity(String.class);
			System.out.println(result);
			for(Map.Entry<Integer, Integer> mapElement: idList.entrySet()) {
				ProductOrder po = new ProductOrder(newID, mapElement.getKey(), mapElement.getValue());
				Response resPO = client.target("http://localhost:6060/PA3/rest/orders/product").request(MediaType.APPLICATION_JSON).post(Entity.json(po));
				System.out.println(resPO);
			}
			//Future<String> res = target.request(MediaType.APPLICATION_FORM_URLENCODED).accept(MediaType.TEXT_PLAIN).buildPost(Entity.form(formData)).submit(String.class);
			//System.out.println("res: " + res);
//			Response res = target.request().post(Entity.form(formData));
//			System.out.println("res: " + res);
			
        	
//			PreparedStatement orderInsert;
//			PreparedStatement productInsert;
//			String orderInsertStr = "insert into orders values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
//			String productInsertStr = "insert into orderProducts values(?,?,?);";
//			connection.setAutoCommit(false);
//			int newID = (int)(Math.random()* 10000);
//			session.setAttribute("newID", newID);
//			orderInsert = connection.prepareStatement(orderInsertStr);
//			productInsert = connection.prepareStatement(productInsertStr);
//			orderInsert.setInt(1, newID);
//			orderInsert.setString(2, params.get("email"));
//			orderInsert.setString(3, params.get("phoneNumber"));
//			orderInsert.setString(4, params.get("address"));
//			orderInsert.setString(5, params.get("city"));
//			orderInsert.setString(6, params.get("state"));
//			orderInsert.setInt(7, Integer.parseInt(params.get("zip")));
//			orderInsert.setString(8, params.get("shippingMethod"));
//			orderInsert.setString(9, params.get("cardname"));
//			orderInsert.setString(10, params.get("cardnumber"));
//			orderInsert.setInt(11, Integer.parseInt(params.get("expmonth")));
//			orderInsert.setInt(12, Integer.parseInt(params.get("expyear")));
//			orderInsert.setInt(13, Integer.parseInt(params.get("cvv")));
//			orderInsert.setString(14, params.get("fullname"));
//			orderInsert.setFloat(15, subtotal);
//			orderInsert.execute();
//			for(Map.Entry<Integer, Integer> mapElement : idList.entrySet()) {
//				productInsert.setInt(1, newID);
//				productInsert.setInt(2, mapElement.getKey());
//				productInsert.setInt(3, mapElement.getValue());
//				productInsert.execute();
//			}
//			connection.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		doGet(request, response);
	}

}
