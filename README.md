
# inf124-assignment4
Building a web application using Java Servlets, JSPs, and REST APIs

Contributors:
* Darrel Christopher Belen ID: 72866173
* Payam Dowlat Yari ID: 11124675
* Sanjith Venkatesh ID: 20038520	
* Deming Zhu ID: 81112413

The home page consists of a list of products, including names, descriptions, and prices. There are also a header and a footer, including navigation to other pages and contact information. There is also a section that shows the items recently viewed by each user.

For each requirement:
1. See either the products.jsp or index.jsp files. Both files use REST calls to get the necessary information and present all of the products in a table like manner.
2. For calls regarding products, see the Product.java, ProductResource.java, and ProductService.java files. For calls regarding orders, see Order.java, OrderResource.java, and OrderService.java. The Product and Order java classes define the structure of those two objects. The Resource classes defines the URLs and the functions that will be invoked. Finally the Service classes are where the database connections are made and the results are delivered.
3. In each of the servlet pages as well as the jsp files, a Client object which is then used to make the necessary REST calls to either GET or POST information to the server files. 

## REST Calls
### All REST calls start with /rest/
### Sample URL: localhost:6060/PA3/rest/products

1. /products (GET): returns all of the products in the products table in the database. Sample return value:[
    {
        "category": "Sports Balls",
        "detail": "\<p>NBA and NCAA-approved Basketball.<\br />\nBecome like one of the pros with this NBA-approved Basketball! Improved durable exterior for extend play.\nGreat for indoor or outdoor games.<\/p>\n<\ul>\n<\li>For indoor/outdoor play\</li>\\n\<li>Ideal size for adults\</li>\n\<li>Composite cover for various surfaces\</li>\n\<li>Replica NBA Basketball\</li>\n\</ul>",
        "id": 1,
        "name": "Basketball",
        "price": 23.99,
        "summary": "NBA AND NCAA-APPROVED BASKETBALL",
        "thumbnail": "baseketball.jpg"
    },
    {
        "category": "Sports Balls",
        "detail": "\<p>NFHS and NCAA-approved Soccer ball. \<br />\nStandard 6-panel construction with original look and feel. Thermal bonded construction allow for a\nmore realistic trajectory and better touch.\</p>\n\<ul>\n\<li>100% polyurethane cover\</li>\n\<li>Textured surface for performance and mobility\</li>\n\<li>FIFA Quality Pro certified\</li>\n\</ul>",
        "id": 2,
        "name": "Soccer Ball",
        "price": 49.99,
        "summary": "NFHS AND NCAA-APPROVED SOCCER BALL",
        "thumbnail": "soccer.jpg"
    }
]
2. /products/{id} (GET): Returns the json details for a single product. For example if the url is "localhost:6060/PA3/rest/products/2", the return value will be:
{
    "category": "Sports Balls",
    "detail": "\<p>NFHS and NCAA-approved Soccer ball. \<br />\nStandard 6-panel construction with original look and feel. Thermal bonded construction allow for a\nmore realistic trajectory and better touch.\</p>\n\<ul>\n\<li>100% polyurethane cover\</li>\n\<li>Textured surface for performance and mobility\</li>\n\<li>FIFA Quality Pro certified\</li>\n\</ul>",
    "id": 2,
    "name": "Soccer Ball",
    "price": 49.99,
    "summary": "NFHS AND NCAA-APPROVED SOCCER BALL",
    "thumbnail": "soccer.jpg"
}

3. /orders (POST): Will insert a new order into the database. Sample input:
{
    "category": "Sports Balls",
    "detail": "\<p>NFHS and NCAA-approved Soccer ball. \<br />\nStandard 6-panel construction with original look and feel. Thermal bonded construction allow for a\nmore realistic trajectory and better touch.\</p>\n\<ul>\n\<li>100% polyurethane cover\</li>\n\<li>Textured surface for performance and mobility\</li>\n\<li>FIFA Quality Pro certified\</li>\n\</ul>",
    "id": 2,
    "name": "Soccer Ball",
    "price": 49.99,
    "summary": "NFHS AND NCAA-APPROVED SOCCER BALL",
    "thumbnail": "soccer.jpg"
}
Sample output: "Order added successfully"

4. /orders/product (POST): Will post the quantity of each product bought in each order to the orderproducts table. Sample input value:
{
	"orderId": 8877,
	"productId": 12,
	"quantity": 2
}
Sample return value: "Order product successfully added"
5. /orders/{id} (GET): returns the specified order details given the id of the order. Sample input value: "localhost:6060/PA3/rest/orders/123"
Sample output value: 
{
    "address": "boboboobo",
    "cardname": "nsfd",
    "cardnumber": "123232",
    "city": "jkjkjk",
    "cvv": 123,
    "email": "bob@bob.com",
    "expmonth": 123,
    "expyear": 123,
    "id": 123,
    "method": "2Day",
    "name": "bob white",
    "phone": "1231231234",
    "state": "BO",
    "totalPrice": 10,
    "zip": 12332
}
6. /orders/{id}/{product} (GET): Returns the quantity amount of each product bought in each order. 
Sample input URL: "localhost:6060/rest/orders/1851/5
Sample output:
{
    "orderId": 2851,
    "productId": 5,
    "quantity": 1
}