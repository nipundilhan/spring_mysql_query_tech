package com.sprng.tech_inventory.service;

import com.sprng.comn_comman.exception.ApplicationException;
import com.sprng.tech_comman.request.OrderRequest;
import com.sprng.tech_comman.response.SuccessDetailReponse;

public interface StoreStockService {
	
	SuccessDetailReponse deductStock(OrderRequest orderRequest) throws ApplicationException;

}
