package com.sprng.tech.service;

import com.sprng.comn_comman.exception.ApplicationException;
import com.sprng.tech_comman.request.OrderRequest;
import com.sprng.tech_comman.response.SuccessDetailReponse;

public interface OrderService {
	
	SuccessDetailReponse saveOrder(OrderRequest orderRequest) throws ApplicationException;

}
