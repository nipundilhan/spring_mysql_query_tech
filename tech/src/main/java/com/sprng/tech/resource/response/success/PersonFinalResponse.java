package com.sprng.tech.resource.response.success;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sprng.comn_comman.core.resource.response.sucess.PagingResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PersonFinalResponse {
	
	List<PersonResponse> personList;
	
	PagingResponseDto pagingResponseDto;

}
