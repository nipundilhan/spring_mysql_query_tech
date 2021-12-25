package com.sprng.tech.resource.response.success;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PersonFullDetailsDto {
	
	private Long id;
	
	private String fName;
	
	private Date dob;
	
	private String civilStatus;
	
	private String contactValue;
	
	private BigDecimal bmi;
	
	private String profession;
	
	private String area;
	

	public PersonFullDetailsDto(Long id, String fName, Date dob, String civilStatus, String contactValue,
			BigDecimal bmi, String profession, String area) {
		super();
		this.id = id;
		this.fName = fName;
		this.dob = dob;
		this.civilStatus = civilStatus;
		this.contactValue = contactValue;
		this.bmi = bmi;
		this.profession = profession;
		this.area = area;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getCivilStatus() {
		return civilStatus;
	}

	public void setCivilStatus(String civilStatus) {
		this.civilStatus = civilStatus;
	}

	public String getContactValue() {
		return contactValue;
	}

	public void setContactValue(String contactValue) {
		this.contactValue = contactValue;
	}

	public BigDecimal getBmi() {
		return bmi;
	}

	public void setBmi(BigDecimal bmi) {
		this.bmi = bmi;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	
	
	
	
	

}
