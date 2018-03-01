package com.palmg.boot.webstarter.sample.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id  
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable = false)
	private Long id;

	@Column(name="name", nullable = false)
	private String name;

	@Column(name="address", nullable = false)
	private String address;

	public User() {}
	
	public User(Long id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}
	
	public User(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
	
	public static String statement() {
		return "Create Table User (id int(10) Not NULL AUTO_INCREMENT PRIMARY KEY, address varchar(255) NOT NULL, name varchar(255) NOT NULL);";
	}
}