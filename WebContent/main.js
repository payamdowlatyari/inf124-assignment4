function sendEmail() {
  document.getElementById("contactform").action = "mailto:info@sportsstore.com";
}

var quantity = 1;
var price = 0;
var quantityOptions = 1;
price = document.getElementById('unitPrice').innerHTML.substring(1);

var total = quantity * price;

var subtotal = document.getElementsByName("subtotal")[0].id;
var subtotalEl = document.getElementById('total-price');
subtotalEl.innerHTML = subtotal;

function updatePrice() {
	var totalPriceEl = document.getElementById('total-price');
	console.log(totalPriceEl);
  quantityOptions = document.getElementById('quantity').value;
  price = document.getElementById('unitPrice').innerHTML.substring(1);
  quantity = quantityOptions;
  total = quantity * price;
  totalPriceEl.innerHTML = total;
  console.log(quantity);
  console.log(price);

}

// form validation
$(document).ready(function () {
  $("#orderform").submit(function (event) {
    event.preventDefault();
    var fname = $("#fname").val();
    var lname = $("#lname").val();
    var email = $("#email").val();
    var phone = $("#phone").val();
    var adr = $("#adr").val();
    var city = $("#city").val();
    var state = $("#state").val();
    var zip = $("#zip").val();
    var method = $("#method").val();
    var pid = $("#pid").val();
    var quantity = $("#quantity").val();
    var cardname = $("#cname").val();
    var cardnumber = $("#ccnum").val();
    var expmonth = $("#expmonth").val();
    var expyear = $("#expyear").val();
    var cvv = $("#cvv").val();
    var submit = $("#order-submit").val();
    $(".form-message").load("./php/insert.php", {
      firstname: fname,
      lastname: lname,
      email: email,
      phone: phone,
      address: adr,
      city: city,
      state: state,
      zip: zip,
      method: method,
      productid: pid,
      submit: submit,
      quantity: quantity,
      cardname: cardname,
      cardnumber: cardnumber,
      expmonth: expmonth,
      expyear: expyear,
      cvv: cvv
    });
  });
});

function updateTotalPrice(){
	var subtotal = document.getElementsByName("subtotal")[0].id;
	console.log(subtotal);
	var shippingMethod = document.getElementById('method');
	var opt;
	for(var i = 0, len = shippingMethod.options.length; i < len; i++){
		opt = shippingMethod.options[i];
		if(opt.selected === true){
			break;
		}
	}
	var shipping = 0.0;
	console.log(opt.value);
	shippingMethod = opt.value;
	console.log("hello");
	if (shippingMethod === "overnight") {
	      shipping = 11.00;
	      console.log("in overnight");
	 }
	if (shippingMethod === "2day") {
	      shipping = 9.50;
	      console.log("in 2day");
	}
	if (shippingMethod === "7day") {
	      shipping = 6.25;
	      console.log("in 7day");
	}
	var subtotalEl = document.getElementById('total-price');
	var shippingEl = document.getElementById('shipping');
	var finalPrice = document.getElementById('final-price');
	console.log(totalPrice);
	subtotalEl.innerHTML = subtotal;
	console.log(shipping);
	shippingEl.innerHTML = shipping;
	finalPrice.innerHTML = ((parseFloat(subtotal) + parseFloat(shipping)).toFixed(2)).toString();
	console.log(totalPrices);
}

// using jQuery and Ajax for form autocomplete
$(document).ready(function () {

  var shipping = 9.50;

  $(document).mouseover(function () {  

    $('#total-price').html(total.toFixed(2));
       
    let final = (total + (total * taxRate) + shipping);
    
    $('#shipping').html(shipping.toFixed(2));

    $('#final-price').html(final.toFixed(2))
    $('input[id=totalPrice]').val(final.toFixed(2));
  })
	
	var subtotal = document.getElementById('subtotal');
	console.log("subtotal: " + subtotal);
	
	$('#method').change(function () {
	    var choice = $(this).val();
	    console.log(choice);
	    if (choice == "Overnight ($11.00)") {
	      shipping = 11.00;
	    }
	    if (choice == "2-day expedited ($9.50)") {
	      shipping = 9.50;
	    }
	    if (choice == "7-day ground ($6.25)") {
	      shipping = 6.25;
	    }
	  });
	
	$(document).mouseover(function () {

	    $('#total-price').html(total.toFixed(2));
	    $('#tax-amount').html((total * taxRate).toFixed(2));

	    let final = (total + (total * taxRate) + shipping);
	    $('#shipping').html(shipping.toFixed(2));
	    $('#final-price').html(final.toFixed(2))
	    $('input[id=totalPrice]').val(final.toFixed(2));
	  })

 
});

