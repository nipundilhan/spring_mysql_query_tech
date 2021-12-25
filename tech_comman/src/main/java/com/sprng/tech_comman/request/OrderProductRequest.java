package com.sprng.tech_comman.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sprng.tech_comman.response.ProductResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OrderProductRequest {
	
	private Long productId;
	
	private Long quantity;

}
