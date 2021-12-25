package com.sprng.tech.service;

import com.sprng.comn_comman.exception.ApplicationException;
import com.sprng.tech_comman.response.ProductResponse;

public interface ProductService {
	
	ProductResponse getProductDetailsById(Long id) throws ApplicationException;

}
