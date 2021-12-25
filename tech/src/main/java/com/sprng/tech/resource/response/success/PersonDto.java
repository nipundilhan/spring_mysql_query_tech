package com.sprng.tech.resource.response.success;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PersonDto {
	
	private Long id;
	
	private String fName;
	
	private Date dob;
	
	private String civilStatus;
	
	



	public PersonDto(Long id, String fName, Date dob, String civilStatus) {
		super();
		this.id = id;
		this.fName = fName;
		this.dob = dob;
		this.civilStatus = civilStatus;
	}

	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getDob() {
		return dob;
	}




	public void setDob(Date dob) {
		this.dob = dob;
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


	public String getCivilStatus() {
		return civilStatus;
	}


	public void setCivilStatus(String civilStatus) {
		this.civilStatus = civilStatus;
	}


}
