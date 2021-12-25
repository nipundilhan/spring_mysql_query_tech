package com.sprng.tech_inventory.service;

import com.sprng.tech_comman.response.ProductResponse;

public interface ValidationService {
	
	ProductResponse getProductDetails(Long id);

}
