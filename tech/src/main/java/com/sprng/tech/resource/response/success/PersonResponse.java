package com.sprng.tech.resource.response.success;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sprng.tech.resource.request.PersonSearchRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PersonResponse {

	
	private String fName;
	
	private String contactValue;
	
	private String profesion;
	
	private String civilStatus;
}
