package com.sprng.tech.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprng.comn_comman.exception.ApplicationException;
import com.sprng.tech.service.OrderService;
import com.sprng.tech_comman.request.OrderRequest;
import com.sprng.tech_comman.response.SuccessDetailReponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/order")
public class OrderController {
	
	
	@Autowired
	private	OrderService orderService;
	
	
    @PostMapping("/add")
    public ResponseEntity<?> addOrder( @RequestBody OrderRequest orderRequest) throws ApplicationException {
    	
    	SuccessDetailReponse response = orderService.saveOrder(orderRequest);
    	
    	return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
