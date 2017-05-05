package com.techexpo.springboot.model.common;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.data.annotation.Id;

public class RealEstateData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	
	private String street;
	private String city;
	private int zip;
	private String state;
	private int beds;
	private int baths;
	private double sq__ft;
	private String type;
	private Date sale_date;
	private int price;
	private double latitude;
	private double longitude;
	
	public RealEstateData() {
		
	}
	
	public RealEstateData(String street, String city, int zip, String state, int beds, int baths, double sq__ft,
			String type, Date sale_date, int price, double latitude, double longitude) {
		super();
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.state = state;
		this.beds = beds;
		this.baths = baths;
		this.sq__ft = sq__ft;
		this.type = type;
		this.sale_date = sale_date;
		this.price = price;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getBeds() {
		return beds;
	}
	public void setBeds(int beds) {
		this.beds = beds;
	}
	public int getBaths() {
		return baths;
	}
	public void setBaths(int baths) {
		this.baths = baths;
	}
	public double getSq__ft() {
		return sq__ft;
	}
	public void setSq__ft(double sq__ft) {
		this.sq__ft = sq__ft;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getSale_date() {
		return sale_date;
	}
	public void setSale_date(Date sale_date) {
		this.sale_date = sale_date;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "RealEstateData [id=" + id + ", street=" + street + ", city=" + city + ", zip=" + zip + ", state="
				+ state + ", beds=" + beds + ", baths=" + baths + ", sq__ft=" + sq__ft + ", type=" + type
				+ ", sale_date=" + sale_date + ", price=" + price + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}
	
	
		
	
}
