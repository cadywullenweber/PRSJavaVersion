package com.example.spring.PRS.Projectwithjava;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RequestLine {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	@Column(nullable=false)
	private int RequestId;
	
	@Column(nullable=false)
	private int ProductId;
	
	@Column(nullable=false)
	private int Quantity;
	
	//Navigation Properties
	@ManyToOne
	@JoinColumn(name="RequestId", insertable=false, updatable=false)
	@JsonIgnore
	private Request request;
	
	@ManyToOne
	@JoinColumn(name="ProductId", insertable=false, updatable=false)
	@JsonIgnore
	private Product product;
	
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getRequestId() {
		return RequestId;
	}

	public void setRequestId(int requestId) {
		RequestId = requestId;
	}

	public int getProductId() {
		return ProductId;
	}

	public void setProductId(int productId) {
		ProductId = productId;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public RequestLine() {
	
	}

	public RequestLine(int id, int requestId, int productId, int quantity) {
		super();
		Id = id;
		RequestId = requestId;
		ProductId = productId;
		Quantity = quantity;
	}
	
	
	
}
