package com.sprng.tech_inventory.service;

import com.sprng.tech_comman.response.ProductResponse;

public interface InventoryProductService {
	
	ProductResponse getMethod1(Long id);
	
	ProductResponse getMethod2(Long id);

}
