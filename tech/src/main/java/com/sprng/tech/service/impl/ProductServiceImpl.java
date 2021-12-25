package com.sprng.tech.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprng.comn_comman.exception.ApplicationException;
import com.sprng.tech.dao.entity.order.Product;
import com.sprng.tech.dao.repository.ProductRepository;
import com.sprng.tech.service.ProductService;
import com.sprng.tech_comman.response.ProductResponse;


@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private	ProductRepository productRepository;
	
	
	@Override
	public ProductResponse getProductDetailsById(Long id) throws ApplicationException {
				
		Optional<Product> productOptional = productRepository.findById(id);
		if(!productOptional.isPresent()) {
			throw new ApplicationException("product not found");	
		}
		
		Product product = productOptional.get();
		
		ProductResponse productResponse = new ProductResponse();
		productResponse.setProductId(product.getId());
		productResponse.setProductName(product.getName());
		productResponse.setBrandName(product.getProductRoot().getBrand().getName());
		productResponse.setCategory(product.getProductRoot().getProductCategory().getCategoryRoot().getName());
		
		return productResponse;
		
	}

}
