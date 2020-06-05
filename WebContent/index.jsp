<%-- 
    Document   : index
    Created on : May 23, 2020, 8:01:25 PM
    Author     : zhude
--%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include  file="components/head.html" %>
<body>
    
    <div class="container">
        <%@include  file="components/header.html" %>
        <div class="main">
            <div class="content">
                <h1>New Products</h1>
                <table width="100%" height="100%" cellspacing="20">
                    <tbody>
                            <%
                                try {
                                    java.sql.Connection con;
                                    Class.forName("com.mysql.cj.jdbc.Driver");
                                    con = DriverManager.getConnection("jdbc:mysql://localhost/ssdb", "root", "");
                                    out.println ("database successfully opened.");

                                    Statement stmt=con.createStatement();  
                                    ResultSet rs=stmt.executeQuery("select * from products");  
                                    int rowCount = 5, cnt = 0;
                                    
                                    while(rs.next()) 
                                    {
                                        if(cnt == 0)
                                            out.println("<tr>");
                                        cnt++;
                                        out.println("<td class=\"product\">");
                                        out.println("<a href=\"./product.jsp?id=" + rs.getInt(1) +"\">");
                                        out.println("<img src=\"assets/" + rs.getString(4) +"\" alt=\"" + rs.getString(2) + "picture\"></a>");
                                        out.println("<h4>" + rs.getString(5) + "</h4>");
                                        out.println("<h2>" + rs.getString(2) + "</h2>");
                                        out.println("<h3>" + rs.getString(7) + "</h3>");
                                        
                                        out.println("<p>" + rs.getString(3) + "</p>");
                                        /*
                                        out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
                                        out.println("<hr/>");
                                        //
                                        */
                                        out.println("<hr/>");
                                        out.println("</td>");
                                        if(cnt == rowCount)
                                        {
                                            cnt = 0;
                                            out.println("</tr>");
                                        }
                                    }
                                    con.close();  
                                }
                                catch(SQLException e) {
                                    out.println("SQLException caught: " +e.getMessage());
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
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="hotdeal">
                <div class="detail">
                    <h1>Back to school deal</h1>
                    <div class="countdown">00 DAYS, 00 HOURS, 00 MINS, 00 SECS</div>
                    <h2>UP TO 50% OFF</h2>
                    <a href="products.php" class="getdeal">Shop Now</a>
                </div>
            </div>
                        
                        <div class="content">
                <h1>Recent Viewed</h1>
                <table width="100%" height="100%" cellspacing="20">
                    <tbody>
                            <%
                                try {
                                    java.sql.Connection con;
                                    Class.forName("com.mysql.cj.jdbc.Driver");
                                    con = DriverManager.getConnection("jdbc:mysql://localhost/ssdb", "root", "");
                                    out.println ("database successfully opened.");

                                    Statement stmt=con.createStatement();  
                                    ResultSet rs=stmt.executeQuery("select * from products");  
                                    int rowCount = 5, cnt = 0;
                                    
                                    while(rs.next()) 
                                    {
                                        if(cnt == 0)
                                            out.println("<tr>");
                                        cnt++;
                                        if(cnt == 1 || cnt == 3 || cnt ==9 )
                                            continue;
                                        
                                        out.println("<td class=\"product\">");
                                        out.println("<a href=\"./product.jsp?id=" + rs.getInt(1) +"\">");
                                        out.println("<img src=\"assets/" + rs.getString(4) +"\" alt=\"" + rs.getString(2) + "picture\"></a>");
                                        out.println("<h4>" + rs.getString(5) + "</h4>");
                                        out.println("<h2>" + rs.getString(2) + "</h2>");
                                        out.println("<h3>" + rs.getString(7) + "</h3>");
                                        
                                        out.println("<p>" + rs.getString(3) + "</p>");
                                        out.println("<p>visited pid:" + rs.getString(1) + "</p>");
                                        /*
                                        out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
                                        out.println("<hr/>");
                                        //
                                        */
                                        out.println("<hr/>");
                                        out.println("</td>");
                                        if(cnt == rowCount)
                                        {
                                            cnt = 0;
                                            out.println("</tr>");
                                            break;
                                        }
                                    }
                                    con.close();  
                                }
                                catch(SQLException e) {
                                    out.println("SQLException caught: " +e.getMessage());
                                }
                            %>
                        </tr>
                    </tbody>
                </table>
            </div>
                        
            <div class="content">
                <h1>TOP SELLING</h1>
                <table width="100%" cellspacing="20">
                    <tbody>
                        <tr>
                            <?php
                                $stmt = $pdo->query("SELECT * FROM products LIMIT 5, 5");
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
                            ?>
                        </tr>
                    </tbody>
                </table>
            </div>
                        
            
            <div class="hotdeal">
                <div class="detail">
                    <h1>Back to school deal</h1>
                    <div class="countdown">00 DAYS, 00 HOURS, 00 MINS, 00 SECS</div>
                    <h2>UP TO 50% OFF</h2>
                    <a href="products.php" class="getdeal">Shop Now</a>
                </div>
            </div>
            <div class="content">
                <h1>TOP SELLING</h1>
                <table width="100%" cellspacing="20">
                    <tbody>
                        <tr>
                            <?php
                                $stmt = $pdo->query("SELECT * FROM products LIMIT 5, 5");
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
                            ?>
                        </tr>
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