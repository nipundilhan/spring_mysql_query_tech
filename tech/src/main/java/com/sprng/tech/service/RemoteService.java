package com.sprng.tech.service;

import com.sprng.tech_comman.request.OrderRequest;
import com.sprng.tech_comman.response.SuccessDetailReponse;

public interface RemoteService {
	
	SuccessDetailReponse deductStore(OrderRequest orderRequest);

}
