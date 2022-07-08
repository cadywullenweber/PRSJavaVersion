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
    @UniqueConstraint(name="UK_User_Username", columnNames ="Username")
})

public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	@Column(length=30, nullable=false)
	private String Username;
	
	@Column(length=30, nullable=false)
	private String Password;
	
	@Column(length=30, nullable=false)
	private String Firstname;
	
	@Column(length=30, nullable=false)
	private String Lastname;
	
	@Column(length=12, nullable=true)
	private String Phone;
	
	@Column(length=255, nullable=true)
	private String Email;
	
	@Column(nullable=false)
	private boolean IsReviewer;
	
	@Column(nullable=false)
	private boolean IsAdmin;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		Firstname = firstname;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
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

	public boolean isIsReviewer() {
		return IsReviewer;
	}

	public void setIsReviewer(boolean isReviewer) {
		IsReviewer = isReviewer;
	}

	public boolean isIsAdmin() {
		return IsAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		IsAdmin = isAdmin;
	}
	
public User() {
		
	}

	public User(int id, String username, String password, String firstname, String lastname, String phone, String email,
			boolean isReviewer, boolean isAdmin) {
		super();
		Id = id;
		Username = username;
		Password = password;
		Firstname = firstname;
		Lastname = lastname;
		Phone = phone;
		Email = email;
		IsReviewer = isReviewer;
		IsAdmin = isAdmin;
	}

	
}