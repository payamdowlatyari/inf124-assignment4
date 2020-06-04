package iae.s20;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference; 
import org.glassfish.jersey.client.ClientConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;


//@WebServlet(name = "MainServlet", urlPatterns = { "/MainServlet" })
//@WebServlet("/products")
public class ProductServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
           
    public ProductServlet() {
        super();
    }
    
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
//    	System.out.println(jsonResponse);
//    	ObjectMapper objectMapper = new ObjectMapper();
//    	List<Product> products = objectMapper.readValue(jsonResponse, new TypeReference<List<Product>>() {});
    	
    	   	
     response.setContentType("text/html;charset=UTF-8");
        
        response.setContentType("text/html");
        response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        PrintWriter out = response.getWriter();
        
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Products</title>");  
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
            out.println("<a href=\"HomeServlet\"> ");
            out.println("<h1>Sports Store</h1>");
            out.println("</a></div></div>");
            out.println("<div class=\"navbar\"> ");
            out.println("<ul class=\"content\"> ");
            out.println("<li><a href=\"HomeServlet\">Home</a></li> ");
            out.println("<li><a href=\"ProductServlet\"class=\"active\">Products</a></li> ");
            out.println("<li><a href=\"AboutServlet\">About Us</a></li> ");
            out.println("<li><a href=\"ContactServlet\">Contact Us</a></li>");
            out.println("<li><a href=\"CartServlet\">Shopping Cart</a></li>");
            out.println("</ul></div></div>");
            out.println(" <div class=\"main\">");
            out.println(" <div class=\"content\">");          
            out.println("<br><br> <h1>Products for Sale</h1>");

            String id = ""; 
    		String name = "";
    		String price = "";
    		String thumbnail = "";
    		String category = "";
    		
    		
    		try {   		
    			
//    			java.sql.Connection connection = DatabaseConnection.connect();
//                Statement st = connection.createStatement();    	 
//                ResultSet rs = st.executeQuery("SELECT * FROM products;");
                
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
                 out.println("<h3 id=\"unitPrice\">"+ price +"</h3>");

                 out.println("<form  action=\"DetailServlet\" method=\"GET\">");
                 out.println("<input type=hidden id=id name=id value="+ id +">");
                 out.println("<input type=submit>");               
                 out.println("</form>"); 
                 out.println("</td>");
                 
                 if (Integer.parseInt(id) == 5) {               	 
                	 out.println("</tr><tr>");           	 
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
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	
    	service(request, response);
    	 	
    }
         
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private static URI baseURI() {
        return UriBuilder.fromUri("http://localhost:6060/PA3").build();
    }

}
