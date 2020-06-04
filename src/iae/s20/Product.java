package iae.s20;

import javax.xml.bind.annotation.XmlRootElement;
public class Product {
	private String name;
	private String summary;
	private String thumbnail;
	private String category;
	private String detail;
	private double price;
	private int id;
	
	public Product() {
		this.name = "";
		this.summary = "";
		this.thumbnail = "";
		this.category = "";
		this.detail = "";
		this.price = 0.0;
		this.id = Integer.MAX_VALUE;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public String getSummary() {
		return this.summary;
	}
	
	public void setSummary(String newSummary) {
		this.summary = newSummary;
	}
	
	public String getThumbnail() {
		return this.thumbnail;
	}
	
	public void setThumbnail(String newThumbnail) {
		this.thumbnail = newThumbnail;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public void setCategory(String newCategory) {
		this.category = newCategory;
	}
	
	public String getDetail() {
		return this.detail;
	}
	
	public void setDetail(String newDetail) {
		this.detail = newDetail;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setPrice(double newPrice) {
		this.price = newPrice;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int newId) {
		this.id = newId;
	}
}

