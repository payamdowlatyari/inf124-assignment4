package iae.s20;

public class Order {
	private int id;
	private String email;
	private String phone;
	private String address;
	private String city;
	private String state;
	private int zip;
	private String method;
	private String cardname;
	private String cardnumber;
	private int expmonth;
	private int expyear;
	private int cvv;
	private String name;
	private double totalPrice;
	
	public Order() {
		this.id = Integer.MIN_VALUE;
		this.email = "";
		this.phone= "";
		this.address = "";
		this.city = "";
		this.state = "";
		this.zip = 0;
		this.method = "";
		this.cardname = "";
		this.cardnumber = "";
		this.expyear = 0;
		this.expmonth = 0;
		this.cvv = Integer.MIN_VALUE;
		this.name = "";
		this.totalPrice = Double.MIN_NORMAL;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getCardname() {
		return cardname;
	}

	public void setCardname(String cardname) {
		this.cardname = cardname;
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public int getExpmonth() {
		return expmonth;
	}

	public void setExpmonth(int expmonth) {
		this.expmonth = expmonth;
	}

	public int getExpyear() {
		return expyear;
	}

	public void setExpyear(int expyear) {
		this.expyear = expyear;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
