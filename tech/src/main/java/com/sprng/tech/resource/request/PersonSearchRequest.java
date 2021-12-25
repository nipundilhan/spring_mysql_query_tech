package com.sprng.tech.resource.request;



import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PersonSearchRequest {
	
	private String contactValue;
	
	private String name;
	
//	private Long profeesionId;
	
	private BigDecimal bmi;
	
	private String civilStatusCode;
	
	private Long areaId;
	
	private String fromDate;
	
	private String toDate;
	
	private List<Long> profeesionIdList;

}
