package com.sprng.tech.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprng.comn_comman.core.resource.response.error.ValidateResponse;
import com.sprng.comn_comman.exception.ApplicationException;
import com.sprng.comn_comman.exception.ValidationListException;

@RestController
@RequestMapping(value = "/exception")
public class ExceptionTestController {
	
	
    @GetMapping("/exception-1")
    ResponseEntity<?> exception1() throws Exception {
        // persisting the user
    	boolean value = true;
    	
    	if(value) {
    		throw new ApplicationException("application exception thorws");
    	}
        return ResponseEntity.ok("ok");
    }
    
    
    @GetMapping("/exception-2")
    ResponseEntity<?> exception2() throws Exception {
        // persisting the user
    	boolean value = true;
        List<ValidateResponse> vldtnLst = new ArrayList<>();
        
        ValidateResponse vr1 = new ValidateResponse("item","not match with parent id");
        vldtnLst.add(vr1);
        ValidateResponse vr2 = new ValidateResponse("item","status inactive");
        vldtnLst.add(vr2);
        
    	if(!vldtnLst.isEmpty()) {
    		throw new ValidationListException(vldtnLst);
    	}
        return ResponseEntity.ok("ok");
    }

}
