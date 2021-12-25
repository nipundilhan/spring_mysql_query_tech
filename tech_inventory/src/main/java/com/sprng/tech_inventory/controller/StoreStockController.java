package com.sprng.tech_inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprng.comn_comman.exception.ApplicationException;
import com.sprng.tech_comman.request.OrderRequest;
import com.sprng.tech_comman.response.SuccessDetailReponse;
import com.sprng.tech_inventory.service.StoreStockService;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/inventory/store-stock")
public class StoreStockController {
	
	@Autowired
	private	StoreStockService storeStockService;
	
    @PostMapping("/deduct")
    public ResponseEntity<?> deductStore(@Valid  @RequestBody OrderRequest orderRequest) throws ApplicationException {
    	
    	SuccessDetailReponse response = storeStockService.deductStock(orderRequest);
    	
    	return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
