package com.example.spring.PRS.Projectwithjava;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints= {
	    @UniqueConstraint(name="UK_Product_PartNbr", columnNames ="PartNbr")
	})
public class Product {
	
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int Id;

@Column(length=30, nullable=false)
private String PartNbr;

@Column(length=30, nullable=false)
private String Name;

@Column(nullable=false)
private BigDecimal Price;

@Column(length=30, nullable=false)
private String Unit;

@Column(length=255, nullable=true)
private String PhotoPath;

@Column(nullable=false)
private int VendorId;

public int getId() {
	return Id;
}

public void setId(int id) {
	Id = id;
}

public String getPartNbr() {
	return PartNbr;
}

public void setPartNbr(String partNbr) {
	PartNbr = partNbr;
}

public String getName() {
	return Name;
}

public void setName(String name) {
	Name = name;
}

public BigDecimal getPrice() {
	return Price;
}

public void setPrice(BigDecimal price) {
	Price = price;
}

public String getUnit() {
	return Unit;
}

public void setUnit(String unit) {
	Unit = unit;
}

public String getPhotoPath() {
	return PhotoPath;
}

public void setPhotoPath(String photoPath) {
	PhotoPath = photoPath;
}

public int getVendorId() {
	return VendorId;
}

public void setVendorId(int vendorId) {
	VendorId = vendorId;
}

public Product() {

}

public Product(int id, String partNbr, String name, BigDecimal price, String unit, String photoPath, int vendorId) {
	super();
	Id = id;
	PartNbr = partNbr;
	Name = name;
	Price = price;
	Unit = unit;
	PhotoPath = photoPath;
	VendorId = vendorId;
}


}
