package com.sprng.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprng.comn_comman.exception.ApplicationException;
import com.sprng.tech.service.ProductService;
import com.sprng.tech_comman.response.ProductResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/product")
public class ProductController {
	
	@Autowired
	private	ProductService productService;
	
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct( @PathVariable(value = "id") Long id) throws ApplicationException {
    	
    	ProductResponse response = productService.getProductDetailsById(id);
    	
    	return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
