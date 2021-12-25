package com.sprng.tech_inventory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sprng.comn_comman.core.LoggerRequest;
import com.sprng.tech_comman.response.ProductResponse;
import com.sprng.tech_inventory.service.InventoryProductService;
import com.sprng.tech_inventory.service.ValidationService;


@Service
public class InventoryProductServiceImpl implements InventoryProductService{
	
	@Autowired
	private	ValidationService validationService;
	
	
	@Override
	public ProductResponse getMethod1(Long id) {
		
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "http://localhost:8082/product/";
		
		
		ResponseEntity<ProductResponse> response  = restTemplate.getForEntity(fooResourceUrl + id, ProductResponse.class);
		ProductResponse productResponse = response.getBody();
		
		
		LoggerRequest.getInstance().logInfo1("----- get product method print start---------");
		LoggerRequest.getInstance().logInfo1("					");
		LoggerRequest.getInstance().logInfo1("					");
		
		LoggerRequest.getInstance().logInfo1(" brand name -"+productResponse.getBrandName()+"    category -"+productResponse.getCategory());
	
		LoggerRequest.getInstance().logInfo1("					");
		LoggerRequest.getInstance().logInfo1("					");
		LoggerRequest.getInstance().logInfo1("----- get product method print start---------");
		return productResponse;
		
	}
	
	@Override
	public ProductResponse getMethod2(Long id) {
		
		ProductResponse productResponse = validationService.getProductDetails(id);

		LoggerRequest.getInstance().logInfo1("					");
		LoggerRequest.getInstance().logInfo1("					");
		LoggerRequest.getInstance().logInfo1("------------------");
		LoggerRequest.getInstance().logInfo1("					");
		LoggerRequest.getInstance().logInfo1("					");
		
		LoggerRequest.getInstance().logInfo1(" brand name -"+productResponse.getBrandName()+"    category -"+productResponse.getCategory());
	
		LoggerRequest.getInstance().logInfo1("					");
		LoggerRequest.getInstance().logInfo1("					");
		LoggerRequest.getInstance().logInfo1("------------------");
		LoggerRequest.getInstance().logInfo1("					");
		LoggerRequest.getInstance().logInfo1("					");
		
		return productResponse;
	}

}
