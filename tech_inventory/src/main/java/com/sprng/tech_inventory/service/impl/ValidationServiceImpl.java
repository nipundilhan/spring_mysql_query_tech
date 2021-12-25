package com.sprng.tech_inventory.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprng.comn_comman.core.DefaultRequestHeaders;
import com.sprng.comn_comman.core.LoggerRequest;
import com.sprng.tech_comman.response.ProductResponse;
import com.sprng.tech_inventory.service.ValidationService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ValidationServiceImpl implements ValidationService{

	@Autowired
    private RestTemplate restTemplate;
	
	
	private Object getDetailFromServiceBasic( String serviceUrl, String id, Class<?> classObject) {
	     try {
	    	 HttpHeaders headers = null;
	    	 HttpEntity <String> entity = new HttpEntity<>(headers);
	    	 
	    	 return restTemplate.exchange(serviceUrl+"/"+id, HttpMethod.GET, entity, classObject).getBody();
	    	 
	    	 
	     } catch (RestClientException e) {
	         String result=null;
	         if(e.getMessage().contains("503")) {
	        	 result="not avaliable";
	         }else if(e.getMessage().contains("500")) {
	        	 result="internal server error";
	         }else if(e.getMessage().contains("404")) {
	        	 result="not found";
	         }else if(e.getMessage().contains("400")) {
	        	 result="bad request";
	         }else if(e.getMessage().contains("204")) {
	        	 result="internal server error";
	         }
	         ObjectMapper mapper = new ObjectMapper();
	         try {
	             return mapper.readValue(result, classObject);
	         } catch (IOException e1) {
	             return null;
	         }
	     }
	}
	
	
	
	private Object getDetailFromService( String serviceUrl, String id, Class<?> classObject) {
		LoggerRequest.getInstance().logInfo("getDetailFromService ### IN getDetailFromService ");
	     try {
	    	 LoggerRequest.getInstance().logInfo("getDetailFromService ### IN getDetailFromService 01");
	    	 HttpHeaders headers = null;
//	    	 HttpHeaders headers = DefaultRequestHeaders.getInstance().getHeaders();
//	    	 headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    	 HttpEntity <String> entity = new HttpEntity<>(headers);
	    	 return restTemplate.exchange(serviceUrl+"/"+id, HttpMethod.GET, entity, classObject).getBody();
	     } catch (RestClientException e) {
	         String result=null;
	         if(e.getMessage().contains("503")) {
	        	 result="not avaliable";
//	             result=serviceStatus +ServiceStatus.NOT_AVAILABLE.toString()+"\"}";
	         }else if(e.getMessage().contains("500")) {
	        	 result="internal server error";
//	             result=serviceStatus+ ServiceStatus.EXCEPTION.toString()+"\"}";
	         }else if(e.getMessage().contains("404")) {
	        	 result="not found";
//	             result=serviceStatus+ ServiceStatus.NOT_FOUND.toString()+"\"}";
	         }else if(e.getMessage().contains("400")) {
	        	 result="bad request";
//	             result=serviceStatus+ ServiceStatus.BAD_REQUEST.toString()+"\"}";
	         }else if(e.getMessage().contains("204")) {
	        	 result="internal server error";
	        	 LoggerRequest.getInstance().logInfo("getDetailFromService ### IN getDetailFromService 02");
//	             result=serviceStatus+ ServiceStatus.EXCEPTION.toString()+"\"}";
	         }
	         LoggerRequest.getInstance().logInfo("getDetailFromService ### IN getDetailFromService 03");
	         ObjectMapper mapper = new ObjectMapper();
	         LoggerRequest.getInstance().logInfo("getDetailFromService ### IN getDetailFromService 04");
	         try {
	        	 LoggerRequest.getInstance().logInfo("getDetailFromService ### IN getDetailFromService 05");
	             return mapper.readValue(result, classObject);
	         } catch (IOException e1) {
	        	 LoggerRequest.getInstance().logInfo("getDetailFromService ### IN getDetailFromService 06");
	             return null;
	         }
	     }
	}
	
	
	
	
	
	
	@Override
	public ProductResponse getProductDetails(Long id) {
		
		ProductResponse productResponse=(ProductResponse)getDetailFromServiceBasic("http://localhost:8082/product", id.toString() ,ProductResponse.class);
		
		
		if(productResponse!=null) {

		}else {

		}
		
		return productResponse;
	}
}
