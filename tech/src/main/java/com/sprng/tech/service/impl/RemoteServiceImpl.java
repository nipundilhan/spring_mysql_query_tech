package com.sprng.tech.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.sprng.tech.service.RemoteService;
import com.sprng.tech_comman.request.OrderRequest;
import com.sprng.tech_comman.response.SuccessDetailReponse;

@Service
public class RemoteServiceImpl implements RemoteService{
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Override
	public SuccessDetailReponse deductStore(OrderRequest orderRequest) {
		
		
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			// headers.set("username", createdUser);
			
			String url = "http://localhost:7071/inventory/store-stock/deduct";
	
	        
			HttpEntity <OrderRequest> entity = new HttpEntity<>(orderRequest, headers);
			
			ResponseEntity<SuccessDetailReponse> response =restTemplate.exchange(url, HttpMethod.POST, entity, SuccessDetailReponse.class);
			return response.getBody();
		} catch (RestClientException e) {
	         String result=null;
	         if(e.getMessage().contains("503")) {
	             result=" not available ";
	         }else if(e.getMessage().contains("500")) {
	             result=" internal server error ";
	         }else if(e.getMessage().contains("404")) {
	             result=" not found";
	         }else if(e.getMessage().contains("400")) {
	             result=" bad request";
	         }else {
	             result="503";
	         }
	         
	         return null;
	     }
	}

}
