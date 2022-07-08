package com.example.spring.PRS.Projectwithjava;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints= {
	    @UniqueConstraint(name="UK_Vendor_Code", columnNames ="Code")
	})
public class Vendor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	@Column(length=30, nullable=false)
	private String Code;
	
	@Column(length=30, nullable=false)
	private String Name;
	
	@Column(length=30, nullable=false)
	private String Address;
	
	@Column(length=30, nullable=false)
	private String City;
	
	@Column(length=2, nullable=false)
	private String State;
	
	@Column(length=5, nullable=false)
	private String Zip;
	
	@Column(length=12, nullable=true)
	private String Phone;
	
	@Column(length=255, nullable=true)
	private String Email;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getZip() {
		return Zip;
	}

	public void setZip(String zip) {
		Zip = zip;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Vendor() {
		
	}

	public Vendor(int id, String code, String name, String address, String city, String state, String zip, String phone,
			String email) {
		super();
		Id = id;
		Code = code;
		Name = name;
		Address = address;
		City = city;
		State = state;
		Zip = zip;
		Phone = phone;
		Email = email;
	}
	
	
}
