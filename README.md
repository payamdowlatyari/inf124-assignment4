# inf124-assignment4
Building a web application using Java Servlets, JSPs, and REST APIs

Contributors:
* Darrel Christopher Belen ID: 72866173
* Payam Dowlat Yari ID: 11124675
* Sanjith Venkatesh ID: 20038520	
* Deming Zhu ID: 81112413

The home page consists of a list of products, including names, descriptions, and prices. There are also a header and a footer, including navigation to other pages and contact information. There is also a section that shows the items recently viewed by each user.

For each requirement:
1. The HomeServlet is the index page and it displays product information with the data retrieved from DB. It shows recently viewed items for each user. PreviousItems page is included for providing in the recently viewed information(lines 107 and 108).
2. When clicking on an item on the home page, a detail page with detailed item information will be displayed. A "Add to Cart" button is on the page and will add the displayed item into a "shopping cart" for each user.
3. When users click on checkout, the page will display all the items stored in the shopping cart (HttpSession) of each user. Users can enter order information and submit it. The submission will be saved in DB and forwarded to an order detail page(Confirmation servlet, lines 173 and 174). 