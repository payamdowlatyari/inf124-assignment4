package iae.s20;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AboutServlet
 */
//@WebServlet("/AboutServlet")
public class AboutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AboutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {	
	   	
        response.setContentType("text/html;charset=UTF-8");
           
           response.setContentType("text/html");
           response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
           PrintWriter out = response.getWriter();
           
               out.println("<!DOCTYPE html>");
               out.println("<html>");
               out.println("<head>");
               out.println("<title>About Us</title>");  
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
               out.println("<li><a href=\"HomeServlet\">Home</a></li> ");
               out.println("<li><a href=\"ProductServlet\">Products</a></li> ");
               out.println("<li><a href=\"AboutServlet\"class=\"active\">About Us</a></li> ");
               out.println("<li><a href=\"ContactServlet\">Contact Us</a></li>");
               out.println("<li><a href=\"CartServlet\">Shopping Cart</a></li>");
               out.println("</ul></div></div>");
               out.println(" <div class=\"main\">");
               out.println(" <div class=\"content\">");          
               out.println("<br><br>  <h1>About Us</h1>\n" + 
               		"                <div class=\"about-section\">\n" + 
               		"                  <div class=\"content\">\n" + 
               		"                        <br><br>\n" + 
               		"                        <h3>WHO WE ARE</h3>\n" + 
               		"                        <p>\n" + 
               		"                            Welcome to Sports Store! We are an online business currently based in\n" + 
               		"                            Irvine, California. We offer only the best quality sporting goods and quality service.\n" + 
               		"                            We hope you use Sports Store for all of your sports gear needs!\n" + 
               		"                        </p><br>\n" + 
               		"                    <h3>CATEGORIES</h3>\n" + 
               		"                        <p>\n" + 
               		"                            At Sports Store, we sell a variety of sporting goods. From sports balls, to sport wear,\n" + 
               		"                            to training equipment, we've got it all. Want to see if an item is in stock?\n" + 
               		"                            Want to request a special item? Contact us and we will respond to your requests as\n" + 
               		"                            soon as possible!\n" + 
               		"                        </p><br>\n" + 
               		"                    <h3>ORDERS</h3>\n" + 
               		"                        <p>\n" + 
               		"                            Placing an order is as easy as 1, 2, 3!\n" + 
               		"                            Search for the product you want to place an order for.\n" + 
               		"                            Fill out your shipping and payment information.\n" + 
               		"                            Wait eagerly for speedy delivery of your products!\n" + 
               		"\n" + 
               		"                            Products will take 5-7 business days to be delivered. Contact us if you have any questions\n" + 
               		"                            about your order.\n" + 
               		"                        </p><br>\n" + 
               		"                    <h3>SERVICES</h3>\n" + 
               		"                        <p>\n" + 
               		"                            Customer Service is our number one priority! With 24/7 customer support, we are available to\n" + 
               		"                            help you whenever you need. Whether it is a question about a product or concerns about\n" + 
               		"                            an order, we've got you covered. Call by phone or email us and we will get back to you\n" + 
               		"                            right away!\n" + 
               		"                        </p><br><br>\n" + 
               		"                </div>\n" + 
               		"                <h2 style=\"text-align:center;margin: 50px 0 25px;\">Our Management Team</h2>\n" + 
               		"                <hr>\n" + 
               		"                <div style=\"margin: 25px 0 50px; display:flex;\">\n" + 
               		"                  <div class=\"card\">\n" + 
               		"                    <h3>Darrel Belen</h3>\n" + 
               		"                    <p class=\"title\">CEO & Founder</p>\n" + 
               		"                    <p>Responsible for overall company strategy and promoting company growth.</p>\n" + 
               		"                    <a href=\"#\">darrel@sportstore.com</a>\n" + 
               		"                    <p>SID: 72866173</p>\n" + 
               		"                  </div>\n" + 
               		"                  <div class=\"card\">\n" + 
               		"                    <h3>Vinh Chuong</h3>\n" + 
               		"                    <p class=\"title\">Sales Director</p>\n" + 
               		"                    <p>Responsible for the development of sales strategy and maintaining customer relations.</p>\n" + 
               		"                    <a href=\"#\">vinh@sportstore.com</a>\n" + 
               		"                    <p>SID: 20376995</p>\n" + 
               		"                  </div>\n" + 
               		"                  <div class=\"card\">\n" + 
               		"                    <h3>Payam Dowlatyari</h3>\n" + 
               		"                    <p class=\"title\">Human Resources Director</p>\n" + 
               		"                    <p>Responsible for the inner workings of the company.</p>\n" + 
               		"                    <a href=\"#\">payam@sportstore.com</a>\n" + 
               		"                    <p>SID: 11124675</p>\n" + 
               		"                  </div>\n" + 
               		"                  <div class=\"card\">\n" + 
               		"                    <h3>Sanjith Venkatesh</h3>\n" + 
               		"                    <p class=\"title\">Customer Service Director</p>\n" + 
               		"                    <p>Responsible for any customer service requests.</p>\n" + 
               		"                    <a href=\"#\">sanjith@sportstore.com</a>\n" + 
               		"                    <p>SID: 20038520</p>\n" + 
               		"                  </div>\n" + 
               		"                </div>");

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
