package com.sprng.tech.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprng.comn_comman.exception.ApplicationException;
import com.sprng.tech.dao.entity.order.OrderProduct;
import com.sprng.tech.dao.entity.order.Orders;
import com.sprng.tech.dao.entity.order.Product;
import com.sprng.tech.dao.entity.table.Customer;
import com.sprng.tech.dao.repository.CustomerRepository;
import com.sprng.tech.dao.repository.OrderProductRepository;
import com.sprng.tech.dao.repository.OrdersRepository;
import com.sprng.tech.dao.repository.ProductRepository;
import com.sprng.tech.service.OrderService;
import com.sprng.tech.service.RemoteService;
import com.sprng.tech_comman.request.OrderProductRequest;
import com.sprng.tech_comman.request.OrderRequest;
import com.sprng.tech_comman.response.SuccessDetailReponse;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private	OrderProductRepository orderProductRepository;
	
	@Autowired
	private	OrdersRepository ordersRepository;
	
	@Autowired
	private	CustomerRepository customerRepository;
	
	@Autowired
	private	ProductRepository productRepository;
	
	@Autowired
	private	RemoteService remoteService;
	
	@Override
	public SuccessDetailReponse saveOrder(OrderRequest orderRequest) throws ApplicationException {
		
		Optional<Customer> customerOptional = customerRepository.findById(orderRequest.getCustomerId());
		if(!customerOptional.isPresent()) {
			throw new ApplicationException("customer not found");
		}
		
		BigDecimal totalAmount = BigDecimal.ZERO;
		
		Orders order = new Orders();
		order.setCustomer(customerOptional.get());
		order.setOrderDate(new Date());
		order.setTotalAmount(totalAmount);
		order = ordersRepository.save(order);
		
		
		
		List<OrderProduct> orderProductList = null;
		
		if((orderRequest.getOrderProductList() != null) && (!orderRequest.getOrderProductList().isEmpty())) {
			
			orderProductList =  new ArrayList<>();
			
			for(OrderProductRequest opr :orderRequest.getOrderProductList()) {
				Optional<Product> productOptional = productRepository.findById(opr.getProductId());
				if(!productOptional.isPresent()) {
					throw new ApplicationException("product not found");
				}
				
				BigDecimal amount =(productOptional.get().getUnitPrice()).multiply(BigDecimal.valueOf(opr.getQuantity()));
				
				totalAmount = totalAmount.add(amount);
				
				OrderProduct ordrPrdct = new OrderProduct();
				ordrPrdct.setOrder(order);
				ordrPrdct.setProduct(productOptional.get());
				ordrPrdct.setQuantity(opr.getQuantity());
				ordrPrdct.setAmount(amount);
				
				orderProductList.add(ordrPrdct);				
			}
			
			if((orderProductList!= null) && (!orderProductList.isEmpty())) {
				orderProductRepository.saveAll(orderProductList);
			}
		}
		
		order.setTotalAmount(totalAmount);
		ordersRepository.save(order);
		
		SuccessDetailReponse response = new SuccessDetailReponse();
		String message =" order sucessfulyy added ";
		
		response = remoteService.deductStore(orderRequest);
		
		if(response==null) {
			throw new ApplicationException("exception occurs in remote service");
		}
		
		String messageStock = response.getMessage();
		message = message +messageStock ;
		
		response.setMessage(message);
		
		return response;

	}

}
