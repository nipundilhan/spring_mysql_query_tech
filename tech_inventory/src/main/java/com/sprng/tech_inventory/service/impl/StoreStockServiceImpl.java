package com.sprng.tech_inventory.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprng.comn_comman.exception.ApplicationException;
import com.sprng.tech_comman.enums.StockActionEnum;
import com.sprng.tech_comman.request.OrderProductRequest;
import com.sprng.tech_comman.request.OrderRequest;
import com.sprng.tech_comman.response.SuccessDetailReponse;
import com.sprng.tech_inventory.entity.Store;
import com.sprng.tech_inventory.entity.StoreStock;
import com.sprng.tech_inventory.entity.StoreStockHistory;
import com.sprng.tech_inventory.repository.StoreRepository;
import com.sprng.tech_inventory.repository.StoreStockHistoryRepository;
import com.sprng.tech_inventory.repository.StoreStockRepository;
import com.sprng.tech_inventory.service.StoreStockService;

@Service
@Transactional(rollbackFor = Exception.class)
public class StoreStockServiceImpl implements StoreStockService{
	
	@Autowired
    private StoreStockHistoryRepository storeStockHistoryRepository;
	
	@Autowired
    private StoreStockRepository storeStockRepository;
	
	@Autowired
    private StoreRepository storeRepository;
	
	
	public SuccessDetailReponse deductStock(OrderRequest orderRequest) throws ApplicationException {
		
		Optional<Store> storeOptional = storeRepository.findById(orderRequest.getStoreId());
		if(!storeOptional.isPresent()) {
			throw new ApplicationException("store not found");
		}
		
		if((orderRequest.getOrderProductList() != null) && (!orderRequest.getOrderProductList().isEmpty())) {
			
			for(OrderProductRequest opr :orderRequest.getOrderProductList()) {
				List<StoreStock> storeStockProductList = storeStockRepository.findAllByStoreAndProductId(storeOptional.get(), opr.getProductId());
				
				if((storeStockProductList == null) || (storeStockProductList.isEmpty())) {
					throw new ApplicationException("store stock not found");
				}
								
				StoreStock storeStock = storeStockProductList.get(0);
				
				Long newAvailable = storeStock.getAvailableQuantity()-opr.getQuantity();
				
				storeStock.setAvailableQuantity(newAvailable);
				storeStockRepository.save(storeStock);

				StoreStockHistory storeStockHistory = new StoreStockHistory();
				storeStockHistory.setOrderProductId(null);
				storeStockHistory.setProductId(opr.getProductId());
				storeStockHistory.setStore(storeOptional.get());
				storeStockHistory.setStoreStock(storeStock);
				storeStockHistory.setAction(StockActionEnum.DEBIT);
				storeStockHistory.setInitialQuantity(storeStock.getInitialQuantity());
				storeStockHistory.setAvailableQuantity(newAvailable);
				storeStockHistory.setQuantity(opr.getQuantity());
				
				storeStockHistoryRepository.save(storeStockHistory);			
			}
		}
		
		SuccessDetailReponse response = new SuccessDetailReponse();
		response.setCode(1l);
		response.setMessage(" stock deduct successfully ");
		return response;
		
	}

}
