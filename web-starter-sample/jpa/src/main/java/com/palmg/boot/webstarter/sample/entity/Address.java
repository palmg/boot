package com.palmg.boot.webstarter.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

	@Id  
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable = false)
	private Long id;

	@Column(name="description", nullable = false)
	private String description;

	public Address(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", name=" + description + "]";
	}
	
	public static String statement() {
		return "Create Table Address (id int(10) Not NULL AUTO_INCREMENT PRIMARY KEY, description varchar(255) NOT NULL);";
	}
}
