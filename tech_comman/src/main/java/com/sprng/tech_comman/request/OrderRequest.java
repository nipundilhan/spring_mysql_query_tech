package com.sprng.tech_comman.request;

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
public class OrderRequest {
	
	private Long storeId;
	
	private Long customerId;
	
	private List<OrderProductRequest> orderProductList;

}
