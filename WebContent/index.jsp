<%-- 
    Document   : index
    Created on : May 23, 2020, 8:01:25 PM
    Author     : zhude
--%>
<%@page import="java.sql.*"%>
<%@page import="javax.servlet.*" %>
<%@page import="java.io.*" %>
<%@page import="javax.ws.rs.client.*" %>
<%@page import="javax.ws.rs.client.ClientBuilder" %>
<%@page import="java.net.*" %>
<%@page import="java.util.*" %>
<%-- <%@page import="java.util.List" %> --%>
<%@page import="org.glassfish.jersey.client.ClientConfig" %>
<%@page import="org.codehaus.jackson.type.TypeReference" %>
<%@page import ="org.codehaus.jackson.map.ObjectMapper" %>
<%@page import="javax.ws.rs.core.UriBuilder" %>
<%@page import="javax.ws.rs.core.MediaType" %>
<%-- <%@page import="javax.servlet.ServletException" %> --%>
<%@page import="iae.s20.Product" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include  file="components/head.html" %>
<body>
    
    <div class="container">
        <%@include  file="components/header.html" %>
        <div class="main">
            <div class="content">
<%--             <%request.getRequestDispatcher("PreviousItems").include(request, response); %> --%>
				<jsp:include page="/PreviousItems" />
                <h1>New Products</h1>
                <table width="100%" cellspacing="20">
                    <tbody>
                            <%
                                try {
                                	List<Product> products = new ArrayList<Product>();
                                	Client client = ClientBuilder.newClient(new ClientConfig());
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
                                	int rowCount = 5, cnt = 0;
                                    
                                    for(Product product: products)
                                    {
                                    	if(cnt < 5){
	                                        if(cnt == 0)
	                                            out.println("<tr>");
	                                        cnt++;
	                                        
	                                        String id = Integer.toString(product.getId());  	 
	                           	         	String name = product.getName(); 	
	                           	         	String price = Double.toString(product.getPrice());   	         
	                           	         	String thumbnail = product.getThumbnail();
	                           	         	String category = product.getCategory();
	                           	         	thumbnail = "assets/" + thumbnail;
	                           	         	
	                                        
	                                         out.println("<td class=\"product\">");
	                                        
	                                        out.println("<img src=\""+thumbnail+"\"/>");
	                           	         	out.println("<h4>"+ category +"</h4>");
	                                        out.println("<h2>"+ name +"</h2>");
	                                        out.println("<h3 id=\"unitPrice\">"+ price +"</h3>");
	                                        
	                                        out.println("<form  action=\"DetailServlet\" method=\"GET\">");
	                                        out.println("<input type=hidden id=id name=id value="+ id +">");
	                                        out.println("<input type=submit>");               
	                                        out.println("</form>"); 
	                                        
	                                        out.println("<hr/>");
	                                        out.println("</td>");
                           	     
                                    	}
                                    }
                                }
                                catch(Exception e) {
                                    out.println("Exception caught: " +e.getMessage());
                                }
                            %>
                                <%--
                                $stmt = $pdo->query("SELECT * FROM products LIMIT 0, 5");
                                while ($row = $stmt->fetch()) {
                                    echo '<td class="product">
                                            <a href="detail.php?id='.$row['id'].'"><img src="assets/'.$row['thumbnail'].'" alt="'.$row['name'].' picture"></a>
                                            <h4>'.$row['category'].'</h4>
                                            <h2>'.$row['name'].'</h2>
                                            <h3>$'.$row['price'].'</h3>
                                            <p>'.$row['summary'].'</p>
                                            <hr />
                                            <a href="detail.php?id='.$row['id'].'" class="addtocart">See Details</a>
                                        </td>';
                                }
                                --%>
                    </tbody>
                </table>
            </div>
            <div class="hotdeal">
                <div class="detail">
                    <h1>Back to school deal</h1>
                    <div class="countdown">00 DAYS, 00 HOURS, 00 MINS, 00 SECS</div>
                    <h2>UP TO 50% OFF</h2>
                    <a href="ProductServlet" class="getdeal">Shop Now</a>
                </div>
            </div>
                        
                        <div class="content">
                <table width="100%" height="100%" cellspacing="20">
                    <tbody>
                            <%
                                try {
                                	List<Product> products = new ArrayList<Product>();
                                	Client client = ClientBuilder.newClient(new ClientConfig());
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
                                	int rowCount = 5, cnt = 0;
                                    
                                    for(Product product: products)
                                    {
                                    	if(cnt >= 5){
	                                        if(cnt == 0)
	                                            out.println("<tr>");
	                                        cnt++;
	                                        
	                                        String id = Integer.toString(product.getId());  	 
	                           	         	String name = product.getName(); 	
	                           	         	String price = Double.toString(product.getPrice());   	         
	                           	         	String thumbnail = product.getThumbnail();
	                           	         	String category = product.getCategory();
	                           	         	thumbnail = "assets/" + thumbnail;
	                           	         	
	                                        
	                                         out.println("<td class=\"product\">");
	                                        
	                                        out.println("<img src=\""+thumbnail+"\"/>");
	                           	         	out.println("<h4>"+ category +"</h4>");
	                                        out.println("<h2>"+ name +"</h2>");
	                                        out.println("<h3 id=\"unitPrice\">"+ price +"</h3>");
	                                        
	                                        out.println("<form  action=\"DetailServlet\" method=\"GET\">");
	                                        out.println("<input type=hidden id=id name=id value="+ id +">");
	                                        out.println("<input type=submit>");               
	                                        out.println("</form>"); 
	                                        
	                                        out.println("<hr/>");
	                                        out.println("</td>");
                           	     
                                    	}
                                    	else{
                                    		cnt++;
                                    	}
                                    }
                                }
                                catch(Exception e) {
                                    out.println("Exception caught: " +e.getMessage());
                                }
                            %>
                    </tbody>
                </table>
            </div>
                        
            <div class="content">
                <div class="subscribe">
                    <h3>Sign Up for the <b>NEWSDEAL</b></h3>
                    <form action="email.php" method="POST">
                        <input type="text" name="email" placeholder="Enter Your Email" />
                        <button type="submit">Subscribe</button>
                    </form>
                </div>
            </div>
        </div>
        <%@include  file="components/footer.html" %>
    </div>
    <script type="text/javascript" src="js/countdown.js"></script>
</body>
</html>